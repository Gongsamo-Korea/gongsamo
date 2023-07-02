package org.project.gongsamo.repository;

import org.project.gongsamo.domain.ArticleSearch;

import java.util.Optional;

public interface ArticleSearchRepository extends ElasticsearchRepository<ArticleSearch, Long> {
    Optional<ArticleSearch> findByArticleId(Long id);
    Optional<ArticleSearch> findFirstByOrderByArticleIdDesc();
}