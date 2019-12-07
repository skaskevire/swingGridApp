package com.epam.sga.view;

import com.epam.sga.view.entity.ViewsEnum;
import com.epam.sga.view.entity.ViewEntity;

public interface View {
	ViewsEnum getName();

	void apply(ViewEntity viewEntity);

	void deactivate();
}
