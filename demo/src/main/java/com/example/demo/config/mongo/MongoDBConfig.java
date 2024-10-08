package com.example.demo.config.mongo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;

@Configuration
public class MongoDBConfig {

    @Value("${mongo.connectionString}")
    private String connectionString;

    @Value("${mongo.database}")
    private String database;

    @Bean
    public MongoDatabaseFactory mongoConfigure(){
        return new SimpleMongoClientDatabaseFactory(connectionString+database+"?authSource=admin");
    }

    @Bean
    public MongoTemplate mongoTemplate(){
        return new MongoTemplate(mongoConfigure());
    }
}
