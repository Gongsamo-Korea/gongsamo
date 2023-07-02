package org.project.gongsamo.dto.article;

import lombok.*;
import org.project.gongsamo.domain.ArticleSearch;
import org.project.gongsamo.dto.TagDto;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
@AllArgsConstructor
public class ArticleResponseDto {
    private Long id;
    private String issueNumber;
    private String tableOfContent;
    private String title;
    private String thumbnailUrl;
    private Long viewCount;
    private LocalDateTime issueDate;
    private List<TagDto> tags;

    public static ArticleResponseDto from(ArticleSearch articleSearch) {
        return ArticleResponseDto.builder()
                .id(articleSearch.getArticleId())
                .issueNumber(articleSearch.getIssueNumber())
                .tableOfContent(articleSearch.getTableOfContent())
                .title(articleSearch.getTitle())
                .thumbnailUrl(articleSearch.getThumbnailUrl())
                .viewCount(articleSearch.getViewCount())
                .issueDate(articleSearch.getIssueDate())
                .tags(articleSearch.getTags().stream().map(TagDto::from).toList())
                .build();
    }
}
