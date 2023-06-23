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
    @Id
    @Field(type = FieldType.Long)
    @Setter
    private Long tagId;

    @Field(type = FieldType.Keyword)
    private String tagName;
}
