package org.bianbian.tmservice.repository;

import org.bianbian.tmservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,String> {
	public User findByAccount(String account);
}
