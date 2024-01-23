package com.oxuegen.user_query_api.repositories;

import com.oxuegen.user_core.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface UserRepository extends MongoRepository<User, String> {
    @Query("{'$or' : [{'firstName': {$regex : ?0, $options: 'i'}}, {'lastName': {$regex : ?0, $options: 'i'}}, {'emailAddress': {$regex : ?0, $options: 'i'}}, {'account.userName': {$regex : ?0, $options: 'i'}}] }")
    List<User> findByFilterRegex(String filter);
}
