package org.project.gongsamo.dto.tag;

import lombok.*;
import org.project.gongsamo.domain.Tag;

@Builder
@Getter
@AllArgsConstructor
public class TagDto {
    private long id;
    private String name;

    static public TagDto from(Tag tag) {
        return TagDto.builder()
                .id(tag.getTagId())
                .name(tag.getTagName())
                .build();
    }
}
