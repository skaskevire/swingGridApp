package com.epam.sga.view.entity;

import java.util.ArrayList;
import java.util.List;

public class ViewData {

	protected List<ViewData.ViewCar> cars = new ArrayList<>();

	public List<ViewData.ViewCar> getCar() {
		if (cars == null) {
			cars = new ArrayList<ViewData.ViewCar>();
		}
		return this.cars;
	}

	public void setCar(ViewCar car) {
		cars.add(car);
	}

	public static class ViewCar {

		protected String make;
		protected String model;
		protected short year;

		public String getMake() {
			return make;
		}

		public void setMake(String value) {
			this.make = value;
		}

		public String getModel() {
			return model;
		}

		public void setModel(String value) {
			this.model = value;
		}

		public short getYear() {
			return year;
		}

		public void setYear(short value) {
			this.year = value;
		}

	}

}
