package com.epam.sga.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.epam.sga.controller.action.invoker.BusinessFunctionInvoker;
import com.epam.sga.controller.entity.ActionResult;
import com.epam.sga.view.ViewStateManager;
import com.google.inject.Inject;

public class MainController implements ActionListener {

	private ViewStateManager viewStateManager;

	private BusinessFunctionInvoker invoker;

	@Inject
	public MainController(BusinessFunctionInvoker m, ViewStateManager vsm) {
		this.invoker = m;
		this.viewStateManager = vsm;
		viewStateManager.applyListener(this);
	}

	public void loadView() {

		ActionResult result = invoker.invoke("load");
		viewStateManager.applyState(result);
	}

	@Override
	public void actionPerformed(ActionEvent action) {
		ActionResult result = invoker.invoke(action.getActionCommand());
		viewStateManager.applyState(result);
	}
}
