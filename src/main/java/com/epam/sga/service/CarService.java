package com.epam.sga.service;

import com.epam.sga.view.entity.ViewData;
import com.epam.sga.view.entity.ViewEntity;

public interface CarService {

  ViewEntity load();

  ViewEntity sort(ViewData viewData);
}
