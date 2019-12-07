package com.epam.sga.view.impl;

import com.epam.sga.controller.Controller;
import com.epam.sga.view.entity.BFEnum;
import com.epam.sga.view.entity.ViewsEnum;
import com.epam.sga.view.entity.ViewEntity;
import com.epam.sga.view.View;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.inject.Inject;
import javax.inject.Named;
import javax.swing.JButton;

import com.epam.sga.view.entity.ViewData;
import com.epam.sga.view.entity.ViewLayout;
import com.epam.sga.view.entity.ViewData.ViewCar;

import static com.epam.sga.constants.Constants.*;
public class GridViewImpl implements View, ActionListener {
	private ViewData data;
	private GridWindow window;
	private Controller controller;


	@Override
	public void actionPerformed(ActionEvent action) {
		switch (BFEnum.getByName(action.getActionCommand())) {
			case LOAD:
				controller.load();
				break;
			case SORT_GRID_BY_MAKE:
				controller.sort(data);
				break;
			default:
				throw new RuntimeException("Action not specified");
		}
	}

	@Inject
	public GridViewImpl(@Named("systemMessages")ResourceBundle messages, @Named("mainController") Controller controller) {
		window = new GridWindow(
				messages.getString(WINDOW_HEADER_NAME_PROP),
				GRID_WINDOW_MIN_WIDTH,
				GRID_WINDOW_MIN_HEIGHT);
		this.controller = controller;
	}

	@Override
	public ViewsEnum getName() {
		return ViewsEnum.TITLE;
	}

	@Override
	public void apply(ViewEntity viewEntity) {
		if (viewEntity.getViewLayout() != null) {
			applyLayout(viewEntity.getViewLayout());
		}
		applyData(viewEntity.getViewData());
		applyActionListener(this);
		refresh();
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

	public void refresh() {
		window.setVisible(true);
		window.repaint();
	}

	@Override
	public void deactivate() {
		window.setVisible(false);
	}
}
