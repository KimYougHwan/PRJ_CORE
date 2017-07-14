package co.kr.kyhgh.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.BeanNameViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.fasterxml.jackson.databind.ObjectMapper;

import co.kr.kyhgh.interceptor.LoginInterceptor;

@Configuration
@EnableWebMvc
@ComponentScan("co.kr.medialog.prj")
public class WebMvcConfig extends WebMvcConfigurerAdapter{

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry){
		registry.addResourceHandler("/resources/**").addResourceLocations("/WEB-INF/resources/");
	}
	
	/*
	@Bean
	public ViewResolver contentNegotiatingViewResolver(ContentNegotiationManager manager) {
        ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();
        resolver.setContentNegotiationManager(manager);
 
        List < ViewResolver > resolvers = new ArrayList < ViewResolver > ();
        
        resolvers.add(setupViewResolver());
        resolvers.add(jsonView());
        
        resolver.setViewResolvers(resolvers);
        
        return resolver;
	}
	*/
	
	@Bean
	public ViewResolver setupViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/jsp/");
		resolver.setSuffix(".jsp");
		resolver.setContentType("text/html; charset=UTF-8");
		resolver.setOrder(1);
		
		resolver.setViewClass(JstlView.class);
		return resolver;
	}
	
	@Bean
	public BeanNameViewResolver beanNameViewResolver(){
		BeanNameViewResolver resolver = new BeanNameViewResolver();
		resolver.setOrder(0);
		return resolver;
		
	}
	
	
	@Bean
	public CommonsMultipartResolver multipartResolver(){
		CommonsMultipartResolver mResolver = new CommonsMultipartResolver();
		mResolver.setDefaultEncoding("UTF-8");
		mResolver.setMaxUploadSize(10000000);
		return mResolver;
		
	}
	
	@Bean
	public MappingJackson2JsonView jsonView(){
		MappingJackson2JsonView jsonView = new MappingJackson2JsonView();
		jsonView.setPrefixJson(false);
		jsonView.setPrettyPrint(true);
		jsonView.setObjectMapper(jsonMapper());
		jsonView.setContentType("application/json;charset=utf-8");
		return jsonView;
		
	}
	
	@Bean
	public ObjectMapper jsonMapper(){
		ObjectMapper jsonMapper = new ObjectMapper();
		return jsonMapper;
	}
	
		
	/*
	@Bean
	public EmbeddedServletContainerCustomizer containerCustomizer() {
		return configurableEmbeddedServletContainer -> configurableEmbeddedServletContainer.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/404.html"));
	}

*/
    @Override
    public void addInterceptors(InterceptorRegistry registry)   {
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**").excludePathPatterns("/" ,"/loginCheck"  ,"/logout" , "/error");
    }
    
}