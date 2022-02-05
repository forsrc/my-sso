package com.forsrc.gateway.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.forsrc.gateway.model.GatewayDefine;

import java.util.List;

@Repository
public interface GatewayDefineDao extends JpaRepository<GatewayDefine, String> {

    List<GatewayDefine> findAll();

    GatewayDefine save(GatewayDefine gatewayDefine);

    void deleteById(String id);

    boolean existsById(String id);
}
