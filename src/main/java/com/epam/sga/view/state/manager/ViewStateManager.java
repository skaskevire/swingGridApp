package com.epam.sga.view.state.manager;

import java.awt.event.ActionListener;
import java.util.Map;

import com.epam.sga.controller.entity.ActionResult;
import com.epam.sga.view.state.ViewState;
import com.google.inject.Inject;
import com.google.inject.name.Named;

public class ViewStateManager implements StateManager {

	private ViewState currentState;

	private Map<String, ViewState> statesAvailable;

	private ActionListener listener;

	@Inject
	public ViewStateManager(@Named("defaultView") ViewState currentState,
			@Named("statesAvailable") Map<String, ViewState> statesAvailable) {
		super();
		this.currentState = currentState;
		this.statesAvailable = statesAvailable;
	}

	public void setState(String name, ViewState state) {
		statesAvailable.put(name, state);
	}

	public void applyState(ActionResult result) {
		if (currentState.getName().equals(result.getState())) {
			currentState.applyActionResult(result);
		} else {
			currentState.deactivate();
			currentState = statesAvailable.get(result.getState());
			currentState.applyActionResult(result);
		}
		currentState.applyActionListener(listener);
		currentState.refresh();
	}

	public void applyListener(ActionListener l) {
		this.listener = l;
	}
}
