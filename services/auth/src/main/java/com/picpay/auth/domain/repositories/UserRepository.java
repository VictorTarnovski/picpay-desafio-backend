package com.picpay.auth.domain.repositories;

import com.picpay.auth.domain.entities.User;
import com.picpay.auth.domain.ids.UserId;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, UserId> {
    @Query(value = "SELECT nextval('auth.users_id_seq')", nativeQuery = true)
    UserId nextId();
}
