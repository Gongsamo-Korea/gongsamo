package org.project.gongsamo.service;

import org.project.gongsamo.domain.Article;
import org.project.gongsamo.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ArticleService {
    private final ArticleRepository articleRepository;

    @Autowired
    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public Article createArticle(Article article) {
        return articleRepository.save(article);
    }

    public Optional<Article> getArticle(Long id) {
        return articleRepository.findById(id);
    }

    public Page<Article> getAllArticles(Pageable pageable) {
        return articleRepository.findAll(pageable);
    }
}
