package com.nvoip.market.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nvoip.market.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
