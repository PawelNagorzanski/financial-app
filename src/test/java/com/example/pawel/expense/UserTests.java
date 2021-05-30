//package com.example.pawel.expense;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//import com.example.pawel.expense.model.User;
//import com.example.pawel.expense.repository.UserRepository;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
//import org.springframework.test.annotation.Rollback;
//
//@DataJpaTest
//@AutoConfigureTestDatabase(replace = Replace.NONE)
//@Rollback(false)
//class UserRepositoryTests {
//
//    @Autowired
//    private TestEntityManager entityManager;
//
//    @Autowired
//    private UserRepository repo;
//
//    @Test
//    public void testCreateUser() {
//        User user = new User();
//        user.setEmail("pmagorz@gmail.com");
//        user.setPassword("123");
//        user.setName("Pawel");
//        user.setLastName("Nago");
//
//        User savedUser = repo.save(user);
//
//        User existUser = entityManager.find(User.class, savedUser.getId());
//
//        assertThat(user.getEmail()).isEqualTo(existUser.getEmail());
//
//    }
//}

package com.example.pawel.expense;

import com.example.pawel.expense.model.User;
import com.example.pawel.expense.repository.RoleRepository;
import com.example.pawel.expense.repository.UserRepository;
import com.example.pawel.expense.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.MockitoAnnotations.initMocks;

public class UserTests {

    @Mock
    private UserRepository mockUserRepository;
    @Mock
    private RoleRepository mockRoleRepository;
    @Mock
    private BCryptPasswordEncoder mockBCryptPasswordEncoder;

    private UserService userServiceUnderTest;
    private User user;

    @Before
    public void setUp() {
        initMocks(this);
        userServiceUnderTest = new UserService(mockUserRepository,
                                               mockRoleRepository,
                                               mockBCryptPasswordEncoder);
        User user = new User();
        user.setEmail("pnagorz@gmail.com");
        user.setPassword("123");
        user.setName("Pawel");
        //user.setLastName("Nago");


      User savedUser = mockUserRepository.save(user);

      User existUser = userServiceUnderTest.findUserByUserName(savedUser.getUsername());

      assertThat(user.getEmail()).isEqualTo(existUser.getEmail());
//        User existUser = userServiceUnderTest.find(User.class, savedUser.getId());

//        assertThat(user.getEmail()).isEqualTo(existUser.getEmail());
        
//        Mockito.when(mockUserRepository.save(any()))
//                .thenReturn(user);
//        Mockito.when(mockUserRepository.findByEmail(anyString()))
//                .thenReturn(user);
    }

    @Test
    public void testFindUserByEmail() {
        // Setup
        final String email = "test@test.com";

        // Run the test
        final User result = userServiceUnderTest.findUserByEmail(email);

        // Verify the results
        assertEquals(email, result.getEmail());
    }

    @Test
    public void testSaveUser() {
        // Setup
        final String email = "test@test.com";

        // Run the test
        User result = userServiceUnderTest.saveUser(user);

        // Verify the results
        assertEquals(email, result.getEmail());
    }
}