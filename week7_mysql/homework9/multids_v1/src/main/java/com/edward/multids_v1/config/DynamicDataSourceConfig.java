package com.edward.multids_v1.config;

import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.edward.multids_v1.dataSource.DynamicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "com.edward.multids_v1.modules.order.mapper", sqlSessionFactoryRef = "sqlSessionFactory")
public class DynamicDataSourceConfig {
    // 主数据源
    @Bean
    @ConfigurationProperties("spring.datasource.master")
    public DataSource masterDataSource(){
        return DataSourceBuilder.create().build();
    }
    @Bean
    public DataSourceTransactionManager masterTransactionManager(@Qualifier("masterDataSource") DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }

    // 从数据源1
    @Bean
    @ConfigurationProperties("spring.datasource.slave1")
    public DataSource slave1DataSource(){
        return DataSourceBuilder.create().build();
    }
    @Bean
    public DataSourceTransactionManager slave1TransactionManager(@Qualifier("slave1DataSource") DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }

    // 从数据源2
    @Bean
    @ConfigurationProperties("spring.datasource.slave2")
    public DataSource slave2DataSource(){
        return DataSourceBuilder.create().build();
    }
    @Bean
    public DataSourceTransactionManager slave2TransactionManager(@Qualifier("slave2DataSource") DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }

    // 多数据源
    @Bean
    public DynamicDataSource dynamicDataSource(@Qualifier("masterDataSource") DataSource masterDS,
                                               @Qualifier("slave1DataSource") DataSource slave1DS,
                                               @Qualifier("slave2DataSource") DataSource slave2DS){
        return new DynamicDataSource(masterDS, slave1DS, slave2DS);
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(@Qualifier("dynamicDataSource") DynamicDataSource dataSource) throws Exception {
        MybatisSqlSessionFactoryBean bean = new MybatisSqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResource("classpath:/mapper/order/OrderMapper.xml"));
        bean.setTypeAliasesPackage("com.edward.multids_v1.modules.order.mapper");
        return bean.getObject();
    }

//    @Bean
//    public SqlSessionTemplate masterSessionTemplate(@Qualifier("sqlSessionFactory") SqlSessionFactory sqlSessionFactory){
//        return new SqlSessionTemplate(sqlSessionFactory);
//    }

}
