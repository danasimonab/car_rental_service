package com.cognizant.carrentalservice_20220831.carrentalservice_20220831.controllersTest;


import com.cognizant.carrentalservice_20220831.carrentalservice_20220831.controllers.UserController;
import com.cognizant.carrentalservice_20220831.carrentalservice_20220831.model.User;
import com.cognizant.carrentalservice_20220831.carrentalservice_20220831.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@SpringBootTest
public class UserControllerTest {

    @InjectMocks
    UserController userController;
    @Mock
    UserService userService;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        userController = new UserController(userService);

    }


    @Test
    public void should_GetAllRecords_when_CallGetAllMethod() {
        //given
        List<User> users = List.of(
                new User(6, "user6","password6","username6","firstname6","lastname6","country6",97865,"ACTIVE", "ROLE_ADMIN"),
                new User(6, "user6","password6","username6","firstname6","lastname6","country6",97865,"ACTIVE", "ROLE_ADMIN")
        );

        //when
        when(userService.findUsers()).thenReturn(users);

        ResponseEntity<List<User>> responseEntity = userController.getAll();

        //then
        assertNotNull(responseEntity);
        assertEquals(2, responseEntity.getBody().size());


    }

    @Test
    public void should_GetNullBody_when_CallGetAllMethod() {

        //when
        when(userService.findUsers()).thenReturn(null);

        ResponseEntity<List<User>> responseEntity = userController.getAll();

        //then
        assertNotNull(responseEntity);
        assertNull(responseEntity.getBody());

    }

    @Test
    public void should_GetUser_when_CallGetByIdMethod() {

        User user = new User(6, "user6","password6","username6","firstname6","lastname6","country6",97865,"ACTIVE","ROLE_ADMIN");

        when(userService.findUserById(user.getUser_id())).thenReturn(Optional.of(user));

        ResponseEntity<Optional<User>> userbyid = userController.getById(user.getUser_id());

        assertEquals(userbyid.getBody().get(),user);

    }

    @Test
    public void should_DeleteUser_when_CallDeleteMethod() {

        //given
        List<User> users = List.of(
                new User(6, "user6","password6","username6","firstname6","lastname6","country6",97865,"ACTIVE", "ROLE_ADMIN"),
                new User(7, "user6","password6","username6","firstname6","lastname6","country6",97865,"ACTIVE", "ROLE_ADMIN")
        );

        int user_id=6;

        //when
        when(userService.findUsers()).thenReturn(users);

        ResponseEntity<String> responseEntity = userController.deleteById(user_id);
        assertEquals("User with id:" + user_id + " was deleted!!!",responseEntity.getBody());

        
    }


}
