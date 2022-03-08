package org.forsrc.sso.config;


import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

@Configuration
// @MapperScan("org.forsrc.**.dao.mapper")
public class MyBatisConfig {

    @Configuration
    @MapperScan(basePackages = { "org.forsrc.sso.dao.mapper" },
            sqlSessionFactoryRef = "sqlSessionFactory",
            sqlSessionTemplateRef = "sqlSessionTemplate")
    public static class DataSourcePrimary {

        @Autowired
        private DataSource dataSource;

        @Bean("sqlSessionFactory")
        @Qualifier("sqlSessionFactory")
        @Primary
        public SqlSessionFactory sqlSessionFactory() throws Exception {

            SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
            sqlSessionFactoryBean.setDataSource(dataSource);

            PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

            sqlSessionFactoryBean.setMapperLocations(
                    resolver.getResources("classpath*:org/forsrc/sso/dao/mapper/*Mapper.xml"));
            return sqlSessionFactoryBean.getObject();
        }
    }
}
