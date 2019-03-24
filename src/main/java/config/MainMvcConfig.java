package config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@ComponentScans({ @ComponentScan("config"), @ComponentScan("entity"), @ComponentScan("service"),
		@ComponentScan("repository"), @ComponentScan("controller"), @ComponentScan("exception") })
public class MainMvcConfig extends AnnotationConfigWebApplicationContext {
}
