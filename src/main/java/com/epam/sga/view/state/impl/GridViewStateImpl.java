package com.epam.sga.view.state.impl;

import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.inject.Inject;
import javax.inject.Named;
import javax.swing.JButton;

import com.epam.sga.controller.entity.ActionResult;
import com.epam.sga.view.entity.ViewData;
import com.epam.sga.view.entity.ViewLayout;
import com.epam.sga.view.entity.ViewData.ViewCar;
import com.epam.sga.view.state.GridViewState;

import static com.epam.sga.constants.Constants.*;
public class GridViewStateImpl implements GridViewState {
	private ViewData data;
	private GridWindow window;
	
	@Inject
	public GridViewStateImpl(@Named("systemMessages")ResourceBundle messages) {
		window = new GridWindow(
				messages.getString(WINDOW_HEADER_NAME_PROP),
				GRID_WINDOW_MIN_WIDTH,
				GRID_WINDOW_MIN_HEIGHT);
	}

	@Override
	public String getName() {
		return "title";
	}

	@Override
	public void applyActionResult(ActionResult result) {
		if (result.getData("layout") != null) {
			applyLayout(result.getData("layout"));
		}
		applyData(result.getData("data"));
	}

	private void applyData(ViewData data) {
		this.data = data;
		while (window.getRowCount() != 0) {
			window.removeTableRow(0);
		}

		for (ViewCar car : data.getCar()) {
			String[] row = { car.getMake(), car.getModel(), String.valueOf(car.getYear()) };
			window.putTableRow(row);
		}
	}

	private void applyLayout(ViewLayout layout) {
		String[] columns = new String[layout.getGrid().getColumn().size()];
		for (int i = 0; i < columns.length; i++) {
			columns[i] = layout.getGrid().getColumn().get(i);
		}
		window.putTableBasement(columns);
		window.putButtons(layout.getMenu().getButton());
	}


	@Override
	public void applyActionListener(ActionListener listener) {
		for (JButton button : window.getButtons().values()) {

			for (ActionListener l : button.getActionListeners()) {
				if (!l.getClass().equals(listener.getClass())) {
					button.addActionListener(listener);
				}
			}
			if (button.getActionListeners().length == 0) {
				button.addActionListener(listener);
			}
		}
	}

	@Override
	public void refresh() {
		window.setVisible(true);
		window.repaint();
	}

	@Override
	public void deactivate() {
		window.setVisible(false);
	}

	@Override
	public ViewData getData() {
		return data;
	}
}
