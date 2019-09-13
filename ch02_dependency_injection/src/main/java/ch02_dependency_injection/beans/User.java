package ch02_dependency_injection.beans;

public class User {
    private String id;

    public User(String id) {
        super();
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
