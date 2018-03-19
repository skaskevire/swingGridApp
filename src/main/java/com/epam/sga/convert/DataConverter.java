package com.epam.sga.convert;

import com.epam.sga.model.entity.data.Data;
import com.epam.sga.model.entity.data.Data.Car;
import com.epam.sga.view.entity.ViewData;

public class DataConverter implements Converter<Data, ViewData> {

	@Override
	public ViewData convert(Data i) {
		ViewData vd = new ViewData();
		for (Car car : i.getCar()) {
			ViewData.ViewCar viewCar = new ViewData.ViewCar();
			viewCar.setMake(car.getMake());
			viewCar.setModel(car.getModel());
			viewCar.setYear(car.getYear());
			vd.setCar(viewCar);
		}

		return vd;
	}

}
