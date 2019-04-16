package fr.formation.controllers;

import fr.formation.artist.ArtistService;
import fr.formation.user.UserDTO;
import fr.formation.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

public abstract class AbstractController {

    private static final Logger log = LoggerFactory.getLogger(AbstractController.class);

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
    protected UserDTO getAuthenticatedUserDTO() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        log.info("username : " + username);
        return userService.findByUsername(username);

    }





}