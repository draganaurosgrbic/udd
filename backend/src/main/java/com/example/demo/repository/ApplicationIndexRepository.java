package com.example.demo.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.ApplicationIndexUnit;

@Repository
public interface ApplicationIndexRepository extends ElasticsearchRepository<ApplicationIndexUnit, String> {

}
