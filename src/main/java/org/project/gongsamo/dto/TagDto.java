package org.project.gongsamo.dto;

import lombok.*;
import org.project.gongsamo.domain.TagSearch;

@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TagDto {
    private Long id;
    private String name;

    public static TagDto from(TagSearch tagSearch) {
        return TagDto.builder()
                .id(tagSearch.getTagId())
                .name(tagSearch.getTagName())
                .build();
    }

    public TagSearch toEntity() {
        return TagSearch.builder()
                .tagName(this.name)
                .build();
    }
}
