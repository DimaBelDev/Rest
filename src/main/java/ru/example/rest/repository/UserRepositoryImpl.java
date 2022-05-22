package ru.example.rest.repository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.example.rest.entity.User;

import java.util.List;


@Repository
public class UserRepositoryImpl implements UserRepository{

    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public UserRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(User user) {
        return jdbcTemplate.update("INSERT INTO users(firstname, lastname, data, party) values(?, ?, ?, ?)",
                user.getFirstname(), user.getLastname(), user.getData(), user.getParty());
    }

    @Override
    public int update(User user) {
        return jdbcTemplate.update("UPDATE users SET firstname=?, lastname=?, data=?, party=? WHERE id=?",
                new Object[] { user.getFirstname(), user.getLastname(), user.getData(), user.getParty(), user.getId() });
    }

    @Override
    public int delete(long id) {
        return jdbcTemplate.update("DELETE FROM users WHERE id=?", id);
    }

    @Override
    public List<User> findALL() {
        return jdbcTemplate.query("SELECT * from users", new UserRowMapper());
    }

    @Override
    public User findById(long id) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM users WHERE id=?",
                    new UserRowMapper(), id);
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }
}
