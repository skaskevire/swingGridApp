package com.epam.sga.view.state.manager;

import java.awt.event.ActionListener;

import com.epam.sga.controller.entity.ActionResult;
import com.epam.sga.view.state.ViewState;

public interface StateManager {
	void setState(String name, ViewState state);
	void applyState(ActionResult result);
	void applyListener(ActionListener l);
}
