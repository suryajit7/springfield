package com.automation.framework.data.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface BaseMongoRepository<T,ID extends Serializable> extends MongoRepository<T,ID> {
    Page<T> find(Criteria searchCriteria, Pageable pageable);

    Class<T> getEntityType();
}
