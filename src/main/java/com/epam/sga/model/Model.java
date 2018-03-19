package com.epam.sga.model;

import com.epam.sga.model.entity.data.Data;
import com.epam.sga.model.entity.layout.Layout;

public interface Model {
	Data getData();

	Layout getLayout();
}
