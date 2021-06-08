package MODELS;

import java.util.Objects;

public class User {

    private String username;
    private String password_login;
    private boolean role;


    public User() {
    }

    public User(String username, String password_login, Boolean role) {
        this.username = username;
        this.password_login = password_login;
        this.role = role;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword_login() {
        return password_login;
    }

    public void setPassword_login(String password_login) {
        this.password_login = password_login;
    }

    public boolean getRole() {
        return role;
    }

    public void setRole(boolean role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getUsername().equals(user.getUsername());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername());
    }
}
