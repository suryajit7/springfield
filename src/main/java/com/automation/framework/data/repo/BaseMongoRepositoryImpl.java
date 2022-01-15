package com.automation.framework.data.repo;

import com.automation.framework.data.repo.mongo.config.MongoConfigProperties;
import com.automation.framework.util.AppContextProvider;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.query.MongoEntityInformation;
import org.springframework.data.mongodb.repository.support.SimpleMongoRepository;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class BaseMongoRepositoryImpl<T, ID extends Serializable> extends SimpleMongoRepository<T, ID> implements BaseMongoRepository<T, ID> {

    private final MongoEntityInformation<T, ID> metadata;
    private final MongoTemplate mongoTemplate;
    private MongoConfigProperties mongoConfigProperties;

    public BaseMongoRepositoryImpl(MongoEntityInformation<T, ID> metadata, MongoTemplate mongoTemplate) {
        super(metadata, mongoTemplate);
        this.metadata = metadata;
        this.mongoTemplate = mongoTemplate;
    }


    @Override
    public Page<T> find(Criteria criteria, Pageable pageable) {
        Query query = criteria != null ? new Query(criteria) : new Query();
        query.with(pageable);
        if (getMongoConfigProperties().getSocketTimeout() > 0) {
            query.maxTime(getMongoConfigProperties().getSocketTimeout(), TimeUnit.MILLISECONDS);
        }

        long totalCount = mongoTemplate.count(query, metadata.getJavaType());
        List<T> results = mongoTemplate.find(query, metadata.getJavaType());

        return new PageImpl<>(results, pageable, totalCount);

    }

    @Override
    public Class<T> getEntityType() {
        return metadata.getJavaType();
    }

    private MongoConfigProperties getMongoConfigProperties() {
        if (mongoConfigProperties == null) {
            mongoConfigProperties = AppContextProvider.getFirstBeanOfType(MongoConfigProperties.class);
        }
        return mongoConfigProperties;
    }
}

