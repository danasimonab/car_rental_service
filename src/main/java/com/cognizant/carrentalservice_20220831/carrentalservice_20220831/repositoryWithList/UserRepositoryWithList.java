package com.cognizant.carrentalservice_20220831.carrentalservice_20220831.repositoryWithList;

import com.cognizant.carrentalservice_20220831.carrentalservice_20220831.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepositoryWithList {

    private static List<User> users = new ArrayList<>();

    public void saveUser(User user) {
        users.add(user);
    }

    public List<User> findAllSecond() {
        return users;
    }

}
