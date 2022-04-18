package com.automation.framework.data.repo.mongo.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@RefreshScope
public class MongoConfigProperties {

    @Value("${spring.data.mongodb.host:localhost}")
    private String host;

    @Value("${spring.data.mongodb.port:4646}")
    private int port;

    @Value("${spring.data.database.name:mongo}")
    private String dataBaseName;

    @Value("${mongo.connection.socket.timeout:50000000}")
    private Integer socketTimeout;

    @Value("${spring.data.mongodb.username:automation}")
    private String userName;

    @Value("${spring.data.mongodb.password:@utom@t!on@93}")
    private String password;

    @Value("${spring.data.mongodb.authentication-database:admin}")
    private String authDatabase;
}
