package com.cognizant.carrentalservice_20220831.carrentalservice_20220831.repository;

import com.cognizant.carrentalservice_20220831.carrentalservice_20220831.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository class.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    UserDetails findByUsername(String username);
}
