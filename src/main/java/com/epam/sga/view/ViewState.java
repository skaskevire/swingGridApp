package com.epam.sga.view;

import java.awt.event.ActionListener;

import com.epam.sga.controller.entity.ActionResult;

public interface ViewState {
	String getName();

	void applyActionResult(ActionResult result);

	void applyActionListener(ActionListener listener);

	void refresh();

	void deactivate();
}
