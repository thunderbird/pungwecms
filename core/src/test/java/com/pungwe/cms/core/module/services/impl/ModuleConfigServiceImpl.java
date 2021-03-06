package com.pungwe.cms.core.module.services.impl;

import com.pungwe.cms.core.annotations.stereotypes.Module;
import com.pungwe.cms.core.module.ModuleConfig;
import com.pungwe.cms.core.module.services.ModuleConfigService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by ian on 21/01/2016.
 */
@Service
public class ModuleConfigServiceImpl implements ModuleConfigService<ModuleConfigImpl> {

    // Set of modules detected on the classpath
    public static Set<ModuleConfig> modules = new HashSet<>();

    static {
        ModuleConfigImpl missing = new ModuleConfigImpl();
        missing.setName("missing");
        missing.setEnabled(true);
        missing.setEntryPoint("com.pungwe.cms.modules.missing.Missing");
        missing.setModuleLocation("file://somewhere");
        missing.setVersion("1.0.0-SNAPSHOT");
        modules.add(missing);
    }

    @Override
    public ModuleConfigImpl registerModule(Class<?> entryPoint, URL moduleLocation) {

        // Create module config holder
        ModuleConfigImpl config = new ModuleConfigImpl();

        Module moduleInfo = entryPoint.getAnnotation(Module.class);

        /// Set the name of the module
        config.setName(StringUtils.isEmpty(moduleInfo.name()) ? entryPoint.getSimpleName().toLowerCase() : moduleInfo.name());
        config.setEntryPoint(entryPoint.getName()); // class and package
        config.setEnabled(false);
        config.setModuleLocation(moduleLocation.getFile());

        // add to the list of modules
        modules.add(config);

        return config;
    }

    @Override
    public boolean isEnabled(String module) {
        if (StringUtils.isEmpty(module)) {
            return false;
        }
        return listEnabledModules().parallelStream().filter(m -> m != null && module.equals(m.getName())).findFirst().isPresent();
    }

    @Override
    public void removeModules(String... modules) {
        removeModules(Arrays.asList(modules));
    }

    @Override
    public void removeModules(Collection<String> modules) {
        modules.forEach(i -> ModuleConfigServiceImpl.modules.removeIf(m -> m.getName().equalsIgnoreCase(i)));
    }

    @Override
    public void setModuleEnabled(String moduleName, boolean enabled) {
        modules.stream().filter(m -> m.getName().equalsIgnoreCase(moduleName)).forEach(m -> {
            m.setEnabled(enabled);
        });
    }

    @Override
    public void setInstalled(String module, boolean installed) {
        getModuleConfig(module).setInstalled(installed);
    }

    @Override
    public Set<ModuleConfig> listAllModules() {
        return modules;
    }

    @Override
    public Set<ModuleConfig> listEnabledModules() {
        return modules.stream().filter(m -> m.isEnabled()).collect(Collectors.toSet());
    }

    @Override
    public ModuleConfigImpl getModuleConfig(String module) {
        Optional<ModuleConfigImpl> config = listAllModules().parallelStream().filter(m -> m.getName()
                .equalsIgnoreCase(module)).map(m -> (ModuleConfigImpl) m).findFirst();
        return config.orElse(null);
    }
}
