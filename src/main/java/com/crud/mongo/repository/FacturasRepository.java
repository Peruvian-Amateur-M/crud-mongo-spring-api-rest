package com.crud.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.crud.mongo.documents.Facturas;

@Repository
public interface FacturasRepository extends MongoRepository<Facturas, Long>{

}
