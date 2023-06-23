package org.project.gongsamo.repository;

import org.project.gongsamo.domain.ArticleSearch;

public interface ArticleSearchRepository extends ElasticsearchRepository<ArticleSearch, Long> {
}
