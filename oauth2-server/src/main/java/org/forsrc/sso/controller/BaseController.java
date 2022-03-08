package org.forsrc.sso.controller;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityNotFoundException;

import org.forsrc.sso.service.BaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.NestedRuntimeException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@PreAuthorize("hasRole('ADMIN')")
public abstract class BaseController<T extends Serializable, PK> {

    public abstract BaseService<T, PK> getBaseService();

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseController.class);

    @GetMapping("/{pk}")
    public ResponseEntity<T> get(@PathVariable("pk") PK pk) {
        T t = null;
        try {
            t = getBaseService().get(pk);
            LOGGER.info("-->\t{} : {}", pk, t);
        } catch (EntityNotFoundException | NestedRuntimeException e) {
            LOGGER.warn("-->\t{} : {}", pk, e.getMessage());
            return new ResponseEntity<>(t, HttpStatus.OK);
        }

        return new ResponseEntity<>(t, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<Page<T>> page(@RequestParam(name = "page", required = false) Integer page,
            @RequestParam(name = "size", required = false) Integer size) {
        page = page == null || page == 0 ? 0 : page;
        size = size == null || size == 0 ? 10 : size;
        size = size >= 1000 ? 1000 : size;
        Page<T> list = getBaseService().page(page, size);
        LOGGER.info("-->\tpage({}, {}) : {}", page, size, list);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("")
    public ResponseEntity<T> save(@RequestBody T t) {
        Assert.notNull(t, "save: Object is null");
        // Assert.notNull(t, "save: username is nul");
        LOGGER.info("-->\tsave {}", t);
        t = getBaseService().save(t);
        return new ResponseEntity<>(t, HttpStatus.OK);
    }

    @PutMapping("")
    public ResponseEntity<T> update(@RequestBody T t) {
        Assert.notNull(t, "update: Object is null");
        // Assert.notNull(user.getUsername(), "save: username is nul");
        LOGGER.info("-->\tupdate {}", t);
        t = getBaseService().update(t);
        return new ResponseEntity<>(t, HttpStatus.OK);
    }

    @DeleteMapping("/{pk}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable("pk") PK pk) {
        Assert.notNull(pk, "delete: pk is nul");
        LOGGER.info("-->\tdelete {}", pk);
        getBaseService().delete(pk);
        Map<String, Object> message = new HashMap<>(1);
        message.put("message", "Deleted " + pk.toString());
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}