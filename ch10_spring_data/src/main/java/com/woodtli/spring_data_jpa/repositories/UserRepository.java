package com.woodtli.spring_data_jpa.repositories;

import com.woodtli.spring_data_jpa.model.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


import java.util.List;

@RepositoryRestResource(collectionResourceRel = "users", path = "users")
public interface UserRepository extends PagingAndSortingRepository<User, Long> {

    List<User> findByName(String name);
    List<User> findByName(String name, Sort sort);
    List<User> findByName(String name, Pageable pageable);

    Boolean existsByName(String name);

    Long countByName(String name);

    Long deleteByName(String name);

    List<User> removeByName(String name);

    List<User> findByTodosTitle(String title);

    @Query("select u from User u where u.name = ?1")
    List<User> findUsersByNameUsingQuery(String name);

    @Query("select u from User u where u.name = :name")
    List<User> findUsersByNameUsingNamedParameter(@Param("name") String name);

    // see also the named query in the user entity
    List<User> findUsersWithNameUsingNamedQuery(String name);

    @Query(value = "SELECT * FROM USER WHERE name = ?1",
           nativeQuery = true)
    List<User> findUsersByNameUsingNativeQuery(String name);

}
