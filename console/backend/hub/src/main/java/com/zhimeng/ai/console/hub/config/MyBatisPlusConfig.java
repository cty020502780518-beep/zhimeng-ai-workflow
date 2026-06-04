package com.zhimeng.ai.console.hub.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.DynamicTableNameInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.zhimeng.ai.console.toolkit.handler.language.LanguageContext;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

/** MyBatis-Plus basic configuration and Mapper scanning. */
@Configuration
@MapperScan({"com.zhimeng.ai.console.hub.mapper", "com.zhimeng.ai.console.commons.mapper", "com.zhimeng.ai.console.toolkit.mapper"})
public class MyBatisPlusConfig {

    @Bean(name = "mybatisPlusInterceptor")
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        PaginationInnerInterceptor paginationInnerInterceptor = new PaginationInnerInterceptor();
        paginationInnerInterceptor.setDbType(DbType.MYSQL);
        interceptor.addInnerInterceptor(paginationInnerInterceptor);

        DynamicTableNameInnerInterceptor dynamicTable = new DynamicTableNameInnerInterceptor();
        dynamicTable.setTableNameHandler((sql, tableName) -> {
            // Configuration table takes effect
            List<String> tableNames = Arrays.asList("config_info", "prompt_template");
            if (tableNames.contains(tableName)) {
                // Domain check if it's "en"
                if (LanguageContext.isEn()) {
                    return tableName + "_en";
                }
            }
            return tableName;
        });

        interceptor.addInnerInterceptor(dynamicTable);
        return interceptor;
    }
}