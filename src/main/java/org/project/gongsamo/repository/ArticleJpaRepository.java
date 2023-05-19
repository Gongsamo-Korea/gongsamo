package org.project.gongsamo.repository;

import org.project.gongsamo.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleJpaRepository extends JpaRepository<Article, Long>, ArticleRepository {
}
