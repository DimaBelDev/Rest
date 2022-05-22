package ru.example.rest.repository;


import org.springframework.jdbc.core.RowMapper;
import ru.example.rest.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;


public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setId(rs.getLong("id"));
        user.setFirstname(rs.getString("firstname"));
        user.setLastname(rs.getString("lastname"));
        user.setData(rs.getString("data"));
        user.setParty(rs.getInt("party"));
        return user;
    }
}
