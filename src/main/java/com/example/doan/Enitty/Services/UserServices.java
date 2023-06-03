package com.example.doan.Enitty.Services;

import com.example.doan.Enitty.Entity.User;
import com.example.doan.Enitty.Repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServices {
    @Autowired
    private IUserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        Optional<User> optional = userRepository.findById(id);
        return optional.orElse(null);
    }

    public User addUser(User user) {
        userRepository.save(user);
        return user;
    }

    public User editUser(User user) {
        userRepository.save(user);
        return user;
    }

    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }

    public boolean isUsernameTaken(String username) {
        User user = userRepository.findByUserName(username);
        return user != null;
    }



    public User loginUser(String username, String password) {
        User user = userRepository.findByUserName(username);
        if (user == null || !user.getMatKhau().equals(password)) {
            return null;
        } else {
            return user;
        }
    }
}
