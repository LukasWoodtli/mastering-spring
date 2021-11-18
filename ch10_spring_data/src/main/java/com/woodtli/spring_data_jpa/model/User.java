package com.woodtli.spring_data_jpa.model;

import javax.persistence.*;
import java.util.List;

@Entity
@NamedQuery(name = "User.findUsersWithNameUsingNamedQuery",  // see also the method with the same name in `UserRepository`
            query = "select u from User u where u.name = ?1")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String userid;

    private String name;

    @OneToMany(mappedBy = "user")
    private List<Todo> todos;

    public User() {}


    public User(Long id, String userid, String name, List<Todo> todos) {
        this.id = id;
        this.userid = userid;
        this.name = name;
        this.todos = todos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Todo> getTodos() {
        return todos;
    }

    public void setTodos(List<Todo> todos) {
        this.todos = todos;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userid='" + userid + '\'' +
                ", name='" + name + '\'' +
                ", todos=" + todos +
                '}';
    }
}
