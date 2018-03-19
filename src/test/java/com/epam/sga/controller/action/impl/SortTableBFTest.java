package com.epam.sga.controller.action.impl;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Assert;
import org.junit.Test;

import com.epam.sga.controller.entity.ActionResult;
import com.epam.sga.view.entity.ViewData;
import com.epam.sga.view.state.GridViewState;

public class SortTableBFTest {
	private SortTableBF sortTableBF;
	Mockery context = new Mockery();
	private GridViewState view = context.mock(GridViewState.class);

	@Test
	public void testDoAction() {
		sortTableBF = new SortTableBF(view);
		ViewData vd = new ViewData();
		context.checking(new Expectations() {
			{
				oneOf(view).getData();
				will(returnValue(vd));
				oneOf(view).getData();
				will(returnValue(vd));
			}
		});

		ActionResult ar = sortTableBF.doAction();

		context.checking(new Expectations() {
			{
				exactly(2).of(view).getData();
			}
		});
		Assert.assertEquals(ar.getData("data"), vd);
		Assert.assertEquals(ar.getState(), "title");
	}
}
