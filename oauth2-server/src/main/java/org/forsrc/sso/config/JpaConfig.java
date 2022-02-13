package org.forsrc.sso.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement(proxyTargetClass = true)
@EnableConfigurationProperties(JpaProperties.class)
@EnableJpaRepositories(entityManagerFactoryRef = "entityManagerFactory", transactionManagerRef = "transactionManager", basePackages = {
		"org.forsrc.sso" })
public class JpaConfig {

	@Autowired
	private JpaProperties jpaProperties;

	@Autowired
	private EntityManagerFactoryBuilder builder;

	@Autowired
	@Qualifier("dataSource")
	private DataSource dataSource;

	@Primary
	@Bean(name = "entityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {

		// @formatter:off
		LocalContainerEntityManagerFactoryBean entityManagerFactory = builder.dataSource(dataSource)
				.packages("org.forsrc.sso.model")
				.persistenceUnit("persistenceUnit-mysso-oauth2-server")
				.properties(jpaProperties.getProperties())
				.build();
		entityManagerFactory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		return entityManagerFactory;
		// @formatter:on

	}

	@Primary
	@Bean(name = "transactionManager")
	public PlatformTransactionManager transactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager(entityManagerFactory().getObject());
		transactionManager.setRollbackOnCommitFailure(true);
		transactionManager.setDataSource(dataSource);
		return transactionManager;
	}

}