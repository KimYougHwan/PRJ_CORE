/**
 * 
 */
package co.kr.kyhgh.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 *	
 * @Author : yhkim
 * @Date   : 2017. 4. 11.
 */

@Configuration
@PropertySources(value = {@PropertySource("classpath:config/properties/db.properties") ,@PropertySource("classpath:config/properties/system.properties")})
public class PropertyConfig {
	
//	@Autowired
//	private Environment env;
//	
//	@PostConstruct
//    public void initializeDatabasePropertySourceUsage() {
//        MutablePropertySources propertySources = ((ConfigurableEnvironment) env).getPropertySources();
//
//        try {
//            //dataSource, Table Name, Key Column, Value Column
//            DatabaseConfiguration databaseConfiguration = new DatabaseConfiguration(dataSource(), "AppConfiguration", "propertyKey", "propertyValue");
//            
//            //CommonsConfigurationFactoryBean comes from https://java.net/projects/springmodules/sources/svn/content/tags/release-0_8/projects/commons/src/java/org/springmodules/commons/configuration/CommonsConfigurationFactoryBean.java?rev=2110
//            //Per https://jira.spring.io/browse/SPR-10213 I chose to just copy the raw source into our project
//            CommonsConfigurationFactoryBean commonsConfigurationFactoryBean = new CommonsConfigurationFactoryBean(databaseConfiguration);
//            
//            Properties dbProps = (Properties) commonsConfigurationFactoryBean.getObject();
//            PropertiesPropertySource dbPropertySource = new PropertiesPropertySource("dbPropertySource", dbProps);
//
//            //By being First, Database Properties take precedence over all other properties that have the same key name
//            //You could put this last, or just in front of the application.properties if you wanted to...
//            propertySources.addFirst(dbPropertySource);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
	
	
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer(){
		PropertySourcesPlaceholderConfigurer pspc = new PropertySourcesPlaceholderConfigurer();
		pspc.setIgnoreUnresolvablePlaceholders(true);
		return pspc;
	}

}
