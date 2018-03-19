package com.epam.sga.controller.action.invoker;

import com.epam.sga.controller.entity.ActionResult;

public interface BusinessFunctionInvoker {
	ActionResult invoke(String action);
}
