package org.forsrc.sso.controller;

import org.forsrc.sso.entity.User;
import org.forsrc.sso.service.BaseService;
import org.forsrc.sso.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class ApiUserController extends BaseController<User, String> {

    @Autowired
    private UserService userService;

    @Override
    public BaseService<User, String> getBaseService() {
        return userService;
    }
}