package org.forsrc.sso.service;


import org.forsrc.sso.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = { Exception.class })
public interface UserService extends BaseService<User, String> {

    @Transactional(readOnly = true)
    public User getByUsername(String username);

    void updateJwtToken(String username, String jwtToken, String version);

    void updatePassword(String username, String password, String version);
}
