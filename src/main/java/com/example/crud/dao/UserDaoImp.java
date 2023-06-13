package com.example.crud.dao;

import com.example.crud.models.User;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;
@Repository
@Transactional
public class UserDaoImp implements UserDao{
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public ResponseEntity<String> deleteUser(int id) {
        User user = entityManager.find(User.class, id);
        if(user != null) {
            entityManager.remove(user);
            return ResponseEntity.ok("user deleted");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not exists");
    }
    @Override
    public void createUser(User user) {
        user.setPassword(this.hashPassword(user.getPassword()));
        entityManager.merge(user);
    }
    public boolean verifyPassword(String hash, String password) {
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2i);
        return argon2.verify(hash, password);
    }
    public String hashPassword(String password) {
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2i);
        return argon2.hash(1,1024,1,password);
    }
    @Override
    public void editUser(User user) {
        String query = "from User where email = :email";
        List<User> users = entityManager.createQuery(query).setParameter("email", user.getMail()).getResultList();
        if(!users.isEmpty()) {
            User userExist = users.get(0);
            userExist.setName(user.getName());
            userExist.setMail(user.getMail());
            userExist.setLastname(user.getLastname());
            userExist.setPassword(this.hashPassword(user.getPassword()));
            entityManager.merge(userExist);
        }
    }

    @Override
    public boolean login(User user) {
        String query = "from User where email = :email";
        List<User> users = entityManager.createQuery(query).setParameter("email", user.getMail()).getResultList();
        return this.verifyPassword(users.get(1).getPassword(), user.getPassword());
    }

    @Override
    public User getUser(int id) {
        return entityManager.find(User.class, id);
    }
    @Override
    public List<User> getUsers() {
        String query = "from User";
        List<User> users = entityManager.createQuery(query).getResultList();
        if (users.isEmpty()) {
            return new ArrayList<>();
        }
        return users;
    }
}
