package org.project.gongsamo.controller;

import org.project.gongsamo.domain.Article;
import org.project.gongsamo.dto.article.ArticleDetailDto;
import org.project.gongsamo.dto.article.ArticleFormDto;
import org.project.gongsamo.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/articles")
public class ArticleController {
    private final ArticleService articleService;
    private final int PAGE_SIZE = 9;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

//    @PostMapping
//    public ResponseEntity<Article> createArticle(@RequestBody ArticleFormDto article) {
//        var createdArticle = articleService.createArticle(ArticleFormDto::toEntity(article));
//        return ResponseEntity.ok(createdArticle);
//    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<ArticleDetailDto>> article(@PathVariable("id") Long id) {
        var article = articleService.getArticle(id).map(ArticleDetailDto::from);

        if (article.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(article);
    }

    @GetMapping
    public ResponseEntity<List<ArticleDetailDto>> allArticles(@PageableDefault(size = PAGE_SIZE) Pageable pageable) {
        var articles = articleService.getAllArticles(pageable).stream().map(ArticleDetailDto::from).toList();
        return ResponseEntity.ok(articles);
    }

    @GetMapping("/search")
    public ResponseEntity<List<ArticleDetailDto>> searchedArticles(@RequestParam("keyword") String keyword, @PageableDefault(size = PAGE_SIZE) Pageable pageable) {
        var articles = articleService.searchArticles(keyword, pageable).stream().map(ArticleDetailDto::from).toList();
        return ResponseEntity.ok(articles);
    }

}
