package org.project.gongsamo.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.project.gongsamo.dto.article.ArticleResponseDto;
import org.project.gongsamo.dto.article.ArticleRequestDto;
import org.project.gongsamo.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/articles")
@Tag(name = "article", description = "뉴스레터")
public class ArticleController {
    private final ArticleService articleService;
    private final int PAGE_SIZE = 9;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ArticleResponseDto> create(@RequestBody ArticleRequestDto articleRequestDto) {
        System.out.println("articleRequestDto = " + articleRequestDto);
        var article = articleService.createArticle(articleRequestDto);
        return ResponseEntity.ok(ArticleResponseDto.from(article));
    }

    @GetMapping("/{id}")
    @Operation(
        summary = "뉴스레터 상세 조회",
        responses = {
            @ApiResponse(responseCode = "200", description = "성공", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ArticleResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 뉴스레터")
        }
    )
    public ResponseEntity<Optional<ArticleResponseDto>> article(@PathVariable("id") Long id) {
        var article = articleService.findArticle(id).map(ArticleResponseDto::from);

        if (article.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(article);
    }

    @GetMapping
    @Operation(
        summary = "뉴스레터 목록 조회",
        description = "뉴스레터 목록을 검색어로 필터링하여 조회합니다. 아무것도 입력하지 않으면 전체 목록을 발행 호수 기준 내림차순으로 정렬합니다.",
        parameters = {
            @Parameter(name = "keyword", description = "검색어", required = false),
            @Parameter(name = "pageable", description = "페이지네이션 정보", required = false),
            @Parameter(name = "page", description = "페이지 번호", schema = @Schema(defaultValue = "0")),
            @Parameter(name = "size", description = "페이지 크기", schema = @Schema(defaultValue = "9")),
            @Parameter(name = "sort", description = "정렬 기준", schema = @Schema(type = "string[]")),
            @Parameter(name = "direction", description = "정렬 방향", schema = @Schema(defaultValue = "ASC")),
        },
        responses = {
            @ApiResponse(responseCode = "200", description = "성공", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ArticleResponseDto.class)))
        }
    )
    public ResponseEntity<List<ArticleResponseDto>> articles(
        @RequestParam(defaultValue = "") String keyword,
        @PageableDefault(size = PAGE_SIZE) Pageable pageable
    ) {
        var articles = articleService.searchArticles(keyword, pageable).stream().map(ArticleResponseDto::from).toList();
        return ResponseEntity.ok(articles);
    }
}
