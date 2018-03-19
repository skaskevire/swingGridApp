package com.epam.sga.main;

import java.util.Locale;
import java.util.ResourceBundle;

import com.epam.sga.constants.Constants;
import com.epam.sga.controller.action.BFEnum;
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
import com.epam.sga.view.entity.ViewData;
import com.epam.sga.view.entity.ViewLayout;
import com.epam.sga.view.state.ViewState;
import com.epam.sga.view.state.impl.GridViewStateImpl;
import com.epam.sga.view.state.manager.StateManager;
import com.epam.sga.view.state.manager.ViewStateManager;
import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import com.google.inject.multibindings.MapBinder;
import com.google.inject.name.Names;

public class MainModule extends AbstractModule{

	@Override
	protected void configure() {
		ResourceBundle messages = ResourceBundle.getBundle("messages",
				new Locale(Constants.USER_LANGUAGE, Constants.USER_COUNTRY));
		bind(ResourceBundle.class).annotatedWith(Names.named("systemMessages")).toInstance(messages);

		bind(new TypeLiteral<Converter<Data, ViewData>>() {}).annotatedWith(Names.named("dataConverter")).toInstance(new DataConverter());
		bind(new TypeLiteral<Converter<Layout, ViewLayout>>() {}).annotatedWith(Names.named("layoutConverter")).toInstance(new LayoutConverter());
		bind(Model.class).annotatedWith(Names.named("jaxbRelatedModel")).toInstance(new JAXBRelatedModel());

		
		MapBinder<BFEnum, BusinessFunction> businessFunctionMap = MapBinder.newMapBinder(
                  binder(), 
                  BFEnum.class, 
                  BusinessFunction.class, Names.named("businessFunctions"));

		businessFunctionMap.addBinding(BFEnum.LOAD).to(LoadBF.class);
		businessFunctionMap.addBinding(BFEnum.SORT_GRID_BY_MAKE).to(SortTableBF.class);

		 bind(BusinessFunctionInvoker.class).to(InvokerImpl.class);
		 bind(StateManager.class).to(ViewStateManager.class);
		 ViewState vi = new GridViewStateImpl(messages);
		 bind(ViewState.class).annotatedWith(Names.named("defaultView")).toInstance(vi);
		  MapBinder<String, ViewState> statesMap = MapBinder.newMapBinder(
                  binder(), 
                  String.class, 
                  ViewState.class, Names.named("statesAvailable"));

		  statesMap.addBinding(vi.getName()).toInstance(vi);
		  
	}

}
