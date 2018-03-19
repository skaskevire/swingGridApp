package com.epam.sga.view.entity;

import java.util.ArrayList;
import java.util.List;

public class ViewLayout {

	protected ViewLayout.ViewGrid grid;
	protected ViewLayout.ViewMenu menu;

	public ViewLayout.ViewGrid getGrid() {
		return grid;
	}

	public void setGrid(ViewLayout.ViewGrid value) {
		this.grid = value;
	}

	public ViewLayout.ViewMenu getMenu() {
		return menu;
	}

	public void setMenu(ViewLayout.ViewMenu value) {
		this.menu = value;
	}

	public static class ViewGrid {

		protected List<String> columns = new ArrayList<>();

		public List<String> getColumn() {
			if (columns == null) {
				columns = new ArrayList<String>();
			}
			return this.columns;
		}

		public void addColumn(String column) {
			columns.add(column);
		}

	}

	public static class ViewMenu {

		protected List<ViewLayout.ViewMenu.ViewButton> buttons = new ArrayList<>();

		public List<ViewLayout.ViewMenu.ViewButton> getButton() {
			if (buttons == null) {
				buttons = new ArrayList<ViewLayout.ViewMenu.ViewButton>();
			}
			return this.buttons;
		}

		public void addButton(ViewButton button) {
			buttons.add(button);
		}

		public static class ViewButton {

			protected String value;
			protected String impl;

			public String getValue() {
				return value;
			}

			public void setValue(String value) {
				this.value = value;
			}

			public String getImpl() {
				return impl;
			}

			public void setImpl(String value) {
				this.impl = value;
			}

		}

	}

}
