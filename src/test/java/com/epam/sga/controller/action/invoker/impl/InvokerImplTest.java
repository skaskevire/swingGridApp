package com.epam.sga.controller.action.invoker.impl;

import java.util.Map;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Assert;
import org.junit.Test;

import com.epam.sga.controller.action.BFEnum;
import com.epam.sga.controller.action.BusinessFunction;
import com.epam.sga.controller.entity.ActionResult;

public class InvokerImplTest {
	private InvokerImpl invokerImpl;
	Mockery context = new Mockery();
	@SuppressWarnings("unchecked")
	private Map<BFEnum, BusinessFunction> businessFunctions = (Map<BFEnum, BusinessFunction>) context.mock(Map.class);
	private BusinessFunction businessFunction = context.mock(BusinessFunction.class);

	@Test
	public void testInvoke() {
		ActionResult actionResult = new ActionResult(null, null);
		invokerImpl = new InvokerImpl(businessFunctions);
		context.checking(new Expectations() {
			{
				oneOf(businessFunctions).get(BFEnum.LOAD);
				will(returnValue(businessFunction));
				oneOf(businessFunction).doAction();
				will(returnValue(actionResult));
			}
		});

		ActionResult actual = invokerImpl.invoke("load");

		context.checking(new Expectations() {
			{
				exactly(1).of(businessFunctions).get(BFEnum.LOAD);
			}
		});
		Assert.assertEquals(actionResult, actual);
	}
}
