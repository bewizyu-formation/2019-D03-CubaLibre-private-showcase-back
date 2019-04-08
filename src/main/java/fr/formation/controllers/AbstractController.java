package fr.formation.controllers;

import fr.formation.user.User;
import fr.formation.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

public abstract class AbstractController {

    @Autowired
    private UserService userService;

    /**
     * Get the username of the authenticated  user
     * @return username
     */
    protected String getAuthenticatedUsername () {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    /**
     * Get the authenticated user
     * @return user
     */
    protected User getAuthenticatedUser () {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userService.getUserByUsername(username);

    }


}