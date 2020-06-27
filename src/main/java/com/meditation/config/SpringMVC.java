package com.meditation.config;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;

import com.meditation.interceptor.PermissionInterceptor;

/**
 * SpringMVC
 *
 * @author zhangyuting
 */
@Configuration
@ComponentScan("com.meditation.*")
@EnableWebMvc
public class SpringMVC extends WebMvcConfigurerAdapter {

    /**
     * 配置jsp前后缀
     *
     * @param registry registry
     */
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.jsp("/WEB-INF/view/", ".jsp");
    }

    /**
     * 配置静态文件加载规则
     *
     * @param registry registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/dist/**").addResourceLocations("/dist/");
        registry.addResourceHandler("/upload/**").addResourceLocations("/upload/");
    }

    /**
     * 配置json消息转化器，基于Fastjson
     *
     * @param converters converters
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
        List<MediaType> supportedMediaTypes = new ArrayList<MediaType>();
        supportedMediaTypes.add(MediaType.TEXT_HTML);
        converter.setSupportedMediaTypes(supportedMediaTypes);
        converter.setDefaultCharset(Charset.forName("utf-8"));
        converters.add(converter);
    }

    /**
     * 配置multipartResolver
     */
    @Bean("multipartResolver")
    public CommonsMultipartResolver multipartResolver(@Value("#{systemProperties['webapp.root']}") String webRoot) throws IOException {
        String uploadTmpPath = SpringMVC.class.getResource("/uploadtmp").getPath();
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        resolver.setMaxUploadSize(1024 * 1024 * 1024);
        resolver.setMaxUploadSizePerFile(1024 * 1024 * 1024);
        resolver.setMaxInMemorySize(1024 * 1024 * 1024);
        resolver.setUploadTempDir((new FileSystemResource(uploadTmpPath)));
        resolver.setDefaultEncoding("utf-8");
        return resolver;
    }


    /**
     * 配置拦截器
     *
     * @param registry registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new PermissionInterceptor()).addPathPatterns("/course/**");
    }
}
