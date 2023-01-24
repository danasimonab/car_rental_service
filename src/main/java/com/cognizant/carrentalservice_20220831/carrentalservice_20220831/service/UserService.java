package com.cognizant.carrentalservice_20220831.carrentalservice_20220831.service;

import com.cognizant.carrentalservice_20220831.carrentalservice_20220831.model.User;

import java.util.List;
import java.util.Optional;

/**
 * Service interface.
 */
public interface UserService {

    /**
     * Save user.
     *
     * @param user The user.
     */
    void saveUser(User user);

    /**
     * Find users.
     *
     * @return all users.
     */
    List<User> findUsers();

    /**
     * Find by id.
     *
     * @param user_id User id.
     * @return The user.
     */
    Optional<User> findUserById(int user_id);

    /**
     * Delete by id.
     *
     * @param user_id User id.
     */
    void deleteUserById(int user_id);


}
