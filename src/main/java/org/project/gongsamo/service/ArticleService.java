package org.project.gongsamo.service;

import org.project.gongsamo.domain.Article;
import org.project.gongsamo.repository.ArticleQueryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleService {
    private final ArticleQueryRepository articleRepository;

    @Autowired
    public ArticleService(ArticleQueryRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public Article createArticle(Article article) {
        return articleRepository.save(article);
    }

//    public Page<Article> searchArticles(String keyword, Pageable pageable) {
//        return articleRepository.search(keyword, pageable);
//    }

    public Optional<Article> getArticle(Long id) {
        return articleRepository.findById(id);
    }

    public List<Article> getAllArticles(Pageable pageable) {
        return articleRepository.search("", pageable);
    }
}
