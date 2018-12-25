package com.xz.platform.common.interceptor;

import java.nio.charset.Charset;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 拦截器
 * 
 * @ClassName: MyWebAppConfigurer
 * @author xz
 * @date 2018年6月3日
 *
 */
@Configuration
public class MyWebAppConfigurer extends WebMvcConfigurationSupport {

	/** 解决跨域问题 **/
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		// super.addCorsMappings(registry);
	}

	/** 添加拦截器 **/
	@Override
	protected void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new MyInterceptor());
		super.addInterceptors(registry);
	}

	/** 这里配置视图解析器 **/
	@Override
	protected void configureViewResolvers(ViewResolverRegistry registry) {
		super.configureViewResolvers(registry);
	}

	/** 配置内容裁决的一些选项 **/
	@Override
	protected void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		super.configureContentNegotiation(configurer);
	}

	/** 视图跳转控制器 **/
	@Override
	protected void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("forward:/login");
		super.addViewControllers(registry);
	}

	/** 静态资源处理 **/
	@Override
	protected void addResourceHandlers(ResourceHandlerRegistry registry) {

		registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");

	}

	/** 默认静态资源处理器 **/

	protected void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		// super.configureDefaultServletHandling(configurer);
		// configurer.enable("stati");
		configurer.enable();
	}

	@Bean
	public HttpMessageConverter<String> responseBodyConverter() {
		StringHttpMessageConverter converter = new StringHttpMessageConverter(Charset.forName("UTF-8"));
		return converter;
	}

	@Bean
	public ObjectMapper getObjectMapper() {
		return new ObjectMapper();
	}

	@Bean
	public MappingJackson2HttpMessageConverter messageConverter() {
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		converter.setObjectMapper(getObjectMapper());
		return converter;
	}

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		super.configureMessageConverters(converters);
		// 解决中文乱码
		converters.add(responseBodyConverter());
		// 解决 添加解决中文乱码后 上述配置之后，返回json数据直接报错 500：no convertter for return value
		// of type
		converters.add(messageConverter());
	}

}
