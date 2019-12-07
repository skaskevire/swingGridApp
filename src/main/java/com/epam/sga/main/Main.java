package com.epam.sga.main;

import com.epam.sga.controller.CarController;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class Main {

  public static void main(String[] args) {
    Injector injector = Guice.createInjector(new MainModule());
    CarController mc = injector.getInstance(CarController.class);
    mc.load();
  }
}
