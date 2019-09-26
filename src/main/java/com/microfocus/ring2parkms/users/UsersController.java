package com.microfocus.ring2parkms.users;

import com.microfocus.ring2parkms.users.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.logging.Logger;

/**
 * A RESTFul controller for accessing user information.
 *
 * @author Kevin A. Lee
 */
@RestController
public class UsersController {

    protected Logger logger = Logger.getLogger(UsersController.class
            .getName());
    protected UserRepository userRepository;

    /**
     * Create an instance plugging in the respository of Users.
     *
     * @param userRepository
     *            An user repository implementation.
     */
    @Autowired
    public UsersController(UserRepository userRepository) {
        this.userRepository = userRepository;

        logger.info("UserRepository says system has "
                + userRepository.countUsers() + " users");
    }

    /**
     * Fetch all users.
     *
     * @return A non-null, non-empty set of users.
     * @throws UserNotFoundException If there are no matches at all.
     */
    @RequestMapping("/users")
    public List<User> all() {
        logger.info("users-service all() invoked: "
                + userRepository.getClass().getName());

        List<User> users = userRepository
                .findAll();
        logger.info("users-service all() found: " + users);

        if (users == null || users.size() == 0)
            throw new UserNotFoundException("all");
        else {
            return users;
        }
    }

    /**
     * Fetch an user with the specified id.
     *
     * @param id
     *            A numeric id.
     * @return The user if found.
     * @throws UserNotFoundException
     *             If the id is not recognised.
     */
    @RequestMapping("/user/{id}")
    public User byId(@PathVariable("id") Long id) {

        logger.info("users-service byId() invoked: " + id.toString());
        User user = userRepository.findById(id);
        logger.info("users-service byId() found: " + user);

        if (user == null)
            throw new UserNotFoundException(id.toString());
        else {
            return user;
        }
    }

    /**
     * Fetch users with the specified username. A partial case-insensitive match
     * is supported. So <code>http://.../users/username/a</code> will find any
     * users with upper or lower case 'a' in their username.
     *
     * @param partialName
     * @return A non-null, non-empty set of users.
     * @throws UserNotFoundException
     *             If there are no matches at all.
     */
    @RequestMapping("/users/username/{name}")
    public List<User> byUsername(@PathVariable("name") String partialName) {
        logger.info("users-service byUsername() invoked: "
                + userRepository.getClass().getName() + " for "
                + partialName);

        List<User> users = userRepository
                .findByUsernameContainingIgnoreCase(partialName);
        logger.info("users-service byUsername() found: " + users);

        if (users == null || users.size() == 0)
            throw new UserNotFoundException(partialName);
        else {
            return users;
        }
    }
}

