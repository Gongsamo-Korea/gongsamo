package org.project.gongsamo.dto.article;

import lombok.*;
import org.project.gongsamo.domain.Article;
import org.project.gongsamo.dto.tag.TagDto;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
@AllArgsConstructor
public class ArticleDetailDto {
    private long id;
    private String issueNumber;
    private String tableOfContent;
    private String title;
    private String thumbnailUrl;
    private String content;
    private long viewCount;
    private LocalDateTime issueDate;
    private List<TagDto> tags;

    public static ArticleDetailDto from(Article article) {
        return ArticleDetailDto.builder()
                .id(article.getArticleId())
                .issueNumber(article.getIssueNumber())
                .tableOfContent(article.getTableOfContent())
                .title(article.getTitle())
                .thumbnailUrl(article.getThumbnailUrl())
                .content(article.getContent())
                .viewCount(article.getViewCount())
                .issueDate(article.getIssueDate())
                .tags(article.getTags().stream().map(TagDto::from).toList())
                .build();
    }
}
