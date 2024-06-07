package com.cocreate.repositories;
import com.cocreate.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;
import java.util.List;

@Repository
public class UserRepository{

    private final JdbcClient jdbcClient;

    @Autowired
    public UserRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public List<User> getUsers() {
       //Map<String, Object> result = this.jdbcTemplate.queryForMap("select * from users");
       //System.out.println(result);
        return this.jdbcClient.sql("SELECT * FROM users").query(User.class).list();
    }

    // Create a new user
    public void createUser(User newUSer) {
        var updated = jdbcClient.sql("INSERT INTO users(userName, emailAddress, preferredLanguage) values(?, ?, ?)")
                .params(List.of(newUSer.getUserName(), newUSer.getEmailAddress(), newUSer.getPreferredLanguage()))
                .update();

        Assert.state(updated == 1, "Failed to create user" + newUSer.getUserName());
        System.out.println(updated);
    }



    /*
    public void createUser(User newUser) {
        String query = "INSERT INTO users (username, emailAddress, preferredLanguage) VALUES (?, ?, ?)";
        this.jdbcTemplate.update(query, newUser.getUserName(), newUser.getEmailAddress(), newUser.getPreferredLanguage());
    }*/


    /*
    public void getUser(int id) {
        Map<String, Object> user = this.jdbcTemplate.queryForMap("SELECT * FROM users WHERE userId = ?", id);
        System.out.println(user);
    }

*/

}
