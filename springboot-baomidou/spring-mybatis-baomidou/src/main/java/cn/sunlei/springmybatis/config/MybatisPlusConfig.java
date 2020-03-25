package cn.sunlei.springmybatis.config;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Slf4j
@Configuration
public class MybatisPlusConfig {


    /**
     * @description: 配置分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        //注册分页插件
        return new PaginationInterceptor();
    }


}
