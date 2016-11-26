package com;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.CharacterEncodingFilter;

/**
 * Created by sdyang on 2016/10/16.
 */
@SpringBootApplication
public class SpringBootStart extends SpringBootServletInitializer {

    private static Logger logger = Logger.getLogger(SpringBootStart.class);

    /**
     * spring mvc默认是ISO-8859-1 编码，表单POST不支持中文，此处强制 utf-8 编码
     * Content-Type:"text/html;charset=ISO-8859-1"
     *
     * @return
     */
    @Bean
    FilterRegistrationBean charEncode() {
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.addUrlPatterns("/*.html");
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);
        bean.setFilter(filter);
        return bean;
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Override
    protected SpringApplicationBuilder configure(final SpringApplicationBuilder application) {
        return application.sources(SpringBootStart.class);
    }

    public static void main(String[] args) {
        System.out.println("==============  ***  开始启动系统  ***  ==============");
        ConfigurableApplicationContext ctx = SpringApplication.run(SpringBootStart.class, args);
        System.out.println("==============  ***  系统启动完成  ***  ==============");
    }
}
