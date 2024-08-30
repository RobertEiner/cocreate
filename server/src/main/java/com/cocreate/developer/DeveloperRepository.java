package com.cocreate.developer;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DeveloperRepository extends JpaRepository<Developer, Integer> {

    Optional<Developer> findByUserName(String userName);
}
