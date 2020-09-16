package com.zlab.commune.api.user.dao;

import com.zlab.commune.api.user.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, Long>{

    UserEntity findByEmail(String email);

    UserEntity findByUserId(String userId);
}
