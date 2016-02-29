package com.pungwe.cms.core.form.element;

import com.pungwe.cms.core.annotations.ThemeInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Collection;
import java.util.LinkedList;
import java.util.stream.Collectors;

/**
 * Created by ian on 09/01/2016.
 */
@ThemeInfo("form/string")
public class StringElement extends AbstractFormElement<String> {

	protected int size = 60;

	public StringElement() {
	}

	@ModelAttribute("size")
	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	@Override
	protected Collection<String> excludedAttributes() {
		Collection<String> excluded = new LinkedList<>(super.excludedAttributes());
		excluded.add("size");
		return excluded;
	}
}
