package com.example.xx2.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HomeController {

    private final static Logger LOG = LoggerFactory.getLogger(HomeController.class);

    private final UserController userController;

    @Autowired
    public HomeController(UserController userController) {
        this.userController = userController;
    }

    public void home(){
        LOG.trace("Trace");
        LOG.debug("Debug");
        LOG.info("Info");
        LOG.warn("Warn");
        LOG.error("Error");
        userController.authMenu();
    }
}
