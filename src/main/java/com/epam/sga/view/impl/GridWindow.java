package com.epam.sga.view.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.epam.sga.view.entity.ViewLayout.ViewMenu.ViewButton;

public class GridWindow {
	private Map<String, JButton> buttons = new HashMap<>();
	private DefaultTableModel model;
	private JTable table;
	private JScrollPane jsp;
	private JFrame mainFrame;

	public GridWindow(String name, int width, int height) {
		mainFrame = new JFrame(name);
		mainFrame.setSize(width, height);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setLayout(new BoxLayout(mainFrame.getContentPane(), BoxLayout.Y_AXIS));
	}

	public void setVisible(boolean visible) {
		mainFrame.setVisible(visible);
	}

	public void repaint() {
		mainFrame.repaint();
	}

	public void putTableBasement(String[] columns) {
		model = new DefaultTableModel(columns, 0);
		table = new JTable(model);
		if (jsp != null) {
			mainFrame.remove(jsp);
		}

		jsp = new JScrollPane(table);

		mainFrame.add(jsp);
	}

	public void putTableRow(String[] data) {
		model.addRow(data);
	}

	public void removeTableRow(int index) {
		model.removeRow(index);
	}

	public int getRowCount() {
		return model.getRowCount();
	}

	public void putButtons(List<ViewButton> vbl) {
		for (JButton button : buttons.values()) {
			mainFrame.remove(button);
		}
		for (ViewButton button : vbl) {
			JButton b = new JButton(button.getValue());
			b.setActionCommand(button.getImpl());
			buttons.put(button.getValue(), b);
			mainFrame.add(b);
		}
	}
	
	public Map<String, JButton> getButtons()
	{
		return buttons;
	}
}