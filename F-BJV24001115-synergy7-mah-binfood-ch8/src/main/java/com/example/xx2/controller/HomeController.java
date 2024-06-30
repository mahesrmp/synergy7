package com.example.xx2.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("home")
public class HomeController {

    private final static Logger LOG = LoggerFactory.getLogger(HomeController.class);

    private final UserController userController;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    public HomeController(UserController userController) {
        this.userController = userController;
    }

    @GetMapping
    public void home() {
        System.out.println(passwordEncoder.encode("123456"));
//        LOG.trace("Trace");
//        LOG.debug("Debug");
//        LOG.info("Info");
//        LOG.warn("Warn");
//        LOG.error("Error");
    }
}
