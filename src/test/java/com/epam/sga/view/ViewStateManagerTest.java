package com.epam.sga.view;

import java.awt.event.ActionListener;
import java.util.Map;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Before;
import org.junit.Test;

import com.epam.sga.controller.entity.ActionResult;
import com.epam.sga.view.state.ViewState;
import com.epam.sga.view.state.manager.ViewStateManager;

public class ViewStateManagerTest {
	private ViewStateManager vsm;
	Mockery context = new Mockery();
	private ViewState currentState = context.mock(ViewState.class);
	private ActionListener listener = context.mock(ActionListener.class);
	@SuppressWarnings("unchecked")
	private Map<String, ViewState> statesAvailable = (Map<String, ViewState>) context.mock(Map.class);

	@Before
	public void init() {
		vsm = new ViewStateManager(currentState, statesAvailable);
		vsm.applyListener(listener);
	}

	@Test
	public void testSetState() {
		context.checking(new Expectations() {
			{
				oneOf(statesAvailable).put("title", currentState);
			}
		});

		vsm.setState("title", currentState);

		context.checking(new Expectations() {
			{
				exactly(1).of(statesAvailable).put("title", currentState);
			}
		});
	}

	@Test
	public void testApplyStateWhenStateIsNotChanging() {
		ActionResult result = new ActionResult("title", null);
		context.checking(new Expectations() {
			{
				oneOf(currentState).getName();
				will(returnValue("title"));
				oneOf(currentState).applyActionResult(result);
				oneOf(currentState).applyActionListener(listener);
				oneOf(currentState).refresh();

			}
		});
		vsm.applyState(result);
		context.checking(new Expectations() {
			{
				exactly(1).of(currentState).getName();
				exactly(1).of(currentState).applyActionResult(result);
				exactly(1).of(currentState).applyActionListener(listener);
				exactly(1).of(currentState).refresh();
			}
		});
	}

	@Test
	public void testApplyStateWhenStateChanging() {
		ActionResult result = new ActionResult("error", null);
		context.checking(new Expectations() {
			{
				oneOf(currentState).getName();
				will(returnValue("title"));
				oneOf(currentState).deactivate();
				oneOf(statesAvailable).get("error");
				// let's assume that is error state
				will(returnValue(currentState));
				oneOf(currentState).applyActionResult(result);
				oneOf(currentState).applyActionListener(listener);
				oneOf(currentState).refresh();
			}
		});
		vsm.applyState(result);
		context.checking(new Expectations() {
			{
				exactly(1).of(currentState).getName();
				exactly(1).of(currentState).deactivate();
				exactly(1).of(statesAvailable).get("error");
				exactly(1).of(currentState).applyActionResult(result);
				exactly(1).of(currentState).applyActionListener(listener);
				exactly(1).of(currentState).refresh();
			}
		});
	}
}
