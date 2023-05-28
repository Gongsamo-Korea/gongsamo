package org.project.gongsamo.controller;

import org.project.gongsamo.domain.Article;
import org.project.gongsamo.dto.article.ArticleDetailDto;
import org.project.gongsamo.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/articles")
public class ArticleController {
    private final ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<ArticleDetailDto>> article(@PathVariable("id") Long id) {
        var article = articleService.getArticle(id).map(ArticleDetailDto::from);

        if (article.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(article);
    }

    @GetMapping
    public ResponseEntity<List<Article>> allArticles(Pageable pageable) {
        var articles = articleService.getAllArticles(pageable);
        return ResponseEntity.ok(articles);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Article>> searchedArticles(@RequestParam("keyword") String keyword, Pageable pageable) {
        var articles = articleService.searchArticles(keyword, pageable);
        return ResponseEntity.ok(articles);
    }

}
