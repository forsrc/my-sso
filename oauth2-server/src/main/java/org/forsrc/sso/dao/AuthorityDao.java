package org.forsrc.sso.dao;



import org.forsrc.sso.entity.Authority;
import org.forsrc.sso.entity.AuthorityPk;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;


@Repository
@RepositoryRestResource(collectionResourceRel = "authority", path = "authority")
public interface AuthorityDao
        extends BaseDao<Authority, AuthorityPk> {

}
