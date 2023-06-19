package org.project.gongsamo.controller;

import org.project.gongsamo.dto.article.ArticleDetailDto;
import org.project.gongsamo.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/articles")
public class ArticleController {
    private final ArticleService articleService;
    private final int PAGE_SIZE = 9;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<ArticleDetailDto>> article(@PathVariable("id") Long id) {
        var article = articleService.findArticle(id).map(ArticleDetailDto::from);

        if (article.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(article);
    }

    @GetMapping
    public ResponseEntity<List<ArticleDetailDto>> articles(@RequestParam(defaultValue = "") String keyword, @PageableDefault(page = 1, size = PAGE_SIZE) Pageable pageable) {
        var articles = articleService.findArticles(keyword, pageable).stream().map(ArticleDetailDto::from).toList();
        return ResponseEntity.ok(articles);
    }
}
