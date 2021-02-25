package entities;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

/**
 * The type User.
 */
public class User {
    private static final Logger LOG = LoggerFactory.getLogger(User.class);

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private int roleId;
    private String hash;
    private int active;

    /**
     * Instantiates a new User.
     */
    public User() {
    }

    /**
     * Of user.
     *
     * @param resultSet the result set
     * @return the user
     */
    public static User of(ResultSet resultSet) {
        try {
            int id = resultSet.getInt("id");
            String firstName = resultSet.getString("first_name");
            String lastName = resultSet.getString("last_name");
            String email = resultSet.getString("email");
            String password = resultSet.getString("password");
            int roleId = resultSet.getInt("role_id");
            String hash = resultSet.getString("hash");
            int active = resultSet.getInt("active");

            return new User.Builder()
                    .withId(id)
                    .withFirstName(firstName)
                    .withLastName(lastName)
                    .withEmail(email)
                    .withPassword(password)
                    .withRoleId(roleId)
                    .withHash(hash)
                    .withActive(active)
                    .build();

        } catch (SQLException e) {
            LOG.error("SQLException in of method of User class", e);
        }
        //todo
        return null;
    }

    /**
     * Gets hash.
     *
     * @return the hash
     */
    public String getHash() {
        return hash;
    }

    /**
     * Sets hash.
     *
     * @param hash the hash
     */
    public void setHash(String hash) {
        this.hash = hash;
    }

    /**
     * Gets active.
     *
     * @return the active
     */
    public int getActive() {
        return active;
    }

    /**
     * Sets active.
     *
     * @param active the active
     */
    public void setActive(int active) {
        this.active = active;
    }

    /**
     * The type Builder.
     */
    public static class Builder{
        private User user;

        /**
         * Instantiates a new Builder.
         */
        public Builder() {
            user = new User();
        }

        /**
         * With id builder.
         *
         * @param id the id
         * @return the builder
         */
        public Builder withId(int id){
            user.id = id;
            return this;
        }

        /**
         * With first name builder.
         *
         * @param firstName the first name
         * @return the builder
         */
        public Builder withFirstName(String firstName){
            user.firstName = firstName;
            return this;
        }

        /**
         * With last name builder.
         *
         * @param lastName the last name
         * @return the builder
         */
        public Builder withLastName(String lastName){
            user.lastName = lastName;
            return this;
        }

        /**
         * With email builder.
         *
         * @param email the email
         * @return the builder
         */
        public Builder withEmail(String email){
            user.email = email;
            return this;
        }

        /**
         * With role id builder.
         *
         * @param roleId the role id
         * @return the builder
         */
        public Builder withRoleId(int roleId){
            user.roleId = roleId;
            return this;
        }

        /**
         * With password builder.
         *
         * @param password the password
         * @return the builder
         */
        public Builder withPassword(String password){
            user.password = password;
            return this;
        }

        /**
         * With hash builder.
         *
         * @param hash the hash
         * @return the builder
         */
// TODO: 25.02.2021
        public Builder withHash(String hash){
            user.hash = hash;
            return this;
        }

        /**
         * With active builder.
         *
         * @param active the active
         * @return the builder
         */
// TODO: 25.02.2021
        public Builder withActive(int active){
            user.active = active;
            return this;
        }


        /**
         * Build user.
         *
         * @return the user
         */
        public User build(){
            return user;
        }
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets first name.
     *
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets first name.
     *
     * @param firstName the first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets last name.
     *
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets last name.
     *
     * @param lastName the last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets password.
     *
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets role id.
     *
     * @return the role id
     */
    public int getRoleId() {
        return roleId;
    }

    /**
     * Sets role id.
     *
     * @param roleId the role id
     */
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
