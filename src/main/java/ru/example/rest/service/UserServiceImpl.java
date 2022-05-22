package ru.example.rest.service;

;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.example.rest.entity.User;
import ru.example.rest.repository.UserRepository;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public int save(User user) {
        return userRepository.save(user);
    }
    @Override
    public int update(User user) {
        return userRepository.update(user);
    }
    @Override
    public int delete(long id) {
        return userRepository.delete(id);
    }
    @Override
    public List<User> findALL() {
        return userRepository.findALL();
    }
    @Override
    public User findById(long id) {
        return userRepository.findById(id);
    }
}
