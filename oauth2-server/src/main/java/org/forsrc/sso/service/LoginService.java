package org.forsrc.sso.service;


import java.rmi.NoSuchObjectException;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = { Exception.class })
public interface LoginService {

    @Transactional(readOnly = true)
    UserDetails check(String username, String password) throws NoSuchObjectException;

}
