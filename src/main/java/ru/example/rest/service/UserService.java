package ru.example.rest.service;


import ru.example.rest.entity.User;

import java.util.List;

public interface UserService {
    int save(User user);
    int update(User user);
    int delete(long id);
    List<User> findALL();
    User findById(long id);
}
