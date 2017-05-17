package com.ubs.calculator.price;

import com.ubs.calculator.enums.Instrument;
import com.ubs.calculator.enums.Market;

/**
 * {@link PriceCache} is used to store prices for {@link Instrument}s
 * Using a 2-dimensional array to allow for O(1) access to elements. Indexed by {@link Instrument} and {@link Market}
 * */
public class PriceCache {
	private TwoWayPrice[][] twoWayPriceArray;
	
	public PriceCache() {
		int instrumentsCount = Instrument.values().length;
		int marketsCount = Market.values().length;
		
		this.twoWayPriceArray = new TwoWayPrice[instrumentsCount][marketsCount];
	}

	public void addPrice(Instrument instrument, Market market, TwoWayPrice twoWayPrice) {
		int instrumentIndex = instrument.getIndex();
		int marketIndex = market.getIndex();
		
		this.twoWayPriceArray[instrumentIndex][marketIndex] = twoWayPrice;
	}
	
	public TwoWayPrice[] getPrices(Instrument instrument) {
		int index = instrument.getIndex();
		TwoWayPrice[] twoWayPrices = this.twoWayPriceArray[index];
		
		return twoWayPrices;
	}
	
}
