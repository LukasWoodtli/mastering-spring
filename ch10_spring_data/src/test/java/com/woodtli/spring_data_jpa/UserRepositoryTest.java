package com.woodtli.spring_data_jpa;

import com.woodtli.spring_data_jpa.model.User;
import com.woodtli.spring_data_jpa.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@ExtendWith(SpringExtension.class)
public class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;

    @Autowired
    TestEntityManager entityManager;

    @Test
    public void testSort() {
        List<String> expected = Arrays.asList("User Name 4", "User Name 3", "User Name 2", "User Name 1");

        Sort sort = Sort.by(Sort.Direction.DESC, "name")
                .and(Sort.by(Sort.Direction.ASC, "userid"));

        Iterable<User> users = userRepository.findAll(sort);

        List<String> usersList = StreamSupport.stream(users.spliterator(), false)
                                    .map(user -> user.getName())
                                    .collect(Collectors.toList());

        assertEquals(expected, usersList);

    }

    @Test
    public void testPaging() {
        PageRequest pageable = PageRequest.of(0, 2);
        Page<User> userPage = userRepository.findAll(pageable);
        var users = userPage.getContent();
        assertEquals(2, users.size());
        assertEquals(1, users.get(0).getId());
        assertEquals("User Name 2", users.get(1).getName());
    }

    @Test
    public void testFindByName() {
        List<User> users = userRepository.findByName("User Name 2");
        assertEquals(1, users.size());
        assertEquals(2, users.get(0).getId());
    }

    @Test
    public void testFindByNameSorting() {
        Sort sort = Sort.by(Sort.Direction.ASC, "name");
        List<User> users = userRepository.findByName("User Name 2", sort);
        assertEquals(1, users.size());
        assertEquals(2, users.get(0).getId());
    }

    @Test
    public void testFindByNamePageable() {
        PageRequest pageable = PageRequest.of(0, 2);
        List<User> users = userRepository.findByName("User Name 2", pageable);
        assertEquals(1, users.size());
        assertEquals(2, users.get(0).getId());
    }

    @Test
    public void testExistsByName() {
        Boolean exists = userRepository.existsByName("User Name 2");
        assertTrue(exists);
    }

    @Test
    public void testContByName() {
        Long num = userRepository.countByName("User Name 2");
        assertEquals(1, num);
    }

    @Test
    public void testDeleteByName() {
        Long deletedNum = userRepository.deleteByName("User Name 2");
        assertEquals(1, deletedNum);
    }

    @Test
    public void testRemoveByName() {
        List<User> deletedUsers = userRepository.removeByName("User Name 2");
        assertEquals(1, deletedUsers.size());
    }

    @Test
    public void testFindByTodosTitle() {
        List<User> users = userRepository.findByTodosTitle("Todo Title 3");
        assertEquals(1, users.size());
    }

    @Test
    public void testFindUsersByNameUsingQuery() {
        List<User> users = userRepository.findUsersByNameUsingQuery("User Name 2");
        assertEquals(1, users.size());
    }

    @Test
    public void testFindUsersByNameUsingNamedParameter() {
        List<User> users = userRepository.findUsersByNameUsingNamedParameter("User Name 2");
        assertEquals(1, users.size());
    }

    @Test
    public void testFindUsersWithNameUsingNamedQuery() {
        List<User> users = userRepository.findUsersWithNameUsingNamedQuery("User Name 2");
        assertEquals(1, users.size());
    }

    @Test
    public void testFindUsersByNameUsingNativeQuery() {
        List<User> users = userRepository.findUsersByNameUsingNativeQuery("User Name 2");
        assertEquals(1, users.size());
    }
}
