package com.microfocus.ring2parkms.users;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

/**
 * Repository for User data implemented using Spring Data JPA.
 *
 * @author Kevin A. Lee
 */
public interface UserRepository extends Repository<User, Long> {

    /**
     * Find all locations
     *
     * @return The users if found, null otherwise.
     */
    public List<User> findAll();

    /**
     * Find a user with the specified id.
     *
     * @param id
     * @return The user if found, null otherwise.
     */
    public User findById(Long id);

    /**
     * Find a user with the specified username.
     *
     * @param username
     * @return The user if found, null otherwise.
     */
    public User findByUsername(String username);

    /**
     * Find users whose username matches the partial name
     *
     * @param partialName
     * @return A list of users if found, null otherwise.
     */
    public List<User> findByUsernameContainingIgnoreCase(String partialName);

    /**
     * Find user by their username and password.
     * @param username the user's username
     * @param password the user's password
     * @return the user
     */
    public User findUserByUsernameAndPassword(String username, String password);

    /**
     * Find user by their mobile.
     * @param mobile the user's mobile number
     * @return the user
     */
    public User findUserByMobile(String mobile);

    /**
     * Fetch the number of users known to the system.
     *
     * @return The number of users.
     */
    @Query("SELECT count(*) from User")
    public int countUsers();
}

