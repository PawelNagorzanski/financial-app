package com.example.pawel.expense;

import com.example.pawel.expense.model.User;
import com.example.pawel.expense.repository.RoleRepository;
import com.example.pawel.expense.repository.UserRepository;
import com.example.pawel.expense.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.assertj.core.api.Assertions.assertThat;


public class UserTests {

    @Mock
    private UserRepository mockUserRepository;

    @Mock
    private RoleRepository mockRoleRepository;

    @Mock
    private BCryptPasswordEncoder mockBCryptPasswordEncoder;

    @Autowired
    private TestEntityManager entityManager;

    private UserService testUserService;

//    @Test
//    public void testCreateUser() {
//        User user = new User();
//        user.setEmail("pnagorz@gmail.com");
//        user.setPassword("asd");
//        user.setName("Pawel");
//        user.setLastName("NAg");
//
//        User savedUser = mockUserRepository.save(user);
//
//        User existUser = entityManager.find(User.class, savedUser.getId());
//
//        assertThat(user.getEmail()).isEqualTo(existUser.getEmail());
//
//    }

    private User user = User.builder()
            .id("1")
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
        String email = "test@test.com";

        // Run the test
        User result = testUserService.findUserByEmail(email);

        // Verify the results
        assertEquals(email, result.getEmail());
    }

    @Test
    public void testSaveUser() {
        // Setup
        String email = "test@test.com";

        // Run the test
        User result = testUserService.saveUser(User.builder().build());

        // Verify the results
        assertEquals(email, result.getEmail());
    }

    @Test
    public void testCreateUser() {
        User user = new User(); // problem
        user.setName("Pawel");
        user.setPassword("strong");
        user.setEmail("pnagorz@onet.pl");

        User savedUser = mockUserRepository.save(user);
        User existUser = entityManager.find(User.class, savedUser.getId());
        assertThat(user.getEmail()).isEqualTo(existUser.getEmail());
    }
}