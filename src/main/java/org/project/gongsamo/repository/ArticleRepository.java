package org.project.gongsamo.repository;

import org.project.gongsamo.domain.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ArticleRepository {
    Article save(Article article);
    Optional<Article> findById(Long id);
    Page<Article> findAll(Pageable pageable);
}
