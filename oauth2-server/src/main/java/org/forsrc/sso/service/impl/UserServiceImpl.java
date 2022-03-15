package org.forsrc.sso.service.impl;

import org.forsrc.sso.dao.BaseDao;
import org.forsrc.sso.dao.UserDao;
import org.forsrc.sso.dao.mapper.UserMapper;
import org.forsrc.sso.entity.User;
import org.forsrc.sso.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = { Exception.class })
public class UserServiceImpl extends BaseServiceImpl<User, String> implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    @Transactional(readOnly = true)
    public User getByUsername(String username) {
        return userDao.getById(username);
    }

    @Override
    public User save(User user) {
        if (user.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        return super.save(user);
    }

    @Override
    public User update(User user) {
        if (user.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        return super.update(user);
    }

    @Override
    public BaseDao<User, String> getBaseDao() {
        return userDao;
    }

    @Override
    public void updateJwtToken(String username, String jwtToken, String version) {
        userMapper.updateJwtToken(username, jwtToken, version);
    }

    @Override
    public void updatePassword(String username, String password, String version) {
        userMapper.updatePassword(username, password, version);
    }


}
