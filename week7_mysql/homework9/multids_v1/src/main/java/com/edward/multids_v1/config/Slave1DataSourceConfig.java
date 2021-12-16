//package com.edward.multids_v1.config;
//
//import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
//import com.zaxxer.hikari.HikariDataSource;
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.mybatis.spring.SqlSessionTemplate;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
//import org.springframework.jdbc.datasource.DataSourceTransactionManager;
//
//import javax.sql.DataSource;
//
//@Configuration
//public class Slave1DataSourceConfig {
//    @Bean
//    @ConfigurationProperties("spring.datasource.slave1")
//    public HikariDataSource slave1DataSource(){
//        return new HikariDataSource();
//    }
//
//    @Bean
//    public SqlSessionFactory slave1SessionFactory(@Qualifier("slave1DataSource") DataSource dataSource) throws Exception {
//        MybatisSqlSessionFactoryBean bean = new MybatisSqlSessionFactoryBean();
//        bean.setDataSource(dataSource);
//        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResource("classpath:/mapper/order/slave1/OrderMapper.xml"));
//        bean.setTypeAliasesPackage("com.edward.multids_v1.modules.order.mapper");
//        return bean.getObject();
//    }
//
//    @Bean
//    public DataSourceTransactionManager slave1TransactionManager(@Qualifier("slave1DataSource") DataSource dataSource){
//        return new DataSourceTransactionManager(dataSource);
//    }
//
//    @Bean
//    public SqlSessionTemplate slave1SessionTemplate(@Qualifier("slave1SessionFactory") SqlSessionFactory sqlSessionFactory){
//        return new SqlSessionTemplate(sqlSessionFactory);
//    }
//}
