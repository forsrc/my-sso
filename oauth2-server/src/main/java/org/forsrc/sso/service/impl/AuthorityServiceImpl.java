package org.forsrc.sso.service.impl;

import java.util.List;

import org.forsrc.sso.dao.AuthorityDao;
import org.forsrc.sso.dao.BaseDao;
import org.forsrc.sso.entity.Authority;
import org.forsrc.sso.entity.AuthorityPk;
import org.forsrc.sso.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = { Exception.class })
public class AuthorityServiceImpl extends BaseServiceImpl<Authority, AuthorityPk> implements AuthorityService {

    @Autowired
    private AuthorityDao authorityDao;

    @Override
    @Transactional(readOnly = true)
    public List<Authority> getByUsername(String username) {
        Authority entity = new Authority();
        entity.setUsername(username);
        Example<Authority> example = Example.of(entity);
        return authorityDao.findAll(example);
    }

    @Override
    public void delete(String username) {
        List<Authority> list = getByUsername(username);
        for (Authority authority : list) {
            authorityDao.delete(authority);
        }
    }

    @Override

    public List<Authority> update(List<Authority> list) {
        return authorityDao.saveAll(list);
    }

    @Override
    public List<Authority> save(List<Authority> list) {
        return authorityDao.saveAll(list);
    }

    @Override
    public BaseDao<Authority, AuthorityPk> getBaseDao() {
        return authorityDao;
    }
}
