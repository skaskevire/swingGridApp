package com.epam.sga.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.epam.sga.model.entity.data.Data;
import com.epam.sga.model.entity.layout.Layout;

public class JAXBRelatedModel implements Model {

	@Override
	public Data getData() {
		JAXBContext jaxbContext = null;
		Data data = null;
		try {
			jaxbContext = JAXBContext.newInstance(Data.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			data = (Data) jaxbUnmarshaller.unmarshal(new FileInputStream("data.xml"));

		} catch (JAXBException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return data;
	}

	@Override
	public Layout getLayout() {
		JAXBContext jaxbContext = null;
		Layout layout = null;
		try {
			jaxbContext = JAXBContext.newInstance(Layout.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			layout = (Layout) jaxbUnmarshaller.unmarshal(new FileInputStream("layout.xml"));

		} catch (JAXBException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return layout;
	}

}
