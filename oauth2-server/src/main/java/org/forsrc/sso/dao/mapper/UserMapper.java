package org.forsrc.sso.dao.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.forsrc.sso.entity.User;


public interface UserMapper {
	
	
    List<User> findByAuthority(@Param("authority") String authority);

    void updateJwtToken(@Param("username") String username, @Param("jwtToken") String jwtToken,
            @Param("version") String version);

    void updatePassword(@Param("username") String username, @Param("password") String password,
            @Param("version") String version);
}
