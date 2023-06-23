package org.project.gongsamo.repository;

import org.project.gongsamo.domain.TagSearch;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TagSearchRepository extends ElasticsearchRepository<TagSearch, Long>, CrudRepository<TagSearch, Long> {
    Optional<TagSearch> findByTagName(String name);
}
