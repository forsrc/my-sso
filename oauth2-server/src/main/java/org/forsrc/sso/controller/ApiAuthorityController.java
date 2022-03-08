package org.forsrc.sso.controller;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.forsrc.sso.entity.Authority;
import org.forsrc.sso.entity.AuthorityPk;
import org.forsrc.sso.service.AuthorityService;
import org.forsrc.sso.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.NestedRuntimeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/authority")
public class ApiAuthorityController extends BaseController<Authority, AuthorityPk>{

    @Autowired
    private AuthorityService authorityService;

    @GetMapping("/{pk}")
    public ResponseEntity<List<Authority>> get(@PathVariable("pk") String usanename) {

        List<Authority> list = null;
        try {
            list = authorityService.getByUsername(usanename);

        } catch (EntityNotFoundException | NestedRuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.OK);
        }

        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    
    @Override
    @GetMapping("/_")
    public ResponseEntity<Authority> get(AuthorityPk pk) {

    	return null;
    }

	@Override
	public BaseService<Authority, AuthorityPk> getBaseService() {
		return authorityService;
	}
    

}