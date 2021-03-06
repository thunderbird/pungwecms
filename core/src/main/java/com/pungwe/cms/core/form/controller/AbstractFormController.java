package com.pungwe.cms.core.form.controller;

import com.pungwe.cms.core.block.BlockConfig;
import com.pungwe.cms.core.form.Form;
import com.pungwe.cms.core.form.element.FormElement;
import com.pungwe.cms.core.utils.services.HookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by ian on 19/03/2016.
 */
public abstract class AbstractFormController<T> implements Form<T> {

	@Autowired
	protected HookService hookService;

	@ModelAttribute("form")
	public FormElement buildForm() throws InvocationTargetException, IllegalAccessException {
		Assert.hasText(getFormId());
		FormElement<T> element = new FormElement<T>();
		element.setName(getFormId());
		element.setMethod("post");
		build(element);
		hookService.executeHook("form_alter", getFormId(), element);
		return element;
	}

	@InitBinder("form")
	public void initBinder(final WebDataBinder webDataBinder) {
		webDataBinder.setValidator(new Validator() {
			@Override
			public boolean supports(Class<?> clazz) {
				return FormElement.class.isAssignableFrom(clazz);
			}

			@Override
			public void validate(Object target, Errors errors) {
				AbstractFormController.this.validate(((FormElement) target), errors);
			}
		});
	}

	@Override
	public void validate(FormElement<T> form, Errors errors) {
		form.validate(errors);
	}
}
