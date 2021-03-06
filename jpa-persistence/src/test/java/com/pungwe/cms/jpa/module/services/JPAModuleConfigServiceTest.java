package com.pungwe.cms.jpa.module.services;

import com.pungwe.cms.core.module.ModuleConfig;
import com.pungwe.cms.core.module.services.ModuleConfigService;
import com.pungwe.cms.jpa.config.JPAConfiguration;
import com.pungwe.cms.modules.TestModule;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.transaction.TransactionManager;
import javax.transaction.Transactional;

import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created by ian on 22/01/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles(profiles = "jpa")
@SpringApplicationConfiguration(JPAConfiguration.class)
@Transactional
public class JPAModuleConfigServiceTest {


	@Autowired
	ModuleConfigService configService;

	@PersistenceContext
	EntityManager entityManager;

	@Test
	@Rollback
	public void testRegisterModule() {
		configService.registerModule(TestModule.class, TestModule.class.getProtectionDomain().getCodeSource().getLocation());
		entityManager.flush();
		assertNotNull("Test Module was null", configService.getModuleConfig("test_module"));
	}

	@Test()
	@Rollback
	public void testRegisterAndDelete() throws Exception {

		configService.registerModule(TestModule.class, TestModule.class.getProtectionDomain().getCodeSource().getLocation());
		entityManager.flush();

		assertNotNull("Test Module was null", configService.getModuleConfig("test_module"));

		configService.removeModules("test_module");

		entityManager.flush();

		assertNull("Module still exists", configService.getModuleConfig("test_module"));
	}

	/*

	Set<M> listAllModules();

	M getModuleConfig(String module);
	*/

	@Test
	@Rollback
	public void testListModules() {
		configService.registerModule(TestModule.class, TestModule.class.getProtectionDomain().getCodeSource().getLocation());
		entityManager.flush();
		assertEquals("Module was not registered", 1, configService.listAllModules().size());
	}

	@Test
	@Rollback
	public void testSetModuleEnabled() {
		configService.registerModule(TestModule.class, TestModule.class.getProtectionDomain().getCodeSource().getLocation());
		entityManager.flush();
		assertNotNull("Test Module was null", configService.getModuleConfig("test_module"));
		configService.setModuleEnabled("test_module", true);
		entityManager.flush();
		assertTrue("Module was not enabled", configService.getModuleConfig("test_module").isEnabled());
		assertTrue("Module was not enabled", configService.isEnabled("test_module"));
		Set<ModuleConfig> modules = configService.listEnabledModules();
		assertEquals("Module was not enabled", 1, modules.size());
	}


}
