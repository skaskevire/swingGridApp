package com.epam.sga.view;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.epam.sga.controller.entity.ActionResult;
import com.epam.sga.view.entity.ViewData;
import com.epam.sga.view.entity.ViewLayout;
import com.epam.sga.view.entity.ViewData.ViewCar;
import com.epam.sga.view.entity.ViewLayout.ViewMenu.ViewButton;

public class ViewImpl implements ViewState {
	private Map<String, JButton> buttons = new HashMap<>();
	private ViewData data;
	private ViewLayout layout;
	private DefaultTableModel model;
	JTable table;
	JScrollPane jsp;

	private JFrame mainFrame = new JFrame("SwingGridApp");

	public ViewImpl() {
		mainFrame.setSize(300, 300);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setLayout(new GridLayout(0, 1));

	}

	@Override
	public void applyActionListener(ActionListener listener) {
		for (JButton button : buttons.values()) {
			button.addActionListener(listener);
		}

	}

	@Override
	public void refresh() {
		mainFrame.setVisible(true);
		mainFrame.repaint();

	}

	@Override
	public void deactivate() {
		mainFrame.setVisible(false);
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
		while (model.getRowCount() != 0) {
			model.removeRow(0);
		}

		for (ViewCar car : data.getCar()) {
			String[] row = { car.getMake(), car.getModel(), String.valueOf(car.getYear()) };
			model.addRow(row);
		}

		if (table != null) {
			mainFrame.remove(table);
		}

	}

	private void applyLayout(ViewLayout layout) {

		String[] columns = new String[layout.getGrid().getColumn().size()];
		for (int i = 0; i < columns.length; i++) {
			columns[i] = layout.getGrid().getColumn().get(i);
		}

		model = new DefaultTableModel(columns, 0);
		table = new JTable(model);
		if (jsp != null) {
			mainFrame.remove(jsp);
		}

		jsp = new JScrollPane(table);

		mainFrame.add(jsp);

		this.layout = layout;
		for (JButton button : buttons.values()) {
			mainFrame.remove(button);
		}
		for (ViewButton button : layout.getMenu().getButton()) {
			JButton b = new JButton(button.getValue());
			b.setActionCommand(button.getImpl());
			buttons.put(button.getValue(), b);
			mainFrame.add(b);
		}
	}

	public ViewData getData() {
		return data;
	}
}
