package org.pshow.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.BeanNameViewResolver;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

@Configuration
public class AppConfig {

	// Resolve logical view names to .jsp resources in the /WEB-INF/views
	// directory
	@Bean
	ViewResolver viewResolver() {
		ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();
		
		Map<String, String> mediaTypes = new HashMap<String, String>(3);
		mediaTypes.put("atom", "application/atom+xml");
		mediaTypes.put("html", "text/html");
		mediaTypes.put("json", "application/json");
		resolver.setMediaTypes(mediaTypes);
		
		List<ViewResolver> viewResolvers = new ArrayList<ViewResolver>(2);
		
		viewResolvers.add(new BeanNameViewResolver());
		
		InternalResourceViewResolver internalViewResolver = new InternalResourceViewResolver();
		internalViewResolver.setPrefix("views/");
		internalViewResolver.setSuffix(".jsp");
		viewResolvers.add(internalViewResolver);
		
		resolver.setViewResolvers(viewResolvers);
		List<View> defaultViews = new ArrayList<View>(1);
		defaultViews.add(new MappingJacksonJsonView());
		resolver.setDefaultViews(defaultViews);
		
		return resolver;
	}
}
