package com.epam.sga.main;

import com.epam.sga.service.CarService;
import com.epam.sga.service.impl.CarServiceImpl;
import com.epam.sga.controller.Controller;
import com.epam.sga.controller.CarController;
import com.epam.sga.view.entity.ViewsEnum;
import com.google.inject.Singleton;
import com.google.inject.multibindings.MapBinder;
import java.util.Locale;
import java.util.ResourceBundle;

import com.epam.sga.constants.Constants;
import com.epam.sga.convert.Converter;
import com.epam.sga.convert.DataConverter;
import com.epam.sga.convert.LayoutConverter;
import com.epam.sga.model.JAXBRelatedModel;
import com.epam.sga.model.Model;
import com.epam.sga.model.entity.data.Data;
import com.epam.sga.model.entity.layout.Layout;
import com.epam.sga.view.entity.ViewData;
import com.epam.sga.view.entity.ViewLayout;
import com.epam.sga.view.View;
import com.epam.sga.view.impl.GridViewImpl;
import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import com.google.inject.name.Names;

public class MainModule extends AbstractModule {

  @Override
  protected void configure() {
    ResourceBundle messages = ResourceBundle.getBundle("messages",
        new Locale(Constants.USER_LANGUAGE, Constants.USER_COUNTRY));
    bind(ResourceBundle.class).annotatedWith(Names.named("systemMessages")).toInstance(messages);

    bind(new TypeLiteral<Converter<Data, ViewData>>() {
    }).annotatedWith(Names.named("dataConverter")).toInstance(new DataConverter());
    bind(new TypeLiteral<Converter<Layout, ViewLayout>>() {
    }).annotatedWith(Names.named("layoutConverter")).toInstance(new LayoutConverter());
    bind(Model.class).annotatedWith(Names.named("jaxbRelatedModel"))
        .toInstance(new JAXBRelatedModel());
    bind(Controller.class).annotatedWith(Names.named("mainController")).to(
        CarController.class);
    bind(CarController.class).in(Singleton.class);
    bind(CarService.class).annotatedWith(Names.named("carService")).to(
        CarServiceImpl.class);
    bind(CarServiceImpl.class).in(Singleton.class);
    bind(View.class).to(GridViewImpl.class);
    bind(GridViewImpl.class).in(Singleton.class);

    MapBinder<ViewsEnum, View> availavleViews = MapBinder.newMapBinder(
        binder(),
        ViewsEnum.class,
        View.class, Names.named("availavleViews"));

    availavleViews.addBinding(ViewsEnum.TITLE).to(GridViewImpl.class);
  }

}
