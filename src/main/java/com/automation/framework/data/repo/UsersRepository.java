package com.automation.framework.data.repo;

import com.automation.framework.data.entity.ems.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {

    List<Users> findByFirstNameStartingWith(String startsWith);

}
