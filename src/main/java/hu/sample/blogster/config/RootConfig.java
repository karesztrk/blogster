package hu.sample.blogster.config;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories("hu.sample.blogster.repository")
@ComponentScan("hu.sample.blogster")
@PropertySource("classpath:application.properties")
public class RootConfig {

	private static final String DATABASE_DRIVER = "db.driver";
	private static final String DATABASE_PASSWORD = "db.password";
	private static final String DATABASE_URL = "db.url";
	private static final String DATABASE_USERNAME = "db.username";
	private static final String ENTITYMANAGER_PACKAGES_TO_SCAN = "entitymanager.packages.to.scan";
	private static final String ENTITYMANAGER_SHOW_SQL = "entitymanager.show.sql";
	private static final String ENTITYMANAGER_DIALECT = "entitymanager.dialect";
	private static final String ENTITYMANAGER_DDL = "entitymanager.hbm2ddl.auto";

	/**
	 * Configuration bundle.
	 */
	@Resource
	private Environment env;

	/**
	 * Build the application data source instance.
	 *
	 * @return
	 */
	@Bean
	public DataSource dataSource() {
		final DriverManagerDataSource dataSource = new DriverManagerDataSource();

		// final StrSubstitutor sub = new StrSubstitutor(System.getenv());
		dataSource.setUrl(env.getRequiredProperty(DATABASE_URL));
		dataSource.setUsername(env.getRequiredProperty(DATABASE_USERNAME));
		dataSource.setPassword(env.getRequiredProperty(DATABASE_PASSWORD));
		dataSource.setDriverClassName(env.getRequiredProperty(DATABASE_DRIVER));

		return dataSource;
	}

	/**
	 * Build the entity manager.
	 *
	 * @return
	 */
	@Bean(name = "entityManagerFactory")
	public LocalContainerEntityManagerFactoryBean createEntityManagerFactory() {

		final HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();

		adapter.setShowSql(Boolean.parseBoolean(env
				.getRequiredProperty(ENTITYMANAGER_SHOW_SQL)));
		adapter.setDatabasePlatform(env
				.getRequiredProperty(ENTITYMANAGER_DIALECT));

		final LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();
		entityManager.setDataSource(dataSource());
		entityManager.setJpaVendorAdapter(adapter);
		entityManager.setPackagesToScan(env
				.getRequiredProperty(ENTITYMANAGER_PACKAGES_TO_SCAN));

		entityManager.setPersistenceUnitName("hibernatePersistenceUnit");
		entityManager.getJpaPropertyMap().put("hibernate.hbm2ddl.auto",
				env.getRequiredProperty(ENTITYMANAGER_DDL));
		return entityManager;
	}

	/**
	 * Build the transaction manager.
	 *
	 * @return
	 */
	@Bean(name = "transactionManager")
	public PlatformTransactionManager createTransactionManager() {
		final JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(createEntityManagerFactory()
				.getObject());
		return transactionManager;
	}

}
