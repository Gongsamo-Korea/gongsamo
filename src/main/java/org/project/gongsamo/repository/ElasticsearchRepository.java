package org.project.gongsamo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import javax.management.openmbean.KeyAlreadyExistsException;

@NoRepositoryBean
public interface ElasticsearchRepository<T, S> extends PagingAndSortingRepository<T, S>, CrudRepository<T, S> {
    default Long getNewId() {
        return this.count();
    }

    default void validateId(S id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }

        var target = this.findById(id);
        if (target.isPresent()) {
            throw new KeyAlreadyExistsException("ID " + id + " is already exist");
        }
    }
}