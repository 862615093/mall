package com.ww.mall.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * SpringSecurity白名单资源路径配置
 * Created by macro on 2018/11/5.
 */
@Getter
@Setter
@Component
//@ConfigurationProperties(prefix = "secure.ignored")
public class IgnoreUrlsConfig {

    private List<String> urls = new ArrayList<>();

    @PostConstruct
    public void init(){
        urls.add("/swagger-ui/");
        urls.add("/swagger-resources/**");
        urls.add("/**/v2/api-docs");
        urls.add("/**/*.html");
        urls.add("/**/*.js");
        urls.add("/**/*.css");
        urls.add("/**/*.png");
        urls.add("/**/*.map");
        urls.add("/favicon.ico");
        urls.add("/actuator/**");
        urls.add("/druid/**");
        urls.add("/admin/login");
        urls.add("/admin/register");
        urls.add("/admin/info");
        urls.add("/admin/logout");
        urls.add("/minio/upload");
    }

}
