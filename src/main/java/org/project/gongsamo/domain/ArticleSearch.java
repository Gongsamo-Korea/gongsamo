package org.project.gongsamo.domain;

import lombok.*;
import org.project.gongsamo.util.Formatable;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Document(indexName = "articles")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString
public class ArticleSearch {
    /**
     * Id로 명시된 필드의 경우 자동으로 Keyword 타입이 되므로 더미 필드를 추가한다.
     * JPA CrudRepository를 사용하지 않도록 주의한다.
     */
    @Id
    @ToString.Exclude
    private String id;

    @Field(type = FieldType.Long)
    @Setter
    private Long articleId = 0L;

    @Field(type = FieldType.Keyword)
    private String issueNumber = "";

    @Field(type = FieldType.Keyword)
    private String tableOfContent = "";

    @Field(type = FieldType.Text)
    private String title = "";

    @Field(type = FieldType.Keyword, index = false)
    private String thumbnailUrl = null;

    @Field(type = FieldType.Text)
    @ToString.Exclude
    private String content = "";

    @Field(type = FieldType.Long)
    @ToString.Exclude
    private Long viewCount = 0L;

    @Field(type = FieldType.Nested)
    @Setter
    private List<TagSearch> tags = new ArrayList<>();

    @Field(type = FieldType.Date, format = {}, pattern = Formatable.datetimeFormat)
    @ToString.Exclude
    private LocalDateTime deleteDate = null;

    @Field(type = FieldType.Date, format = {}, pattern = Formatable.datetimeFormat)
    private LocalDateTime issueDate = null;

    @Field(type = FieldType.Date, format = {}, pattern = Formatable.datetimeFormat)
    @ToString.Exclude
    private LocalDateTime registerDate = LocalDateTime.now();
}
