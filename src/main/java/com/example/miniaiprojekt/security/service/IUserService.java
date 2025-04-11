package com.example.miniaiprojekt.security.service;


import com.example.miniaiprojekt.security.model.User;

import java.util.List;

public interface IUserService extends ICrudService<User,Long>{
    List<User> findByName(String name);
}
