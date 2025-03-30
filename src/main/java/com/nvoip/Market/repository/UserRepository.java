package com.nvoip.Market.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nvoip.Market.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
