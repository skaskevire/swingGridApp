package com.epam.sga.controller;

import com.epam.sga.view.entity.ViewData;

public interface Controller {

  void load();

  void sort(ViewData viewData);
}
