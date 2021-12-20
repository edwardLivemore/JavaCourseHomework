//package com.edward.multids_v2.config;
//
//import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
//import com.zaxxer.hikari.HikariDataSource;
//import lombok.Data;
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.apache.shardingsphere.driver.api.ShardingSphereDataSourceFactory;
//import org.apache.shardingsphere.infra.config.algorithm.ShardingSphereAlgorithmConfiguration;
//import org.apache.shardingsphere.readwritesplitting.api.ReadwriteSplittingRuleConfiguration;
//import org.apache.shardingsphere.readwritesplitting.api.rule.ReadwriteSplittingDataSourceRuleConfiguration;
//import org.apache.shardingsphere.sharding.api.config.ShardingRuleConfiguration;
//import org.apache.shardingsphere.sharding.api.config.rule.ShardingTableRuleConfiguration;
//import org.mybatis.spring.annotation.MapperScan;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
//
//import javax.sql.DataSource;
//import java.sql.SQLException;
//import java.util.*;
//
//@Configuration
//@MapperScan(basePackages = "com.edward.multids_v2.modules.order.mapper", sqlSessionFactoryRef = "sqlSessionFactory")
//public class DataSourceConfig {
//    // 主数据源
//    @Bean
//    @ConfigurationProperties("spring.datasource.master")
//    public DataSource masterDataSource(){
//        return DataSourceBuilder.create().build();
//    }
//
//    // 从数据源1
//    @Bean
//    @ConfigurationProperties("spring.datasource.slave1")
//    public DataSource slave1DataSource(){
//        return new HikariDataSource();
//    }
//
//    // 从数据源2
//    @Bean
//    @ConfigurationProperties("spring.datasource.slave2")
//    public DataSource slave2DataSource(){
//        return new HikariDataSource();
//    }
//
//    // 多数据源
//    @Bean
//    public DataSource multiDataSource(@Qualifier("masterDataSource") DataSource masterDataSource,
//                                      @Qualifier("slave1DataSource") DataSource slave1DataSource,
//                                      @Qualifier("slave2DataSource") DataSource slave2DataSource) throws SQLException {
//        // 配置数据源
//        Map<String, DataSource> dataSourceMap = new HashMap<>();
//        dataSourceMap.put("master", masterDataSource);
//        dataSourceMap.put("slave1", slave1DataSource);
//        dataSourceMap.put("slave2", slave2DataSource);
//
//        // 读写分离数据源配置
//        ReadwriteSplittingDataSourceRuleConfiguration dataSourceConfig =
//                new ReadwriteSplittingDataSourceRuleConfiguration("multiDs", null, "master",
//                        Arrays.asList("slave1", "slave2"), "randomLb");
//        Properties properties = new Properties();
//        properties.put("slave1", "1");
//        properties.put("slave2", "2");
//        properties.setProperty("sql-show", String.valueOf(true));
//
//        // 负载均衡配置(随机)
//        ShardingSphereAlgorithmConfiguration algorithmConfiguration = new ShardingSphereAlgorithmConfiguration("RANDOM", properties);
//        Map<String, ShardingSphereAlgorithmConfiguration> configMap = new HashMap<>(1);
//        configMap.put("randomLb", algorithmConfiguration);
//
//        ReadwriteSplittingRuleConfiguration ruleConfiguration = new ReadwriteSplittingRuleConfiguration(Collections.singleton(dataSourceConfig), configMap);
//        return ShardingSphereDataSourceFactory.createDataSource(dataSourceMap, Collections.singleton(ruleConfiguration), properties);
//    }
//
//    @Bean
//    public SqlSessionFactory sqlSessionFactory(@Qualifier("multiDataSource") DataSource dataSource) throws Exception {
//        MybatisSqlSessionFactoryBean bean = new MybatisSqlSessionFactoryBean();
//        bean.setDataSource(dataSource);
//        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResource("classpath:/mapper/order/OrderMapper.xml"));
//        bean.setTypeAliasesPackage("com.edward.multids_v2.modules.order.mapper");
//        return bean.getObject();
//    }
//}
