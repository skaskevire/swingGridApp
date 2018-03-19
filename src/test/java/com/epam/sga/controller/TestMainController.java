package com.epam.sga.controller;

import java.awt.event.ActionEvent;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Before;
import org.junit.Test;

import com.epam.sga.controller.action.BFEnum;
import com.epam.sga.controller.action.invoker.BusinessFunctionInvoker;
import com.epam.sga.controller.entity.ActionResult;
import com.epam.sga.view.state.manager.StateManager;

public class TestMainController {
	private MainController mainController;
	Mockery context = new Mockery();
	final BusinessFunctionInvoker bfi = context.mock(BusinessFunctionInvoker.class);
	final StateManager vsm = context.mock(StateManager.class);
	ActionResult result = new ActionResult(null, null);

	@Before
	public void init() {
		context.checking(new Expectations() {
			{
				oneOf(vsm).applyListener((MainController) with(any(MainController.class)));
			}
		});
		mainController = new MainController(bfi, vsm);
	}

	@Test
	public void testLoadView() {
		configureMocks(BFEnum.LOAD.getName());

		mainController.loadView();

		verifyResult(BFEnum.LOAD.getName());
	}

	@Test
	public void testActionPerformed() {
		ActionEvent action = new ActionEvent("", 0, "command");
		configureMocks("command");

		mainController.actionPerformed(action);

		verifyResult("command");
	}

	private void configureMocks(String command) {
		context.checking(new Expectations() {
			{
				oneOf(bfi).invoke(with(command));
				will(returnValue(result));
				oneOf(vsm).applyState(result);
			}
		});
	}

	private void verifyResult(String command) {
		context.checking(new Expectations() {
			{
				exactly(1).of(bfi).invoke(with(command));
				exactly(1).of(vsm).applyState(with(result));
			}
		});
	}
}
