package com.example.BikeRentalSystem.Repository;
import com.example.BikeRentalSystem.entities.Users;
import com.example.BikeRentalSystem.repository.UserRepository;
import com.example.BikeRentalSystem.service.SequenceGeneratoryService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SequenceGeneratoryService sequenceGeneratoryService;

    @Test
    void getUserByEmail() {

        Users user=new Users();
        user.setId(sequenceGeneratoryService.getSequenceNumber(Users.SEQUENCE_NAME));
        user.setFirstName("testFname");
        user.setLastName("testLname");
        user.setEmail("test@gmail.com");
        user.setRole("ROLE_USER");
        userRepository.save(user);
        Users addedUser=userRepository.getUserByEmail("test@gmail.com");
        assertThat(addedUser.getEmail()).isEqualTo("test@gmail.com");
        assertThat(addedUser.getFirstName()).isEqualTo("testFname");
        assertThat(addedUser.getLastName()).isEqualTo("testLname");
        assertThat(addedUser.getRole()).isEqualTo("ROLE_USER");
        userRepository.deleteById(user.getId());
    }

    @AfterEach
    void tearDown(){
        System.out.println("tearing Down");
    }
    @BeforeEach
    void tearUp(){
        System.out.println("tearing Up");
    }

}