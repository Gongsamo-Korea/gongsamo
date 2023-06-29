package org.project.gongsamo.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.*;

@Document(indexName = "tags")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TagSearch {
    /**
     * Id로 명시된 필드의 경우 자동으로 Keyword 타입이 되므로 더미 필드를 추가한다.
     */
    @Id
    @ToString.Exclude
    private String id;

    @Field(type = FieldType.Long)
    @Setter
    private Long tagId = 0L;

    @Field(type = FieldType.Keyword)
    private String tagName;
}
