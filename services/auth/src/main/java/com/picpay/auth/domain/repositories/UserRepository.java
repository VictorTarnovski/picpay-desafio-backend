package com.picpay.auth.domain.repositories;

import com.picpay.auth.domain.entities.User;
import com.picpay.auth.domain.ids.UserId;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, UserId> {
}
