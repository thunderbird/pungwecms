package com.pungwe.cms.core.form.element;

import com.pungwe.cms.core.annotations.ThemeInfo;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by ian on 25/02/2016.
 */
@ThemeInfo("form/multi_select")
public class MultiSelectListElement extends AbstractFormElement<List<String>> {

	protected Map<String, String> options;

	public Map<String, String> getOptions() {
		if (options == null) {
			options = new LinkedHashMap<>();
		}
		return options;
	}

	@ModelAttribute("options")
	public Set<Map.Entry<String, String>> getOptionsSet() {
		return options.entrySet();
	}

	public void addOption(String label, String value) {
		this.getOptions().put(label, value);
	}
}