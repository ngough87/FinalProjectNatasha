package com.skilldistillery.paseo.services;

import com.skilldistillery.paseo.entities.User;

public interface UserService {
public User show(String username, int id);

public User update(String username, User user, int id);

public boolean destroy(String username, int id);

}
