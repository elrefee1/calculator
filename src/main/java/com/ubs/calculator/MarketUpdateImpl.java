package com.ubs.calculator;

import com.ubs.calculator.enums.Market;
import com.ubs.calculator.price.TwoWayPrice;

public class MarketUpdateImpl implements MarketUpdate {

	private final Market market;
	private final TwoWayPrice twoWayPrice;

	public MarketUpdateImpl(Market market, TwoWayPrice twoWayPrice) {
		this.market = market;
		this.twoWayPrice = twoWayPrice;
	}
	
	@Override
	public Market getMarket() {
		return this.market;
	}

	@Override
	public TwoWayPrice getTwoWayPrice() {
		return this.twoWayPrice;
	}

}
