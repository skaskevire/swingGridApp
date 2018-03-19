package com.epam.sga.controller.entity;

import java.util.Map;

public class ActionResult {
	private String viewState;
	private Map<String, Object> data;

	public ActionResult(String viewState, Map<String, Object> data) {
		super();
		this.viewState = viewState;
		this.data = data;
	}

	@SuppressWarnings("unchecked")
	public <T> T getData(String key) {
		return (T) data.get(key);
	}

	public String getState() {
		return viewState;
	}
}
