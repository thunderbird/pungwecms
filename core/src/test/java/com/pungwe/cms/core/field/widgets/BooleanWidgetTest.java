/*
 * Copyright (c) 2016. Ian Michell
 */

package com.pungwe.cms.core.field.widgets;

import com.pungwe.cms.config.TestConfig;
import com.pungwe.cms.core.config.BaseApplicationConfig;
import com.pungwe.cms.core.element.RenderedElement;
import com.pungwe.cms.core.entity.FieldConfig;
import com.pungwe.cms.core.form.element.CheckboxElement;
import com.pungwe.cms.core.form.element.RadioElement;
import com.pungwe.cms.core.form.element.SingleSelectListElement;
import com.pungwe.cms.core.form.element.TextElement;
import com.pungwe.cms.test.AbstractWebTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration({TestConfig.class, BaseApplicationConfig.class})
@WebAppConfiguration("src/main/resources")
public class BooleanWidgetTest extends AbstractWebTest {

    @Autowired
    BooleanWidget booleanWidget;

    @Test
    public void testBooleanWidgetFormRadioDefaultValue() {

        Map<String, Object> settings = new LinkedHashMap<>();
        settings.putAll(booleanWidget.getDefaultSettings());
        settings.put("type", "radio");
        settings.put("true_label", "Yes");
        settings.put("false_label", "No");
        settings.put("value", "true");

        List<RenderedElement> elements = new ArrayList<>();
        FieldConfig config = new FieldConfig();
        config.setName("yes_no");
        config.setSettings(settings);
        booleanWidget.buildWidgetForm(elements, config, null, 0);
        assertEquals(1, elements.size());
        RadioElement element = (RadioElement)elements.get(0);
        assertTrue(RadioElement.class.isAssignableFrom(element.getClass()));
        assertTrue(Boolean.valueOf(element.getDefaultValue()));
        assertEquals("yes_no", element.getName());
    }

    @Test
    public void testBooleanWidgetFormRadioNegativeValue() {

        Map<String, Object> settings = new LinkedHashMap<>();
        settings.putAll(booleanWidget.getDefaultSettings());
        settings.put("type", "radio");
        settings.put("true_label", "Yes");
        settings.put("false_label", "No");
        settings.put("checked_by_default", "true");

        List<RenderedElement> elements = new ArrayList<>();
        FieldConfig config = new FieldConfig();
        config.setName("yes_no");
        config.setSettings(settings);
        booleanWidget.buildWidgetForm(elements, config, false, 0);
        assertEquals(1, elements.size());
        RadioElement element = (RadioElement)elements.get(0);
        assertTrue(RadioElement.class.isAssignableFrom(element.getClass()));
        assertFalse(Boolean.valueOf(element.getDefaultValue()));
        assertEquals("yes_no", element.getName());
    }

    @Test
    public void testBooleanWidgetFormSelectDefaultValue() {

        Map<String, Object> settings = new LinkedHashMap<>();
        settings.putAll(booleanWidget.getDefaultSettings());
        settings.put("type", "select");
        settings.put("true_label", "Yes");
        settings.put("false_label", "No");
        settings.put("value", "true");

        List<RenderedElement> elements = new ArrayList<>();
        FieldConfig config = new FieldConfig();
        config.setName("yes_no");
        config.setSettings(settings);
        booleanWidget.buildWidgetForm(elements, config, null, 0);
        assertEquals(1, elements.size());
        SingleSelectListElement element = (SingleSelectListElement)elements.get(0);
        assertTrue(SingleSelectListElement.class.isAssignableFrom(element.getClass()));
        assertTrue(Boolean.valueOf(element.getDefaultValue()));
        assertEquals("yes_no", element.getName());
    }

    @Test
    public void testBooleanWidgetFormSelectNegativeValue() {

        Map<String, Object> settings = new LinkedHashMap<>();
        settings.putAll(booleanWidget.getDefaultSettings());
        settings.put("type", "select");
        settings.put("true_label", "Yes");
        settings.put("false_label", "No");
        settings.put("checked_by_default", "true");

        List<RenderedElement> elements = new ArrayList<>();
        FieldConfig config = new FieldConfig();
        config.setName("yes_no");
        config.setSettings(settings);
        booleanWidget.buildWidgetForm(elements, config, false, 0);
        assertEquals(1, elements.size());
        SingleSelectListElement element = (SingleSelectListElement)elements.get(0);
        assertTrue(SingleSelectListElement.class.isAssignableFrom(element.getClass()));
        assertFalse(Boolean.valueOf(element.getDefaultValue()));
        assertEquals("yes_no", element.getName());
    }

    @Test
    public void testBooleanWidgetFormCheckboxDefaultValue() {

        Map<String, Object> settings = new LinkedHashMap<>();
        settings.putAll(booleanWidget.getDefaultSettings());
        settings.put("type", "checkbox");
        settings.put("true_label", "Yes");
        settings.put("false_label", "No");
        settings.put("value", "true");

        List<RenderedElement> elements = new ArrayList<>();
        FieldConfig config = new FieldConfig();
        config.setName("yes_no");
        config.setSettings(settings);
        booleanWidget.buildWidgetForm(elements, config, null, 0);
        assertEquals(1, elements.size());
        CheckboxElement element = (CheckboxElement)elements.get(0);
        assertTrue(CheckboxElement.class.isAssignableFrom(element.getClass()));
        assertTrue(element.isChecked());
        assertEquals("yes_no", element.getName());
    }

    @Test
    public void testBooleanWidgetFormCheckboxRandomType() {

        Map<String, Object> settings = new LinkedHashMap<>();
        settings.putAll(booleanWidget.getDefaultSettings());
        settings.put("type", "random");
        settings.put("true_label", "Yes");
        settings.put("false_label", "No");
        settings.put("value", "true");

        List<RenderedElement> elements = new ArrayList<>();
        FieldConfig config = new FieldConfig();
        config.setName("yes_no");
        config.setSettings(settings);
        booleanWidget.buildWidgetForm(elements, config, null, 0);
        assertEquals(1, elements.size());
        CheckboxElement element = (CheckboxElement)elements.get(0);
        assertTrue(CheckboxElement.class.isAssignableFrom(element.getClass()));
        assertTrue(element.isChecked());
        assertEquals("yes_no", element.getName());
    }

    @Test
    public void testBooleanWidgetFormCheckboxNoType() {

        Map<String, Object> settings = new LinkedHashMap<>();
        settings.putAll(booleanWidget.getDefaultSettings());
        settings.put("type", null);
        settings.put("true_label", "Yes");
        settings.put("false_label", "No");
        settings.put("value", "true");

        List<RenderedElement> elements = new ArrayList<>();
        FieldConfig config = new FieldConfig();
        config.setName("yes_no");
        config.setSettings(settings);
        booleanWidget.buildWidgetForm(elements, config, null, 0);
        assertEquals(1, elements.size());
        CheckboxElement element = (CheckboxElement)elements.get(0);
        assertTrue(CheckboxElement.class.isAssignableFrom(element.getClass()));
        assertTrue(element.isChecked());
        assertEquals("yes_no", element.getName());
    }

    @Test
    public void testBooleanWidgetFormCheckboxNegativeValue() {

        Map<String, Object> settings = new LinkedHashMap<>();
        settings.putAll(booleanWidget.getDefaultSettings());
        settings.put("type", "checkbox");
        settings.put("true_label", "Yes");
        settings.put("false_label", "No");
        settings.put("value", "true");

        List<RenderedElement> elements = new ArrayList<>();
        FieldConfig config = new FieldConfig();
        config.setName("yes_no");
        config.setSettings(settings);
        booleanWidget.buildWidgetForm(elements, config, false, 0);
        assertEquals(1, elements.size());
        CheckboxElement element = (CheckboxElement)elements.get(0);
        assertTrue(CheckboxElement.class.isAssignableFrom(element.getClass()));
        assertFalse(element.isChecked());
        assertEquals("yes_no", element.getName());
    }

    @Test
    public void testBuildWidgetSettingsForm() {

        List<RenderedElement> elements = new ArrayList<>();
        booleanWidget.buildWidgetSettingsForm(elements, booleanWidget.getDefaultSettings());
        assertEquals(3, elements.size());
        assertEquals("checkbox", ((SingleSelectListElement)elements.get(0)).getValueOrDefaultValue());
        assertEquals("True", ((TextElement) elements.get(1)).getValueOrDefaultValue());
        assertEquals("False", ((TextElement)elements.get(2)).getValueOrDefaultValue());

    }
}
