package org.project.gongsamo.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.project.gongsamo.domain.Article;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static org.project.gongsamo.domain.QArticle.article;
import static org.project.gongsamo.domain.QArticleTag.articleTag;
import static org.project.gongsamo.domain.QTag.tag;

@Repository
@RequiredArgsConstructor
public class ArticleRepository {
    private final EntityManager entityManager;
    private final JPAQueryFactory jpaQueryFactory;

    public Optional<Article> findById(Long articleId) {
        return Optional.ofNullable(
            jpaQueryFactory
                .selectFrom(article)
                .leftJoin(article.articleTags, articleTag).fetchJoin()
                .leftJoin(articleTag.tag, tag).fetchJoin()
                .where(
                        article.isDeleted.eq(false),
                        article.articleId.eq(articleId)
                )
                .fetchOne());
    }

    public List<Article> search(String keyword, Pageable pageable) {
        return jpaQueryFactory
                .selectFrom(article)
                .leftJoin(article.articleTags, articleTag).fetchJoin()
                .leftJoin(articleTag.tag, tag).fetchJoin()
                .where(
                        article.isDeleted.eq(false),
                        validKeyword(keyword)
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(article.issueDate.desc())
                .fetch();
    }

    public Article save(Article article) {
        entityManager.persist(article);
        return article;
    }

    private BooleanExpression validKeyword(String keyword) {
        if (keyword.isBlank()) {
            return null;
        }
        return article.content.containsIgnoreCase(keyword);
    }
}
