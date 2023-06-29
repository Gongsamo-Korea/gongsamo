package org.project.gongsamo.dto.article;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.project.gongsamo.domain.ArticleSearch;
import org.project.gongsamo.util.Formatable;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
@ToString
public class ArticleRequestDto extends Formatable {
    @JsonProperty("issue_number")
    private String issueNumber;

    @JsonProperty("table_of_content")
    private String tableOfContent;

    @JsonProperty("title")
    private String title;

    @JsonProperty("thumbnail_url")
    private String thumbnailUrl;

    @JsonProperty("content")
    private String content;

    @JsonProperty("issue_date")
    private String issueDate;

    @JsonProperty("tag_names")
    private List<String> tagNames = new ArrayList<>();

    public ArticleSearch toEntity() {
        return ArticleSearch.builder()
                .issueNumber(this.issueNumber)
                .tableOfContent(this.tableOfContent)
                .title(this.title)
                .thumbnailUrl(this.thumbnailUrl)
                .content(this.content)
                .issueDate(this.datetime(issueDate))
                .build();
    }
}
