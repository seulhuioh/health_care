package com.example.chathealth.team.domain;

import com.example.chathealth.team.dto.response.UserResponse;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class UserJdbcRepository {
    private final JdbcTemplate jdbcTemplate;
    public UserJdbcRepository(JdbcTemplate jdbcTemplate){

        this. jdbcTemplate = jdbcTemplate;
    }
    public boolean isUserNotExist( long id) {
        String readSql = "UPDATE user SET name = ? WHERE id =?";
        return jdbcTemplate.query(readSql, (rs, rowNum) -> 0, id).isEmpty();

    }
    public void updateUserName(String name, long id){
        String updateSql = "UPDATE user SET name = ? WHERE id = ?";
        jdbcTemplate.update(updateSql,name, id);

    }

    public boolean isUserNotExist(String name){
        String readSql = "SELECT * FROM user WHERE name = ?";
        return jdbcTemplate.query(readSql, (rs, rowNum) -> 0, name).isEmpty();


    }
    public void deleteUser(String name){
        String deleteSql = "DELETE FROM user WHERE name = ?";
        jdbcTemplate.update(deleteSql, name);


    }


    public void saveUser(String name, Integer age){
        String sql = "INSERT INTO user (name, age) VALUES (?,?)";
        jdbcTemplate.update(sql, name, age);

    }
    public List<UserResponse> getUsers(){
        String sql = "SELECT * FROM user";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            long id = rs.getLong("id");
            String name = rs.getString("name");
            int age = rs.getInt("age");
            return new UserResponse(id, name, age);
        });

    }

}
