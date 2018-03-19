package com.epam.sga.controller.action.impl;

import java.util.HashMap;
import java.util.Map;

import com.epam.sga.controller.action.BusinessFunction;
import com.epam.sga.controller.entity.ActionResult;
import com.epam.sga.convert.Converter;
import com.epam.sga.model.Model;
import com.epam.sga.model.entity.data.Data;
import com.epam.sga.model.entity.layout.Layout;
import com.epam.sga.view.entity.ViewData;
import com.epam.sga.view.entity.ViewLayout;
import com.google.inject.Inject;
import com.google.inject.name.Named;

public class LoadBF implements BusinessFunction {
	@Inject
	@Named("jaxbRelatedModel")
	private Model model;

	@Inject
	@Named("dataConverter")
	private Converter<Data, ViewData> dataConverter;

	@Inject
	@Named("layoutConverter")
	private Converter<Layout, ViewLayout> layoutConverter;

	@Override
	public ActionResult doAction() {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("data", dataConverter.convert(model.getData()));
		dataMap.put("layout", layoutConverter.convert(model.getLayout()));
		return new ActionResult("title", dataMap);
	}

}
