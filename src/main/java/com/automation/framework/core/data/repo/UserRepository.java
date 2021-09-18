package com.automation.framework.core.data.repo;

import com.automation.framework.core.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    List<User> findByFirstNameStartingWith(String startsWith);

}
