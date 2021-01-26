//package com.example.pawel.expense;
//
//import com.example.pawel.expense.model.User;
//import com.example.pawel.expense.repository.UserRepository;
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
//import org.springframework.test.annotation.Rollback;
//
//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//
//@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@Rollback(false)
//public class UserTests {
//
//    private UserRepository userRepository;
//    private TestEntityManager entityManager;
//
//    @Test
//    public void testCreateUser() {
//        User user = new User(); // problem
//        user.setName("Pawel");
//        user.setPassword("strong");
//        user.setEmail("pnagorz@onet.pl");
//
//        User savedUser = userRepository.save(user);
//        User existUser = entityManager.find(User.class, savedUser.getId());
//        assertThat(user.getEmail()).isEqualTo(existUser.getEmail());
//    }
//
//}
