package com.epam.sga.main;

import com.epam.sga.controller.action.BusinessFunction;
import com.epam.sga.controller.action.impl.LoadBF;
import com.epam.sga.controller.action.impl.SortTableBF;
import com.epam.sga.controller.action.invoker.BusinessFunctionInvoker;
import com.epam.sga.controller.action.invoker.impl.InvokerImpl;
import com.epam.sga.convert.Converter;
import com.epam.sga.convert.DataConverter;
import com.epam.sga.convert.LayoutConverter;
import com.epam.sga.model.JAXBRelatedModel;
import com.epam.sga.model.Model;
import com.epam.sga.model.entity.data.Data;
import com.epam.sga.model.entity.layout.Layout;
import com.epam.sga.view.ViewImpl;
import com.epam.sga.view.ViewState;
import com.epam.sga.view.entity.ViewData;
import com.epam.sga.view.entity.ViewLayout;
import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import com.google.inject.multibindings.MapBinder;
import com.google.inject.name.Names;

public class MainModule extends AbstractModule{

	@Override
	protected void configure() {
		bind(new TypeLiteral<Converter<Data, ViewData>>() {}).annotatedWith(Names.named("dataConverter")).toInstance(new DataConverter());
		bind(new TypeLiteral<Converter<Layout, ViewLayout>>() {}).annotatedWith(Names.named("layoutConverter")).toInstance(new LayoutConverter());
		bind(Model.class).annotatedWith(Names.named("jaxbRelatedModel")).toInstance(new JAXBRelatedModel());

		
		MapBinder<String, BusinessFunction> bfmBinder = MapBinder.newMapBinder(
                  binder(), 
                  String.class, 
                  BusinessFunction.class, Names.named("businessFunctions"));

		  bfmBinder.addBinding("load").to(LoadBF.class);
		  bfmBinder.addBinding("SortTableFunction").to(SortTableBF.class);

		 bind(BusinessFunctionInvoker.class).to(InvokerImpl.class);
		 
		 ViewState vi = new ViewImpl();
		 bind(ViewState.class).annotatedWith(Names.named("defaultView")).toInstance(vi);
		  MapBinder<String, ViewState> wf1Binder = MapBinder.newMapBinder(
                  binder(), 
                  String.class, 
                  ViewState.class, Names.named("statesAvailable"));

		  wf1Binder.addBinding(vi.getName()).toInstance(vi);
	}

}
