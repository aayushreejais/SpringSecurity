package com.springSecurity.security.controller;

import com.springSecurity.security.model.User;
import com.springSecurity.security.service.UserService;
import java.security.Principal;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
@Slf4j
public class UserController {

  @Autowired
  private UserService userService;

  @PreAuthorize("hasRole('USER')")
  @GetMapping("/users")
  public List<User> getUsers() {
    log.info("fetching user list");
    return userService.getUsers();
  }

  //principal is the subject/object which keeps the track of the current logged in user
  @GetMapping("/loggedInUser")
  public String getLoggedInUser(Principal principal) {
    return principal.getName();
  }

}

//Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhYXl1c2hyZWUiLCJleHAiOjE3MTA1ODc0NTcsImlhdCI6MTcxMDU3NTQ1N30.S28YRUsmzS_lSDEP6fxy4JDrtLg8qtenoqMxGQxzrRDlGnGTZN8WWeVvhZDMP3t-b9tvCczDcB04Zxg_gqWy3g