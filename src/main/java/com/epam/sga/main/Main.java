package com.epam.sga.main;

import java.io.FileNotFoundException;

import javax.xml.bind.JAXBException;

import com.epam.sga.controller.MainController;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class Main {
	// todo: apply i18n
	// todo: enums, constants, clean code
	public static void main(String[] args) throws JAXBException, FileNotFoundException {       
		Injector injector = Guice.createInjector(new MainModule());
		MainController mc = injector.getInstance(MainController.class);
		mc.loadView();
	}
}
