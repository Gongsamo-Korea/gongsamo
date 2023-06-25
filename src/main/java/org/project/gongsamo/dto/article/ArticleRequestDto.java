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
    @JsonProperty("issueNumber")
    private String issueNumber;

    @JsonProperty("tableOfContent")
    private String tableOfContent;

    @JsonProperty("title")
    private String title;

    @JsonProperty("thumbnailUrl")
    private String thumbnailUrl;

    @JsonProperty("content")
    private String content;

    @JsonProperty("issueDate")
    private String issueDate;

    @JsonProperty("tagNames")
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
