package com.epam.sga.controller.action.impl;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import com.epam.sga.controller.action.BusinessFunction;
import com.epam.sga.controller.entity.ActionResult;
import com.epam.sga.view.ViewImpl;
import com.epam.sga.view.ViewState;
import com.epam.sga.view.entity.ViewData.ViewCar;
import com.google.inject.Inject;
import com.google.inject.name.Named;

public class SortTableBF implements BusinessFunction {

	@Inject
	@Named("defaultView")
	private ViewState view;

	@Override
	public ActionResult doAction() {
		((ViewImpl) view).getData().getCar().sort(new Comparator<ViewCar>() {

			@Override
			public int compare(ViewCar o1, ViewCar o2) {
				return o1.getMake().compareTo(o2.getMake());
			}

		});
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("data", ((ViewImpl) view).getData());
		return new ActionResult("title", dataMap);
	}

}
