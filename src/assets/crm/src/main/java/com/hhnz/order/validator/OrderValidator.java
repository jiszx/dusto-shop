package com.hhnz.order.validator;

import java.util.List;

import com.hhnz.order.model.OmOrderHeadersAll;
import com.hhnz.order.model.OmOrderLinesAll;

public interface OrderValidator {

	String validateOrderAdd(OmOrderHeadersAll orderh, List<OmOrderLinesAll> orderlines);

	String validateLine(OmOrderLinesAll lines);

}
