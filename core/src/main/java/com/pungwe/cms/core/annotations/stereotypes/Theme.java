package com.pungwe.cms.core.annotations.stereotypes;

import com.pungwe.cms.core.annotations.system.ModuleDependency;
import com.pungwe.cms.core.system.element.templates.PageElement;
import org.springframework.context.annotation.Configuration;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by ian on 13/12/2015.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Configuration
public @interface Theme {

	String name();

	String label() default "";

	String description() default "";

	/**
	 * Used to specify the parent theme. This is to allow sub-theme creation
	 *
	 * @return the name of the parent theme
	 */
	String parent() default "";

	ThemeRegion[] regions() default {};

	ModuleDependency[] dependencies() default {};
}
