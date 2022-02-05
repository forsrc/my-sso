package com.forsrc.gateway.service;

import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.stereotype.Service;

import com.forsrc.gateway.model.GatewayDefine;

import java.util.List;

@Service
public interface GatewayDefineService {
    List<GatewayDefine> findAll();

    RouteDefinition toRouteDefinition(GatewayDefine gatewayDefine);

    List<RouteDefinition> getRouteDefinitions();

    List<GatewayDefine>  loadRouteDefinitions();

    void loadRouteDefinition(GatewayDefine gatewayDefine);

    GatewayDefine save(GatewayDefine gatewayDefine);

    void deleteById(String id);

    boolean existsById(String id);

    void refreshRoute();

    void update(GatewayDefine gatewayDefine);

    GatewayDefine findById(String id);

    GatewayDefine loadRouteDefinition(String id);

    void disableRouteDefinition(String id);
}
