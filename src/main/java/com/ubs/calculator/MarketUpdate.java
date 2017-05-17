package com.ubs.calculator;

import com.ubs.calculator.enums.Market;
import com.ubs.calculator.price.TwoWayPrice;

public interface MarketUpdate {
    Market getMarket();
    TwoWayPrice getTwoWayPrice();
}

