package com.vttai.Identify.service.repository;

import com.vttai.Identify.service.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository  extends JpaRepository<User,String> {
    boolean existsByUsername(String username);
}
