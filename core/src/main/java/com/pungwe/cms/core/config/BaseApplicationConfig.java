package com.pungwe.cms.core.config;

import com.pungwe.cms.core.block.services.BlockManagementService;
import com.pungwe.cms.core.module.services.ModuleManagementService;
import com.pungwe.cms.core.theme.services.ThemeManagementService;
import com.pungwe.cms.core.utils.services.HookService;
import org.springframework.boot.autoconfigure.PropertyPlaceholderAutoConfiguration;
import org.springframework.boot.autoconfigure.cache.CacheAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by ian on 20/01/2016.
 */
@Configuration()
@Import({
        PropertyPlaceholderAutoConfiguration.class,
        CacheAutoConfiguration.class
})
// We only want to scan the core package
//@ComponentScan(
//        basePackages = {"com.pungwe.cms.core"},
//        includeFilters = {
//                @ComponentScan.Filter(value = Service.class)
//        },
//        excludeFilters = {
//                @ComponentScan.Filter(value = ModuleContextConfig.class,
//                        type = FilterType.ASSIGNABLE_TYPE),
//                @ComponentScan.Filter(value = Block.class),
//                @ComponentScan.Filter(value = Theme.class),
//                @ComponentScan.Filter(value = Controller.class),
//                @ComponentScan.Filter(value = RequestMapping.class),
//                @ComponentScan.Filter(value = FieldType.class),
//                @ComponentScan.Filter(value = FieldWidget.class),
//                @ComponentScan.Filter(value = FieldFormatter.class)
//        }
//)
@EnableConfigurationProperties
@EnableCaching
public class BaseApplicationConfig {

    /**
     * Returns the list of default enabled modules.
     *
     * @return a list of default enabled modules
     */
    @Bean
    @ConfigurationProperties(prefix = "modules.enabled")
    public List<String> defaultEnabledModules() {
        return new LinkedList<String>();
    }

    /**
     * Configures the module management service.
     *
     * @return the module management service
     */
    @Bean
    public ModuleManagementService moduleManagementService() {
        return new ModuleManagementService();
    }

    @Bean
    public ThemeManagementService themeManagementService() {
        return new ThemeManagementService();
    }

    @Bean
    public HookService hookService() {
        return new HookService();
    }
}
