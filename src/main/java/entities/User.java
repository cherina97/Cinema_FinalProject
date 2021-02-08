package entities;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class User {

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private int roleId;

    public User() {
    }

    public static User of(ResultSet resultSet) {
        try {
            int id = resultSet.getInt("id");
            String firstName = resultSet.getString("first_name");
            String lastName = resultSet.getString("last_name");
            String email = resultSet.getString("email");
            String password = resultSet.getString("password");
            int roleId = resultSet.getInt("role_id");

            return new User.Builder()
                    .withId(id)
                    .withFirstName(firstName)
                    .withLastName(lastName)
                    .withEmail(email)
                    .withPassword(password)
                    .withRoleId(roleId)
                    .build();

        } catch (SQLException e) {
            throw new RuntimeException("Error");
        }
    }

    public static class Builder{
        private User user;

        public Builder() {
            user = new User();
        }

        public Builder withId(int id){
            user.id = id;
            return this;
        }

        public Builder withFirstName(String firstName){
            user.firstName = firstName;
            return this;
        }

        public Builder withLastName(String lastName){
            user.lastName = lastName;
            return this;
        }

        public Builder withEmail(String email){
            user.email = email;
            return this;
        }

        public Builder withRoleId(int roleId){
            user.roleId = roleId;
            return this;
        }

        public Builder withPassword(String password){
            user.password = password;
            return this;
        }

        public User build(){
            return user;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return id == user.id && roleId == user.roleId && Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName) && Objects.equals(email, user.email) && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, email, password, roleId);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                '}';
    }
}
