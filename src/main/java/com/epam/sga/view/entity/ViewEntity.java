package com.epam.sga.view.entity;

public class ViewEntity {

  private ViewData viewData;
  private ViewLayout viewLayout;

  public ViewEntity(ViewData viewData, ViewLayout viewLayout) {
    this.viewData = viewData;
    this.viewLayout = viewLayout;
  }

  public ViewData getViewData() {
    return viewData;
  }

  public ViewLayout getViewLayout() {
    return viewLayout;
  }
}
