package com.cognizant.carrentalservice_20220831.carrentalservice_20220831.service;

import com.cognizant.carrentalservice_20220831.carrentalservice_20220831.model.User;
import com.cognizant.carrentalservice_20220831.carrentalservice_20220831.repository.UserRepository;
import com.sun.source.tree.ModuleTree;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;

@SpringBootTest
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userServiceImpl;

    private List<User> users;

    @BeforeEach
    void setUp() {
        users = new ArrayList<>();
        User firstUser = new User(51, "simona@gmail.com", "test", "dndn", "dana", "buca", "ro", 4723, "active", "ROLE_CUSTOMER");
        User secondUser = new User(63, "dana@gmail.com", "test", "dndn", "dana", "buca", "ro", 4723, "active", "ROLE_CUSTOMER");
        users.add(firstUser);
        users.add(secondUser);
    }

    @Test
    public void findUserByIdTest(){
        User u = new User(63, "dana@gmail.com", "test", "dndn", "dana", "buca", "ro", 4723, "active", "ROLE_CUSTOMER");

        Mockito.when(userRepository.findById(63)).thenReturn(Optional.of(u));
        Assert.assertEquals(userServiceImpl.findUserById(63), Optional.of(u));
    }

    @Test
    public void findUsers(){

       Mockito.when(userRepository.findAll()).thenReturn(users);
       Assert.assertEquals(2, userServiceImpl.findUsers().size());

    }

    @Test
    public void deleteUserById() {
      List<User>  users = new ArrayList<>();
      User firstUser = new User(51, "simona@gmail.com", "test", "dndn", "dana", "buca", "ro", 4723, "active", "ROLE_CUSTOMER");
      User secondUser = new User(63, "dana@gmail.com", "test", "dndn", "dana", "buca", "ro", 4723, "active", "ROLE_CUSTOMER");
      users.add(firstUser);
      users.add(secondUser);

      Mockito.when(userRepository.findById(51)).thenReturn(Optional.of(firstUser));
      Mockito.doNothing().when(userRepository).deleteById(51);
      userServiceImpl.deleteUserById(51);
      Mockito.verify(userRepository, times(1)).deleteById(51);

    }

    @Test
    public void saveUser(){
      User user = new User(63, "ddd@gmail.com", "test", "dndn", "dana", "buca", "ro", 4723, "active", "ROLE_CUSTOMER");
      Mockito.when(userRepository.findAll()).thenReturn(users);

      Mockito.when(userRepository.save(user)).thenReturn(user);
      userServiceImpl.saveUser(user);
      Mockito.verify(userRepository, times(1)).save(user);

    }






}