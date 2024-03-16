package com.springSecurity.security.service;

import com.springSecurity.security.model.User;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  private List<User> users = new ArrayList<>();
  public UserService(){
    users.add(new User(1,"aayu","a@gmail.com"));
    users.add(new User(2,"shree","bob@gmail.com"));
  }
  public List<User> getUsers(){
    return users;
  }
}
