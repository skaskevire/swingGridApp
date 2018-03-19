package com.epam.sga.view;

import java.awt.event.ActionListener;
import java.util.Map;

import com.epam.sga.controller.entity.ActionResult;
import com.google.inject.Inject;
import com.google.inject.name.Named;

public class ViewStateManager {
	@Inject
	@Named("defaultView")
	private ViewState currentState;

	@Inject
	@Named("statesAvailable")
	private Map<String, ViewState> statesAvailable;

	private ActionListener listener;

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
		if (listener != null) {
			currentState.applyActionListener(listener);
		}

		currentState.refresh();
	}

	public void applyListener(ActionListener l) {
		this.listener = l;
	}
}
