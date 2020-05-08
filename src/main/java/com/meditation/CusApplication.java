package com.meditation;

/**
 * <b>模拟Springboot启动类</b>
 * 
 * <br>
 * WebApplicationInitializer是SpringMVC提供的接口<br>
  * 用于初始化任何Spring容器，实现该接口的类会被SpringServletContainerInitializer调用<br>
  * 而AbstractAnnotationConfigDispatcherServletInitializer继承了WebApplicationInitializer<br>
 * 
 * @author: zhangyuting <br>
 * @date: 2019-11-27 <br>
 */
public class CusApplication {
//extends AbstractAnnotationConfigDispatcherServletInitializer {
//
//	public CnkiApplication() {
//	}
//
//	/**
//	 * Dao,Service 配置层
//	 *
//	 * @return
//	 */
//	@Override
//	protected Class<?>[] getRootConfigClasses() {
//		return new Class[] { SpringBoot.class };
//	}
//
//	/**
//	 * MVC 配置层
//	 *
//	 * @return
//	 */
//	@Override
//	protected Class<?>[] getServletConfigClasses() {
//		return new Class[] { SpringMVC.class };
//	}
//
//	/**
//	 * servlet 映射路径
//	 *
//	 * @return
//	 */
//	@Override
//	protected String[] getServletMappings() {
//		return new String[] { "/" };
//	}
//
//	@Override
//	public void onStartup(ServletContext servletContext) throws ServletException {
//		servletContext.addListener(new WebAppRootListener());
//		super.onStartup(servletContext);
//	}
//
//	@Override
//	protected Filter[] getServletFilters() {
//		Filter[] filters = new Filter[2];
//		filters[0] = new CharacterEncodingFilter("utf-8");
//		filters[1] = new ResourceUrlEncodingFilter();
//		return filters;
//	}
}