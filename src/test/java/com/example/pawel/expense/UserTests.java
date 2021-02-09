package com.example.pawel.expense;

import com.example.pawel.expense.model.User;
import com.example.pawel.expense.repository.RoleRepository;
import com.example.pawel.expense.repository.UserRepository;
import com.example.pawel.expense.service.UserService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.Assert.assertEquals;
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

    private UserService testUserService;
    private User user = User.builder()
            .id(1)
            .name("Pawel")
            .lastName("Nagorzanski")
            .email("pnagorz@gmail.com")
            .build();

    @Before
    public void setUp() {
        initMocks(this);
        testUserService = new UserService(mockUserRepository,
                mockRoleRepository,
                mockBCryptPasswordEncoder);

        Mockito.when(mockUserRepository.save(any()))
                .thenReturn(user);
        Mockito.when(mockUserRepository.findByEmail(anyString()))
                .thenReturn(user);
    }

    @Test
    public void testFindUserByEmail() {
        // Setup
        final String email = "test@test.com";

        // Run the test
        final User result = testUserService.findUserByEmail(email);

        // Verify the results
        assertEquals(email, result.getEmail());
    }

    @Test
    public void testSaveUser() {
        // Setup
        final String email = "test@test.com";

        // Run the test
        User result = testUserService.saveUser(User.builder().build());

        // Verify the results
        assertEquals(email, result.getEmail());
    }
}
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


