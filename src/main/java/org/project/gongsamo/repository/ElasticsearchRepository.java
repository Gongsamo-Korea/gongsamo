package org.project.gongsamo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

@NoRepositoryBean
public interface ElasticsearchRepository<T, S> extends PagingAndSortingRepository<T, S>, CrudRepository<T, S> {
}