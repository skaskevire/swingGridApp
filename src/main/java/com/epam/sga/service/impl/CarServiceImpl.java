package com.epam.sga.service.impl;

import com.epam.sga.convert.Converter;
import com.epam.sga.model.Model;
import com.epam.sga.model.entity.data.Data;
import com.epam.sga.model.entity.layout.Layout;
import com.epam.sga.service.CarService;
import com.epam.sga.view.entity.ViewData;
import com.epam.sga.view.entity.ViewData.ViewCar;
import com.epam.sga.view.entity.ViewEntity;
import com.epam.sga.view.entity.ViewLayout;
import com.google.inject.Inject;
import com.google.inject.name.Named;
import java.util.Comparator;

public class CarServiceImpl implements CarService {

  private Model model;
  private Converter<Data, ViewData> dataConverter;
  private Converter<Layout, ViewLayout> layoutConverter;

  @Inject
  public CarServiceImpl(@Named("jaxbRelatedModel") Model model,
      @Named("dataConverter") Converter<Data, ViewData> dataConverter,
      @Named("layoutConverter") Converter<Layout, ViewLayout> layoutConverter) {
    super();
    this.model = model;
    this.dataConverter = dataConverter;
    this.layoutConverter = layoutConverter;
  }

  @Override
  public ViewEntity load() {
    return new ViewEntity(dataConverter.convert(model.getData()),
        layoutConverter.convert(model.getLayout()));
  }

  @Override
  public ViewEntity sort(ViewData viewData) {
    viewData.getCar().sort(Comparator.comparing(ViewCar::getMake));
    return new ViewEntity(viewData, null);
  }
}
