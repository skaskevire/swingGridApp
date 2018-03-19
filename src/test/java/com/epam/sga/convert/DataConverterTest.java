package com.epam.sga.convert;

import org.junit.Assert;
import org.junit.Test;

import com.epam.sga.model.entity.data.Data;
import com.epam.sga.view.entity.ViewData;

public class DataConverterTest {
	private Converter<Data, ViewData> c = new DataConverter();
	
	@Test
	public void testConvert()
	{
		Data data = new Data();
		ViewData vd = c.convert(data);
		
		Assert.assertNotNull(vd);
		Assert.assertEquals(vd.getCar().size(), data.getCar().size());
	}
}
