package com.epam.sga.controller;

import com.epam.sga.service.CarService;
import com.epam.sga.view.entity.ViewsEnum;
import com.epam.sga.view.entity.ViewData;
import com.epam.sga.view.entity.ViewEntity;
import com.epam.sga.view.View;
import com.google.inject.name.Named;

import com.google.inject.Inject;
import java.util.Map;

public class CarController implements Controller, ViewNofifier<ViewEntity> {

  private Map<ViewsEnum, View> availavleViews;
  private CarService carService;

  @Inject
  public CarController(@Named("carService") CarService carService,
      @Named("availavleViews") Map<ViewsEnum, View> availavleViews) {
    this.carService = carService;
    this.availavleViews = availavleViews;
  }

  @Override
  public void load() {
    notify(ViewsEnum.TITLE, carService.load());
  }

  @Override
  public void sort(ViewData viewData) {
    notify(ViewsEnum.TITLE, carService.sort(viewData));
  }

  @Override
  public void notify(ViewsEnum viewName, ViewEntity message) {
    View view = availavleViews.get(viewName);
    if (view == null) {
      throw new RuntimeException("View state is not defined");
    }
    view.apply(message);
  }
}
