package org.forsrc.sso.service.impl;

import java.io.Serializable;

import org.forsrc.sso.service.BaseService;
import org.forsrc.utils.BeanUtils;
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
    @Transactional(rollbackFor = { Exception.class })
    public T save(T t) {
        T saved = getBaseDao().save(t);
        LOG.info("save: {} -> {}", t, saved);
        return saved;
    }

    @Override
    @Transactional(rollbackFor = { Exception.class })
    public T update(T t) {
        T updated = getBaseDao().save(t);
        LOG.info("updated: {} -> {}", t, updated);
        return updated;
    }

    @Override
    @Transactional(rollbackFor = { Exception.class })
    public T update(PK pk, T t) {
        T source = getBaseDao().findById(pk).get();
        BeanUtils.copyIgnoreNull(t, source);
        T updated = getBaseDao().save(source);
        LOG.info("updated({}): {} -> {}", pk, source, updated);
        return updated;
    }


    @Override
    @Transactional(readOnly = true)
    public T get(PK pk) {
    	T t = getBaseDao().findById(pk).get();
    	LOG.info("get({}): {} -> ", pk, t);
        return t;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<T> page(int page, int size) {
        Page<T> p = getBaseDao().findAll(PageRequest.of(page, size));
        LOG.info("get({}, {}): {} -> ", page, size, p);
        return p;
    }

    @Override
    @Transactional(rollbackFor = { Exception.class })
    public void delete(PK pk) {
    	LOG.info("delete({})", pk);
        getBaseDao().deleteById(pk);
    }

    @Override
    @Transactional(rollbackFor = { Exception.class })
    public void delete(T t) {
    	LOG.info("delete(): {}", t);
        getBaseDao().delete(t);
    }
}
