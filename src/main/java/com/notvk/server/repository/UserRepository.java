package com.notvk.server.repository;

import com.notvk.server.model.UserInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UserInfo, Long> {
    Optional<UserInfo> getUserByUsername(String username);

    Optional<UserInfo> getUserById(long id);

    List<UserInfo> findByNameContainingIgnoreCase(String name);
}
