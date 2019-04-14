package model;

public class User extends Entity {

    @Override
    public String toString() {
        return "User{" +
                "name='" + super.getName() + '\'' +
                '}';
    }

    public User(String name) {
        super(name);
    }
}
