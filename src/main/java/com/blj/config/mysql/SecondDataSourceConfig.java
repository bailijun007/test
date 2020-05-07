package com.blj.config.mysql;

import com.blj.util.SqlSessionFactoryUtil;
import com.github.pagehelper.PageInterceptor;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * 创建2个数据库连接的配置类
 * @author BaiLiJun  on 2020/5/7
 */
@Configuration
@MapperScan(basePackages = SecondDataSourceConfig.PACKAGE,sqlSessionFactoryRef = "secondSessionFactory")
public class SecondDataSourceConfig {
    //mapper文件目录
    static final String PACKAGE = "com.blj.mapper.bootTest2";

    //database驱动器
    @Value("${spring.datasource.second.type}")
    private Class<? extends DataSource> dataSourcceType;


    //mapper文件目录
    private static final String MAPPER_LOCATIONS = "classpath:mapper/second/*.xml";

    //处理器包.
    private static final String TYPE_HANDLERS_PACKAGE = "com.blj.handler";
    //暂时不用
    private static final String TYPE_ALIASES_PACKAGE = "com.blj.type";


    @Resource(name = "mybatisConfig")
    private org.apache.ibatis.session.Configuration mybatisConfig;

    @Resource(name = "pageInterceptor")
    private PageInterceptor pageInterceptor;

    @Bean(name = "secondDataSource")
    @ConfigurationProperties("spring.datasource.master")
    public DataSource secondDataSource(){
        return DataSourceBuilder.create().type(dataSourcceType).build();
    }

    @Bean(name = "secondTransactionManager")
    public DataSourceTransactionManager masterTransactionManager(){
        return new DataSourceTransactionManager(secondDataSource());
    }

    @Bean(name = "secondSessionFactory")
    public SqlSessionFactory masterSqlSessionFactory(@Qualifier("secondDataSource") DataSource masterDataSource) {
        return SqlSessionFactoryUtil.createSqlSessionFactory(masterDataSource,TYPE_ALIASES_PACKAGE,
                TYPE_HANDLERS_PACKAGE,MAPPER_LOCATIONS,mybatisConfig,new Interceptor[] {pageInterceptor});
    }

}
