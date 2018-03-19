package com.epam.sga.convert;

import com.epam.sga.model.entity.layout.Layout;
import com.epam.sga.model.entity.layout.Layout.Menu.Button;
import com.epam.sga.view.entity.ViewLayout;
import com.epam.sga.view.entity.ViewLayout.ViewGrid;
import com.epam.sga.view.entity.ViewLayout.ViewMenu;
import com.epam.sga.view.entity.ViewLayout.ViewMenu.ViewButton;

public class LayoutConverter implements Converter<Layout, ViewLayout> {

	@Override
	public ViewLayout convert(Layout i) {
		ViewLayout vl = new ViewLayout();
		ViewGrid vg = new ViewGrid();
		for (String column : i.getGrid().getColumn()) {

			vg.addColumn(column);
		}
		vl.setGrid(vg);
		ViewMenu vm = new ViewMenu();
		for (Button b : i.getMenu().getButton()) {
			ViewButton vb = new ViewButton();
			vb.setImpl(b.getImpl());
			vb.setValue(b.getValue());
			vm.addButton(vb);
		}

		vl.setMenu(vm);
		return vl;
	}

}
