package com.epam.sga.controller;

import com.epam.sga.view.entity.ViewsEnum;

public interface ViewNofifier<T> {
  void notify(ViewsEnum viewName, T message);
}
