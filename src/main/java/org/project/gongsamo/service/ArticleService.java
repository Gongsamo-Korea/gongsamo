package org.project.gongsamo.service;

import co.elastic.clients.elasticsearch._types.query_dsl.*;
import lombok.extern.slf4j.Slf4j;
import org.project.gongsamo.domain.ArticleSearch;
import org.project.gongsamo.domain.TagSearch;
import org.project.gongsamo.dto.article.ArticleRequestDto;
import org.project.gongsamo.repository.ArticleSearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.client.elc.NativeQueryBuilder;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional(readOnly = true)
public class ArticleService {
    private final ArticleSearchRepository articleSearchRepository;
    private final TagService tagService;
    private final ElasticsearchOperations elasticsearchOperations;

    @Autowired
    public ArticleService(
        ArticleSearchRepository articleSearchRepository,
        TagService tagService,
        ElasticsearchOperations elasticsearchOperations) {
        this.articleSearchRepository = articleSearchRepository;
        this.tagService = tagService;
        this.elasticsearchOperations = elasticsearchOperations;
    }

    @Transactional
    public ArticleSearch createArticle(ArticleRequestDto articleRequestDto) {
        ArticleSearch articleSearch = articleRequestDto.toEntity();
        List<TagSearch> tagSearches = articleRequestDto.getTagNames()
                .stream()
                .map(this.tagService::findOrCreateTagByName)
                .toList();
        articleSearch.setTags(tagSearches);

        Long articleId = this.articleSearchRepository.getNewId();
        if (articleId != null) {
            this.articleSearchRepository.validateId(articleId);
        }

        articleSearch.setArticleId(articleId);
        return articleSearchRepository.save(articleSearch);
    }

    public Optional<ArticleSearch> findArticle(Long id) {
        return articleSearchRepository.findById(id);
    }

    public List<ArticleSearch> searchArticles(String keyword, Pageable pageable) {
        NativeQueryBuilder queryBuilder = NativeQuery.builder();

        if (keyword.isEmpty()) {
            queryBuilder.withSort(Sort.by(Sort.Direction.DESC, "issueDate"));
        } else {
            QueryStringQuery queryStringQuery = new QueryStringQuery.Builder()
                    .fields("title", "content")
                    .query("*" + keyword + "*")
                    .build();
            NestedQuery nestedQuery = new NestedQuery.Builder()
                    .path("tags")
                    .query(q -> q.bool(b -> b
                            .filter(m -> m.match(mq -> mq
                                    .field("tags.tagName")
                                    .query(keyword)))))
                    .build();

            queryBuilder.withQuery(q -> q.bool(b -> b
                    .should(m -> m.queryString(queryStringQuery))
                    .should(m -> m.nested(nestedQuery))
            ));
        }

        queryBuilder.withPageable(pageable);
        SearchHits<ArticleSearch> searchHits = elasticsearchOperations.search(queryBuilder.build(), ArticleSearch.class);

        return searchHits.getSearchHits().stream().map(SearchHit::getContent).toList();
    }
}
