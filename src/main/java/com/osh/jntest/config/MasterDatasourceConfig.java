package com.osh.jntest.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import javax.sql.DataSource;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@MapperScan(basePackages = "com.osh.jntest", annotationClass = Mapper.class, sqlSessionFactoryRef = "masterSqlSessionFactory")
@PropertySource({"classpath:properties/database.properties"})
public class MasterDatasourceConfig {

    private Environment env;

    public MasterDatasourceConfig(Environment env) {
        this.env = env;
    }

    @Primary
    @Bean(name = "masterDataSource")
    public DataSource masterDataSource() throws Exception {
        HikariConfig config = new HikariConfig();
        config.setDriverClassName(env.getProperty("master.datasource.driver-class-name"));
        config.setJdbcUrl(env.getProperty("master.datasource.url"));
        config.setUsername(env.getProperty("master.datasource.username"));
        config.setPassword(env.getProperty("master.datasource.password"));

        config.addDataSourceProperty("prepStmtCacheSize",
            Integer.valueOf(env.getProperty("hikari.config.prepStmtCacheSize")));
        config.addDataSourceProperty("prepStmtCacheSqlLimit",
            Integer.valueOf(env.getProperty("hikari.config.prepStmtCacheSqlLimit")));
        config.addDataSourceProperty("cachePrepStmts",
            Boolean.valueOf(env.getProperty("hikari.config.cachePrepStmts")));
        config.addDataSourceProperty("useServerPrepStmts",
            Boolean.valueOf(env.getProperty("hikari.config.useServerPrepStmts")));

        return new HikariDataSource(config);
    }

    @Primary
    @Bean(name = "masterSqlSessionFactory")
    public SqlSessionFactory masterSqlSessionFactory(@Qualifier("masterDataSource") DataSource masterDataSource) throws Exception {

        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(masterDataSource);
        sessionFactoryBean.setMapperLocations(
            new PathMatchingResourcePatternResolver().getResources("classpath:mappers/**/*MasterMapper.xml"));
        sessionFactoryBean.setConfigLocation(new ClassPathResource("mybatis-config.xml"));

        return sessionFactoryBean.getObject();
    }

    @Primary
    @Bean(name = "masterSqlSessionTemplate", destroyMethod = "clearCache")
    public SqlSessionTemplate masterSqlSessionTemplate(@Qualifier("masterSqlSessionFactory") SqlSessionFactory masterSqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(masterSqlSessionFactory);
    }

    @Bean(name = "masterPlatformTransactionManager")
    public PlatformTransactionManager masterPlatformTransactionManager(@Qualifier("masterDataSource") DataSource masterDataSource) throws Exception {
        return new DataSourceTransactionManager(masterDataSource);
    }

}
