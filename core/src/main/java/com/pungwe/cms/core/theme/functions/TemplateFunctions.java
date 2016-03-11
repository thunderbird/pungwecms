package com.pungwe.cms.core.theme.functions;

import com.lyncode.jtwig.functions.annotations.JtwigFunction;
import com.lyncode.jtwig.functions.annotations.Parameter;
import com.lyncode.jtwig.functions.exceptions.FunctionException;
import com.lyncode.jtwig.util.render.RenderHttpServletResponse;
import com.pungwe.cms.core.element.RenderedElement;
import com.pungwe.cms.core.element.services.RenderedElementService;
import com.pungwe.cms.core.utils.services.HookService;
import org.springframework.context.ApplicationContext;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ian on 14/02/2016.
 */
public class TemplateFunctions {

	private ApplicationContext applicationContext;
	private ViewResolver viewResolver;
	private LocaleResolver localeResolver;

	public TemplateFunctions(ApplicationContext context, ViewResolver resolver, LocaleResolver localeResolver) {
		this.applicationContext = context;
		this.viewResolver = resolver;
		this.localeResolver = localeResolver;
	}

	@JtwigFunction(name = "render")
	public <T extends RenderedElement> String render (HttpServletRequest request, @Parameter String template) throws FunctionException {
		return render(request, template, new HashMap<>());
	}

	@JtwigFunction(name = "render")
	public <T extends RenderedElement> String render (HttpServletRequest request, @Parameter String template, @Parameter Map<String, ?> parameters) throws FunctionException {
		if (StringUtils.isEmpty(template)) {
			return "";
		}
		ModelAndView modelAndView = new ModelAndView(template, parameters);
		return render(request, modelAndView);
	}

	@JtwigFunction(name = "render")
	public <T extends RenderedElement> String render (HttpServletRequest request, @Parameter Collection<T> input) throws FunctionException {
		StringBuilder html = new StringBuilder();
		for (T element : input) {
			html.append(render(request, element)).append("\n");
		}
		return html.toString();
	}

	@JtwigFunction(name = "render")
	public <T extends RenderedElement> String render (HttpServletRequest request, @Parameter T input) throws FunctionException {
		try {
			RenderedElementService renderedElementService = applicationContext.getBean(RenderedElementService.class);
			HookService hookService = applicationContext.getBean(HookService.class);
			hookService.executeHook("element_alter", input);
			ModelAndView modelAndView = renderedElementService.convertToModelAndView(input);
			return render(request, modelAndView);
		} catch (Exception ex) {
			throw new FunctionException(ex);
		}
	}

	@JtwigFunction(name = "render")
	public <T extends ModelAndView> String render(HttpServletRequest request, @Parameter T model) throws FunctionException {
		RenderHttpServletResponse responseWrapper = new RenderHttpServletResponse();
		try {
			if (model.getView() == null && StringUtils.isEmpty(model.getViewName())) {
				throw new FunctionException("No view has been found");
			}
			View view = viewResolver.resolveViewName(model.getViewName(), localeResolver.resolveLocale(request));
			view.render(model.getModel(), request, responseWrapper);
			return responseWrapper.toString();
		} catch (Exception ex) {
			throw new FunctionException(ex);
		}
	}

	@JtwigFunction(name = "printAttribute")
	public String printAttribute(@Parameter String name, @Parameter String value) {
		return !StringUtils.isEmpty(value) ? " " + name + "=\"" + value + "\"" : "";
	}

	@JtwigFunction(name = "printAttribute")
	public <T extends Number> String printAttribute(@Parameter String name, @Parameter T value) {
		return value != null ? " " + name + "=\"" + value + "\"" : "";
	}

	@JtwigFunction(name = "printAttribute")
	public String printAttribute(@Parameter String name, @Parameter Boolean value) {
		return value != null ? " " + name + "=\"" + value + "\"" : "";
	}


}
