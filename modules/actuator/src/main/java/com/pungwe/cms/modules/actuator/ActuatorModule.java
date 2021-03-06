package com.pungwe.cms.modules.actuator;

import com.pungwe.cms.core.annotations.stereotypes.Module;
import org.springframework.boot.actuate.autoconfigure.*;
import org.springframework.boot.autoconfigure.PropertyPlaceholderAutoConfiguration;
import org.springframework.boot.autoconfigure.web.DispatcherServletAutoConfiguration;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created by ian on 20/01/2016.
 */
@Module(
		name = "actuator",
		label = "Actuator",
		description = "A module for production deployment / system health based on spring boot actuator"
)
@Import({
		// Actuator
		PropertyPlaceholderAutoConfiguration.class,
		ManagementServerPropertiesAutoConfiguration.class,
		EndpointMBeanExportAutoConfiguration.class,
		HealthIndicatorAutoConfiguration.class,
		AuditAutoConfiguration.class,
		CacheStatisticsAutoConfiguration.class,
		EndpointAutoConfiguration.class,
		EndpointWebMvcAutoConfiguration.class,
		TraceWebFilterAutoConfiguration.class,
		TraceRepositoryAutoConfiguration.class,
})
@EnableWebMvc
@ComponentScan(basePackages = "com.pungwe.cms.modules.actuator")
public class ActuatorModule {
}
