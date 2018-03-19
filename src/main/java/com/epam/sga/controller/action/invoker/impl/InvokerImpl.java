package com.epam.sga.controller.action.invoker.impl;

import java.util.Map;

import com.epam.sga.controller.action.BFEnum;
import com.epam.sga.controller.action.BusinessFunction;
import com.epam.sga.controller.action.invoker.BusinessFunctionInvoker;
import com.epam.sga.controller.entity.ActionResult;
import com.google.inject.Inject;
import com.google.inject.name.Named;

public class InvokerImpl implements BusinessFunctionInvoker {

	private Map<BFEnum, BusinessFunction> bfm;
	@Inject
	public InvokerImpl(@Named("businessFunctions")Map<BFEnum, BusinessFunction> bfm) {
		super();
		this.bfm = bfm;
	}

	@Override
	public ActionResult invoke(String action) {
		return bfm.get(BFEnum.getByName(action)).doAction();
	}
}
