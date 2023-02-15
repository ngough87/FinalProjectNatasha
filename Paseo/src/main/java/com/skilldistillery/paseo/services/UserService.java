package com.skilldistillery.paseo.services;

import java.util.List;

import com.skilldistillery.paseo.entities.User;

public interface UserService {
public List <User> show();

public User update(User user, int id);

public boolean destroy(String username, int id);

User findById(int id);

public List<User> findUsersByMatchingPreferences(User loggedInUser);

public List<User> findByFirstNameLastNameOrUsername(String keyword);

}
