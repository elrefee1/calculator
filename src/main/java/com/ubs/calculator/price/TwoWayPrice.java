package com.ubs.calculator.price;

import com.ubs.calculator.enums.Instrument;
import com.ubs.calculator.enums.State;

public interface TwoWayPrice {
    Instrument getInstrument();
    State getState();
    double getBidPrice();
    double getOfferAmount();
    double getOfferPrice();
    double getBidAmount();
}


