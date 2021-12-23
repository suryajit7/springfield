package com.automation.framework.data.repo;

import com.automation.framework.data.entity.app.ems.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    List<User> findByFirstNameStartingWith(String startsWith);

}
