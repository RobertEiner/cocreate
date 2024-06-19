package com.cocreate.user;

import com.cocreate.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {


}


 /*private final JdbcClient jdbcClient;

    @Autowired
    public UserRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public List<User> findAllUsers() {
       //Map<String, Object> result = this.jdbcTemplate.queryForMap("select * from users");
       //System.out.println(result);
        return this.jdbcClient.sql("SELECT * FROM user").query(User.class).list();
    }

    // Create a new user
    public int createUser(User newUSer) {
        var updated = jdbcClient.sql("INSERT INTO user(userName, emailAddress, preferredLanguage) values(?, ?, ?)")
                .params(List.of(newUSer.getUserName(), newUSer.getEmailAddress(), newUSer.getPreferredLanguage()))
                .update();
        return updated;
        /*Assert.state(updated == 1, "Failed to create user" + newUSer.getUserName());
        System.out.println(updated);

    }

   public Optional<User> findUserById(int id) {
       return jdbcClient.sql("SELECT * FROM user WHERE userId = :id")
               .param("id", id)
               .query(User.class)
               .optional();
   }

   public void deleteUserById(int id) {
       var updated = jdbcClient.sql("DELETE FROM user WHERE userId = :id")
               .param("id", id)
               .update(); //update here because we are not simply querying
       Assert.state(updated == 1, "Failed to delete user with id: " + id);
   }

   public void updateUser(int id, User user) {
        jdbcClient.sql("UPDATE user SET userName = ?, emailAddress = ?, preferredLanguage = ? WHERE userId = ?")
                .param("userName", user.getUserName())
                .param("emailAddress", user.getEmailAddress())
                .param("preferredLanguage", user.getPreferredLanguage())
                .param("id", id)
                .update();
   }

*/

