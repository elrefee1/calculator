package com.ubs.calculator;

import com.ubs.calculator.price.TwoWayPrice;

public interface Calculator {
    TwoWayPrice applyMarketUpdate(final MarketUpdate marketUpdate);
}

