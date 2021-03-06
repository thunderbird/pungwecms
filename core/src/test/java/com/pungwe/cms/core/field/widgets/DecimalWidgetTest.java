package com.pungwe.cms.core.field.widgets;

import com.pungwe.cms.config.TestConfig;
import com.pungwe.cms.core.config.BaseApplicationConfig;
import com.pungwe.cms.core.element.RenderedElement;
import com.pungwe.cms.core.entity.FieldConfig;
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

import static org.junit.Assert.assertEquals;

/**
 * Created by 917903 on 21/04/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration({TestConfig.class, BaseApplicationConfig.class})
@WebAppConfiguration("src/main/resources")
public class DecimalWidgetTest extends AbstractWebTest {

    @Autowired
    DecimalWidget decimalWidget;

    @Test
    public void testSettingsFormDefaultValue() throws Exception {
        List<RenderedElement> elements = new ArrayList<>();
        Map<String, Object> settings = new LinkedHashMap<>();
        settings.putAll(decimalWidget.getDefaultSettings());
        settings.put("default_value", "1234.557890");
        decimalWidget.buildWidgetSettingsForm(elements, settings);
        assertEquals(2, elements.size());
        assertEquals("2", ((TextElement)elements.get(0)).getDefaultValue());
        assertEquals("1234.56", ((TextElement)elements.get(1)).getValueOrDefaultValue());
    }

    @Test
    public void testWidgetForm() throws Exception {
        List<RenderedElement> elements = new ArrayList<>();

        FieldConfig config = new FieldConfig();
        config.setName("decimal");
        config.setCardinality(1);
        config.setSettings(decimalWidget.getDefaultSettings());

        decimalWidget.buildWidgetForm(elements, config, 1234.5789, 0);

        assertEquals(1, elements.size());
        assertEquals("1234.58", ((TextElement)elements.get(0)).getValue());
    }

    @Test
    public void testWidgetFormDefaultValue() throws Exception {
        List<RenderedElement> elements = new ArrayList<>();

        FieldConfig config = new FieldConfig();
        config.setName("decimal");
        config.setCardinality(1);

        Map<String, Object> settings = new LinkedHashMap<>();
        settings.putAll(decimalWidget.getDefaultSettings());
        settings.put("default_value", "1234.557890");

        config.setSettings(settings);

        decimalWidget.buildWidgetForm(elements, config, null, 0);

        assertEquals(1, elements.size());
        assertEquals("1234.56", ((TextElement)elements.get(0)).getValueOrDefaultValue());
    }
}
