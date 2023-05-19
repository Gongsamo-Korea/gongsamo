package org.project.gongsamo.dto.article;

import lombok.Builder;
import lombok.Getter;
import org.project.gongsamo.domain.Article;

import java.time.LocalDateTime;

@Builder
@Getter
public class ArticleResponseDto {
    private long id;
    private int issueNumber;
    private String tableOfContent;
    private String title;
    private String thumbnailUrl;
    private String content;
    private long viewCount;
    private LocalDateTime issueDate;

    public static ArticleResponseDto from(Article article) {
        return ArticleResponseDto.builder()
                .id(article.getArticleId())
                .issueNumber(article.getIssueNumber())
                .tableOfContent(article.getTableOfContent())
                .title(article.getTitle())
                .thumbnailUrl(article.getThumbnailUrl())
                .content(article.getContent())
                .viewCount(article.getViewCount())
                .issueDate(article.getIssueDate())
                .build();
    }
}
