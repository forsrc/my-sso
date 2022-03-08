package org.forsrc.sso.dao;



import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

@NoRepositoryBean
public interface BaseDao<T extends Serializable, PK> extends JpaRepository<T, PK>, PagingAndSortingRepository<T, PK> {
}
