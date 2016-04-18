/*
 * Copyright (c) 2016. Ian Michell
 */

package com.pungwe.cms.core.block.system;


import com.pungwe.cms.config.TestConfig;
import com.pungwe.cms.core.config.BaseApplicationConfig;
import com.pungwe.cms.core.element.RenderedElement;
import com.pungwe.cms.core.element.model.ModelAndViewElement;
import com.pungwe.cms.core.menu.MenuInfo;
import com.pungwe.cms.core.menu.services.MenuManagementService;
import com.pungwe.cms.test.AbstractWebTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration({TestConfig.class, BaseApplicationConfig.class})
@WebAppConfiguration("src/main/resources")
public class SystemTasksBlockTest extends AbstractWebTest {

    @Autowired
    SystemTasksBlock systemTasksBlock;

    @Autowired
    MenuManagementService menuManagementService;


    @Test
    public void testWithNoMenu() {
        List<RenderedElement> elements = new ArrayList<>();
        systemTasksBlock.build(elements, new HashMap<>(), new HashMap<>());
        assertEquals(0, elements.size());
        assertEquals(0, systemTasksBlock.getDefaultSettings().size());
    }

    @Test
    public void testWithMenuAndNoTasks() {
        // Build a menu
        MenuInfo menuInfo = menuManagementService.createMenu("My Menu", "", "en");
        menuManagementService.saveMenuInfo(menuInfo);

        menuManagementService.createMenuItem(menuInfo.getId(), "", "home", "Homepage", "", false, "_self", "/", 0, false);

        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(
                new MockHttpServletRequest(RequestMethod.GET.name(), "/")));
        RequestContextHolder.currentRequestAttributes().setAttribute(
                HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE, "/", RequestAttributes.SCOPE_REQUEST);

        Map<String, Object> model = new HashMap<>();
        Map<String, Object> settings = new HashMap<>();
        settings.put("menu", menuInfo.getId());
        List<RenderedElement> elements = new LinkedList<>();
        systemTasksBlock.build(elements, settings, model);
        assertEquals(0, elements.size());
    }

    @Test
    public void testWithMenuAndNoURLVariables() {

        // Build a menu
        MenuInfo menuInfo = menuManagementService.createMenu("My Menu", "", "en");
        menuManagementService.saveMenuInfo(menuInfo);

        menuManagementService.createMenuItem(menuInfo.getId(), "", "home", "Homepage", "", false, "_self", "/", 0, false);
        menuManagementService.createMenuItem(menuInfo.getId(), "home", "task", "Task", "", false, "_self", "/task/{task}", 0, false, true);

        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(
                new MockHttpServletRequest(RequestMethod.GET.name(), "/")));
        RequestContextHolder.currentRequestAttributes().setAttribute(
                HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE, "/", RequestAttributes.SCOPE_REQUEST);

        Map<String, Object> model = new HashMap<>();
        Map<String, Object> settings = new HashMap<>();
        settings.put("menu", menuInfo.getId());
        List<RenderedElement> elements = new LinkedList<>();
        systemTasksBlock.build(elements, settings, model);
        assertEquals(0, elements.size());
    }

    @Test
    public void testWithMenuAndURLVariables() {

        // Build a menu
        MenuInfo menuInfo = menuManagementService.createMenu("My Menu", "", "en");
        menuManagementService.saveMenuInfo(menuInfo);

        menuManagementService.createMenuItem(menuInfo.getId(), "", "home", "Homepage", "", false, "_self", "/", 0, false);
        menuManagementService.createMenuItem(menuInfo.getId(), "home", "task", "Task", "", false, "_self", "/task/{task}", 0, false, true);

        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(
                new MockHttpServletRequest(RequestMethod.GET.name(), "/")));
        RequestContextHolder.currentRequestAttributes().setAttribute(
                HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE, "/", RequestAttributes.SCOPE_REQUEST);

        Map<String, Object> model = new HashMap<>();
        model.put("task", "1234");
        Map<String, Object> settings = new HashMap<>();
        settings.put("menu", menuInfo.getId());
        List<RenderedElement> elements = new LinkedList<>();
        systemTasksBlock.build(elements, settings, model);
        assertEquals(1, elements.size());
    }

    @Test
    public void testWithMenuAndContentMap() {

        // Build a menu
        MenuInfo menuInfo = menuManagementService.createMenu("My Menu", "", "en");
        menuManagementService.saveMenuInfo(menuInfo);

        menuManagementService.createMenuItem(menuInfo.getId(), "", "home", "Homepage", "", false, "_self", "/", 0, false);
        menuManagementService.createMenuItem(menuInfo.getId(), "home", "task", "Task", "", false, "_self", "/task/{task}", 0, false, true);

        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(
                new MockHttpServletRequest(RequestMethod.GET.name(), "/")));
        RequestContextHolder.currentRequestAttributes().setAttribute(
                HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE, "/", RequestAttributes.SCOPE_REQUEST);

        Map<String, Object> model = new HashMap<>();
        model.put("content", new HashMap<>());
        Map<String, Object> settings = new HashMap<>();
        settings.put("menu", menuInfo.getId());
        List<RenderedElement> elements = new LinkedList<>();
        systemTasksBlock.build(elements, settings, model);
        assertEquals(0, elements.size());
    }

    @Test
    public void testWithMenuAndURLVariablesModelAndView() {

        // Build a menu
        MenuInfo menuInfo = menuManagementService.createMenu("My Menu", "", "en");
        menuManagementService.saveMenuInfo(menuInfo);

        menuManagementService.createMenuItem(menuInfo.getId(), "", "home", "Homepage", "", false, "_self", "/", 0, false);
        menuManagementService.createMenuItem(menuInfo.getId(), "home", "task", "Task", "", false, "_self", "/task/{task}", 0, false, true);

        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(
                new MockHttpServletRequest(RequestMethod.GET.name(), "/")));
        RequestContextHolder.currentRequestAttributes().setAttribute(
                HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE, "/", RequestAttributes.SCOPE_REQUEST);

        Map<String, Object> model = new HashMap<>();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("task", "1234");
        model.put("content", modelAndView);
        Map<String, Object> settings = new HashMap<>();
        settings.put("menu", menuInfo.getId());
        List<RenderedElement> elements = new LinkedList<>();
        systemTasksBlock.build(elements, settings, model);
        assertEquals(1, elements.size());
    }

    @Test
    public void testWithMenuAndURLVariablesModelAndViewElement() {

        // Build a menu
        MenuInfo menuInfo = menuManagementService.createMenu("My Menu", "", "en");
        menuManagementService.saveMenuInfo(menuInfo);

        menuManagementService.createMenuItem(menuInfo.getId(), "", "home", "Homepage", "", false, "_self", "/", 0, false);
        menuManagementService.createMenuItem(menuInfo.getId(), "home", "task", "Task", "", false, "_self", "/task/{task}", 0, false, true);

        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(
                new MockHttpServletRequest(RequestMethod.GET.name(), "/")));
        RequestContextHolder.currentRequestAttributes().setAttribute(
                HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE, "/", RequestAttributes.SCOPE_REQUEST);

        Map<String, Object> model = new HashMap<>();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("task", "1234");
        model.put("content", new ModelAndViewElement(modelAndView));
        Map<String, Object> settings = new HashMap<>();
        settings.put("menu", menuInfo.getId());
        List<RenderedElement> elements = new LinkedList<>();
        systemTasksBlock.build(elements, settings, model);
        assertEquals(1, elements.size());
    }

    @Test
    public void testWithMenuAndURLVariablesModelAndViewElementPath() {

        // Build a menu
        MenuInfo menuInfo = menuManagementService.createMenu("My Menu", "", "en");
        menuManagementService.saveMenuInfo(menuInfo);

        menuManagementService.createMenuItem(menuInfo.getId(), "", "home", "Homepage", "", false, "_self", "/", 0, false);
        menuManagementService.createMenuItem(menuInfo.getId(), "home", "task", "Task", "", false, "_self", "/task/{task}", 0, false, true);

        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(
                new MockHttpServletRequest(RequestMethod.GET.name(), "/task/1234")));
        RequestContextHolder.currentRequestAttributes().setAttribute(
                HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE, "/task/1234", RequestAttributes.SCOPE_REQUEST);
        RequestContextHolder.currentRequestAttributes().setAttribute(
                HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE, "/task/{task}", RequestAttributes.SCOPE_REQUEST);

        Map<String, Object> model = new HashMap<>();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("task", "1234");
        model.put("content", new ModelAndViewElement(modelAndView));
        Map<String, Object> settings = new HashMap<>();
        settings.put("menu", menuInfo.getId());
        List<RenderedElement> elements = new LinkedList<>();
        systemTasksBlock.build(elements, settings, model);
        assertEquals(1, elements.size());
    }
}
