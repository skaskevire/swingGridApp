package com.epam.sga.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.epam.sga.model.entity.data.Data;
import com.epam.sga.model.entity.layout.Layout;

public class JAXBRelatedModel implements Model {
	private static final String DATA_FILE_PATH = "data.xml";
	private static final String LAYOUT_FILE_PATH = "layout.xml";

	@Override
	public Data getData() {
		JAXBContext jaxbContext = null;
		Data data = null;
		try (FileInputStream fos = new FileInputStream(DATA_FILE_PATH)) {
			jaxbContext = JAXBContext.newInstance(Data.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			data = (Data) jaxbUnmarshaller.unmarshal(fos);

		} catch (JAXBException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
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
			layout = (Layout) jaxbUnmarshaller.unmarshal(new FileInputStream(LAYOUT_FILE_PATH));

		} catch (JAXBException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return layout;
	}

}
