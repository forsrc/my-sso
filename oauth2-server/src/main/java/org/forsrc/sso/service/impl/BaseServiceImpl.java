package org.forsrc.sso.service.impl;

import java.io.Serializable;

import org.forsrc.sso.service.BaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = { Exception.class })
public abstract class BaseServiceImpl<T extends Serializable, PK> implements BaseService<T, PK> {

    private static final Logger LOG = LoggerFactory.getLogger(BaseServiceImpl.class);

    @Override
    public T save(T t) {
        T updated = getBaseDao().save(t);
        return updated;
    }

    @Override
    public T update(T t) {
        T updated = getBaseDao().save(t);
        return updated;
    }

    @Override
    @Transactional(readOnly = true)
    public T get(PK pk) {
        return getBaseDao().findById(pk).get();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<T> page(int page, int size) {
        Page<T> p = getBaseDao().findAll(PageRequest.of(page, size));
        return p;
    }

    @Override
    public void delete(PK pk) {
        getBaseDao().deleteById(pk);
    }

    @Override
    public void delete(T t) {
        getBaseDao().delete(t);
    }
}
