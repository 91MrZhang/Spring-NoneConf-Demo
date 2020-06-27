package com.meditation;

import com.meditation.config.SpringBoot;
import com.meditation.config.SpringMVC;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.resource.ResourceUrlEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import org.springframework.web.util.WebAppRootListener;

import javax.servlet.Filter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * Spring启动类
 * AbstractAnnotationConfigDispatcherServletInitializer是Spring提供的接口
 * Spring最终实现了WebApplicationInitializer接口
 * 实际上使用了JAVA的SPI机制，所以启动即会加载，但这只发生在Servlet3.0版本及以上
 *
 * @author zhangyuting
 */
public class CusApplication extends AbstractAnnotationConfigDispatcherServletInitializer {

    public CusApplication() {
    }

    /**
     * Dao,Service 配置层
     *
     * @return Class<?>[]
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{SpringBoot.class};
    }

    /**
     * MVC 配置层
     *
     * @return Class<?>[]
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{SpringMVC.class};
    }

    /**
     * servlet 映射路径
     *
     * @return String[]
     */
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        servletContext.addListener(new WebAppRootListener());
        super.onStartup(servletContext);
    }

    @Override
    protected Filter[] getServletFilters() {
        Filter[] filters = new Filter[2];
        filters[0] = new CharacterEncodingFilter("utf-8");
        filters[1] = new ResourceUrlEncodingFilter();
        return filters;
    }
}