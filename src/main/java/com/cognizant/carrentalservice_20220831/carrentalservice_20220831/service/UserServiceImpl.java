package com.cognizant.carrentalservice_20220831.carrentalservice_20220831.service;

import com.cognizant.carrentalservice_20220831.carrentalservice_20220831.model.User;
import com.cognizant.carrentalservice_20220831.carrentalservice_20220831.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service implementation.
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    /**
     * ArgsConstructor.
     *
     * @param userRepository
     */
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public List<User> findUsers() {
        List<User> users = userRepository.findAll();
        return users;
    }

    @Override
    public Optional<User> findUserById(int user_id) {
        return userRepository.findById(user_id);
    }

    @Override
    public void deleteUserById(int user_id) {
        userRepository.deleteById(user_id);

    }
}
