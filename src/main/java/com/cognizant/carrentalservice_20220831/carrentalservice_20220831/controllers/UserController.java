package com.cognizant.carrentalservice_20220831.carrentalservice_20220831.controllers;


import com.cognizant.carrentalservice_20220831.carrentalservice_20220831.model.AuthenticationRequest;
import com.cognizant.carrentalservice_20220831.carrentalservice_20220831.model.RequestResponse;
import com.cognizant.carrentalservice_20220831.carrentalservice_20220831.model.User;
import com.cognizant.carrentalservice_20220831.carrentalservice_20220831.service.MyUserDetailsService;
import com.cognizant.carrentalservice_20220831.carrentalservice_20220831.service.UserService;
import com.cognizant.carrentalservice_20220831.carrentalservice_20220831.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;
import java.util.Optional;

/***
 * Controller class.
 */
@RestController
@CrossOrigin
//@RequestMapping("/user")
public class UserController {

    //@Autowired
    //  private final UserRepository userRepository;

    //public UserController(UserRepository userRepository) {
    //  this.userRepository = userRepository;
    //}
    @Autowired
    private final UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private MyUserDetailsService userDetailsService;
    @Autowired
    private JwtUtil jwtUtil;

    /**
     * ArgsConstructor
     *
     * @param userService
     */
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @RequestMapping(value = "/authenticate", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> generateToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        final Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
        } catch (BadCredentialsException ex) {
            throw new Exception("Incorrect username/password", ex);
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String jwt = jwtUtil.generateToken(authentication);

        return ResponseEntity.ok(new RequestResponse(jwt));
    }


    /**
     * Save method.
     *
     * @param user
     * @return ResponseEntity.
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> addUser(@RequestBody User user) {
        user.setRole("ROLE_CUSTOMER");
        userService.saveUser(user);
        //   userRepository.saveUser(user);
        return ResponseEntity.ok(user);
    }

    /**
     * Get all users method.
     *
     * @return A list of users.
     */

    // @PreAuthorize("hasRole('Manager')")
    ///@RolesAllowed("ROLE_ADMIN")
    @RequestMapping(value = "/find", method = RequestMethod.GET)
    public ResponseEntity<List<User>> getAll() {
        //List<User> users = userRepository.findAll();
        List<User> users = userService.findUsers();
        return ResponseEntity.ok(users);
    }

    /**
     * Get by id method.
     *
     * @param user_id The user id.
     * @return An user.
     */

    @RolesAllowed("ROLE_ADMIN")
    @RequestMapping(value = "/findById/{user_id}", method = RequestMethod.GET)
    public ResponseEntity<Optional<User>> getById(@PathVariable int user_id) {
        //List<User> users = userRepository.findAll();
        Optional<User> user = userService.findUserById(user_id);
        return ResponseEntity.ok(user);
    }

    /**
     * Delete method.
     *
     * @param user_id The user id.
     * @return The deleted user.
     */
   // @RolesAllowed({"ROLE_MANAGER", "ROLE_CUSTOMER", "ROLE_ADMIN"})
    @RequestMapping(value = "/deleteById/{user_id}", method = RequestMethod.DELETE)
    // @RolesAllowed("Customer")
    public ResponseEntity<String> deleteById(@PathVariable int user_id) {

        List<User> users = userService.findUsers();
        User user = users.stream().filter(u-> u.getUser_id()==user_id).findFirst().orElse(null);


   //     User user = userService.findUsers().get(user_id);
     //   if (user.getRole().equals("ROLE_MANAGER") || user.getRole().equals("ROLE_CUSTOMER")) {

            userService.deleteUserById(user_id);
       // }
        return ResponseEntity.ok("User with id:" + user_id + " was deleted!!!");
    }

    /**
     * Update method
     *
     * @param user
     * @return ResponseEntity.
     */
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public ResponseEntity<?> updateById(@RequestBody User user) {
        user.setRole("ROLE_CUSTOMER");//should be set on client side?
        userService.saveUser(user);
        return ResponseEntity.ok(user);

    }

}
