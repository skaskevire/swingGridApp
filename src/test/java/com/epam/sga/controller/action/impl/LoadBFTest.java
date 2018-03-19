package com.epam.sga.controller.action.impl;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Assert;
import org.junit.Test;

import com.epam.sga.controller.entity.ActionResult;
import com.epam.sga.convert.Converter;
import com.epam.sga.model.Model;
import com.epam.sga.model.entity.data.Data;
import com.epam.sga.model.entity.layout.Layout;
import com.epam.sga.view.entity.ViewData;
import com.epam.sga.view.entity.ViewLayout;

public class LoadBFTest {
	private LoadBF loadBF;
	Mockery context = new Mockery();
	private Model model = context.mock(Model.class);
	
	@SuppressWarnings("rawtypes")
	private Converter converter = context.mock(Converter.class);

	@Test
	@SuppressWarnings("unchecked")
	public void testDoAction()
	{
		ViewData vd = new ViewData();
		ViewLayout vl = new ViewLayout();
		loadBF = new LoadBF(model, converter, converter);
		context.checking(new Expectations() {
			{
				oneOf(converter).convert(with(any(Data.class)));
				will(returnValue(vd));
				oneOf(converter).convert(with(any(Layout.class)));
				will(returnValue(vl));
				oneOf(model).getData();
				will(returnValue(new Data()));
				oneOf(model).getLayout();
				will(returnValue(new Layout()));
			}
		});
		
		ActionResult ar = loadBF.doAction();
		
		context.checking(new Expectations() {
			{
				exactly(1).of(converter).convert(with(any(ViewData.class)));
				exactly(1).of(converter).convert(with(any(ViewLayout.class)));
				exactly(1).of(model).getData();
				exactly(1).of(model).getLayout();
			}
		});
		Assert.assertEquals(ar.getState(), "title");
		Assert.assertEquals(ar.getData("data"), vd);
		Assert.assertEquals(ar.getData("layout"), vl);
	}
}
