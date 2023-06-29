package org.project.gongsamo.service;

import lombok.extern.slf4j.Slf4j;
import org.project.gongsamo.domain.TagSearch;
import org.project.gongsamo.dto.TagDto;
import org.project.gongsamo.repository.TagSearchRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@Transactional(readOnly = true)
public class TagService {
    private final TagSearchRepository tagSearchRepository;

    public TagService(TagSearchRepository tagSearchRepository) {
        this.tagSearchRepository = tagSearchRepository;
    }

    @Transactional
    public TagSearch createTag(TagDto tagDto) {
        TagSearch tagSearch = tagDto.toEntity();

        Long tagId = this.getNewId();
        tagSearch.setTagId(tagId);
        return tagSearchRepository.save(tagSearch);
    }

    public Optional<TagSearch> findTagByName(String name) {
        return tagSearchRepository.findByTagName(name);
    }

    public TagSearch findOrCreateTagByName(String name) {
        Optional<TagSearch> foundTag = this.findTagByName(name);
        if (foundTag.isPresent()) {
            return foundTag.get();
        } else {
            TagDto tagDto = TagDto.builder()
                    .name(name)
                    .build();
            return this.createTag(tagDto);
        }
    }

    private Long getNewId() {
        var first = this.tagSearchRepository.findFirstByOrderByTagIdDesc();
        return first.map(tagSearch -> tagSearch.getTagId() + 1).orElse(0L);
    }
}
