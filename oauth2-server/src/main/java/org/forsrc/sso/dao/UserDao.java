package org.forsrc.sso.dao;


import java.util.List;

import org.forsrc.sso.entity.User;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;


@Repository
@RepositoryRestResource(collectionResourceRel = "user", path = "user")
public interface UserDao extends BaseDao<User, String> {
    List<User> findByUsernameAndPassword(String username, String password);
}
