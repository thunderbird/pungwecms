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
public class TextfieldWidgetTest extends AbstractWebTest {

    @Autowired
    TextfieldWidget textfieldWidget;

    @Test
    public void testSettingsFormDefaultValue() throws Exception {
        List<RenderedElement> elements = new ArrayList<>();
        Map<String, Object> settings = new LinkedHashMap<>();
        settings.putAll(textfieldWidget.getDefaultSettings());
        settings.put("value", "text");
        settings.put("field_size", "20");
        textfieldWidget.buildWidgetSettingsForm(elements, settings);
        assertEquals(1, elements.size());
        assertEquals("20", ((TextElement)elements.get(0)).getValueOrDefaultValue());
    }

    @Test
    public void testWidgetForm() throws Exception {
        List<RenderedElement> elements = new ArrayList<>();

        FieldConfig config = new FieldConfig();
        config.setName("textfield");
        config.setLabel("Textfield");
        config.setCardinality(1);
        config.setSettings(textfieldWidget.getDefaultSettings());

        textfieldWidget.buildWidgetForm(elements, config, "text", 0);

        assertEquals(1, elements.size());
        assertEquals("text", ((TextElement)elements.get(0)).getValue());
    }

    @Test
    public void testWidgetFormDefaultValue() throws Exception {
        List<RenderedElement> elements = new ArrayList<>();

        FieldConfig config = new FieldConfig();
        config.setName("textfield");
        config.setLabel("Textfield");
        config.setCardinality(1);

        Map<String, Object> settings = new LinkedHashMap<>();
        settings.putAll(textfieldWidget.getDefaultSettings());
        settings.put("value", "text");

        config.setSettings(settings);

        textfieldWidget.buildWidgetForm(elements, config, null, 0);

        assertEquals(1, elements.size());
        assertEquals("text", ((TextElement)elements.get(0)).getValueOrDefaultValue());
    }

}
