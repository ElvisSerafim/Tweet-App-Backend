package org.acme.repository;

import com.surrealdb.driver.model.QueryResult;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.model.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ApplicationScoped
public class UserRepository extends AbstractRepository<User> {
    Map<String, String> mapData = new HashMap<>();

    public UserRepository() {
        super("users", User.class);
    }

    public User createUser(User user) {
        return create(user);
    }

    public List<User> getAllUsers() {
        return select();
    }

    public List<QueryResult<User>> getUserByEmail(String email)  {
        mapData.put("emailQuery", email);
        List<QueryResult<User>> userDataQuery =  query("SELECT * FROM users WHERE email = $emailQuery", mapData);
        mapData.clear();
        return userDataQuery;
    }

    public List<QueryResult<User>> updateUserPoints (String email, String points) {
        Map<String, String> mapData = new HashMap<>();
        mapData.put("email", email);
        mapData.put("points", points);
        List<QueryResult<User>> userDataQuery = query("UPDATE users set points = $points WHERE email = $email", mapData);
        mapData.clear();
        return userDataQuery;
    }
}