package org.project.gongsamo.controller;

import org.project.gongsamo.domain.Article;
import org.project.gongsamo.dto.article.ArticleResponseDto;
import org.project.gongsamo.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/api/articles")
public class ArticleController {
    private final ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Optional<Article>> article(@PathVariable("id") Long id) {
        var article = articleService.getArticle(id).map(ArticleResponseDto::from);

        if (article.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(articleService.getArticle(id));
    }

    @GetMapping("/")
    public ResponseEntity<Page<Article>> articles(Pageable pageable) {
        return ResponseEntity.ok(articleService.getAllArticles(pageable));
    }
}
