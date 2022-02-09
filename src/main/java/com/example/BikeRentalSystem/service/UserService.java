package com.example.BikeRentalSystem.service;
import com.example.BikeRentalSystem.entities.Users;
import com.example.BikeRentalSystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<Users> getAllUsers(){
        return this.userRepository.findAll();
    }

    public Users getUserById(int id){
        return this.userRepository.findById(id).get();
    }
    public Users getUserByEmail(String email){
        return this.userRepository.getUserByEmail(email);
    }
    public Users addUser(Users user){
       return this.userRepository.save(user);
    }
    public void deleteUser(int id){
        this.userRepository.deleteById(id);
    }
    public Users updateUser(Users user ,int id){
        user.setId(id);
        return userRepository.save(user);
    }
}
