package com.ubs.calculator;

import com.ubs.calculator.enums.Instrument;
import com.ubs.calculator.enums.Market;
import com.ubs.calculator.enums.State;
import com.ubs.calculator.price.PriceCache;
import com.ubs.calculator.price.TwoWayPrice;
import com.ubs.calculator.price.TwoWayPriceImpl;

public class VWAPCalculator implements Calculator {
	
	private final PriceCache priceCache;

	public VWAPCalculator(PriceCache priceCache) {
		this.priceCache = priceCache;		
	}

	@Override
	public TwoWayPrice applyMarketUpdate(MarketUpdate marketUpdate) {
		Market market = marketUpdate.getMarket();
		TwoWayPrice twoWayPrice = marketUpdate.getTwoWayPrice();
		
		Instrument instrument = twoWayPrice.getInstrument();
		
		this.priceCache.addPrice(instrument, market, twoWayPrice);
		
		TwoWayPrice[] prices = this.priceCache.getPrices(instrument);
		
		TwoWayPrice vwapTwoWayPrice = calculateVwapPrice(instrument, prices);

		return vwapTwoWayPrice;
		
	}

	/**
	 * Calculates VWAP Price given an array of {@link TwoWayPrice}s for an {@link Instrument}
	 * The following assumptions are made:
	 *  - If there is a single indicative price in the array, the vwap price is INDICATIVE
	 *  - If there is no amount for the given price, it is not included in the calculation
	 *  - If there are no amounts for any prices, this will result in a NaN VWAP price 
	 * */
	private TwoWayPrice calculateVwapPrice(Instrument instrument, TwoWayPrice[] prices) {
		double bidPriceAmountSum = 0;
		double bidAmountSum = 0;
		double offerPriceAmountSum = 0;
		double offerAmountSum = 0;
		
		State state = State.FIRM;
		
		for (TwoWayPrice twoWayPrice : prices) {			
			double bidAmount = twoWayPrice.getBidAmount();
			if (bidAmount != 0) {
				double bidPrice = twoWayPrice.getBidPrice();
				double bidPriceAmount = bidPrice * bidAmount;
				
				bidAmountSum += bidAmount;
				bidPriceAmountSum += bidPriceAmount;
			}
			
			double offerAmount = twoWayPrice.getOfferAmount();
			if (offerAmount != 0) {
				double offerPrice = twoWayPrice.getOfferPrice();
				double offerPriceAmount = offerPrice * offerAmount;
				
				offerAmountSum += offerAmount;
				offerPriceAmountSum += offerPriceAmount;
			}
			
			State priceState = twoWayPrice.getState();
			if (priceState.equals(State.INDICATIVE) == true) {
				state = State.INDICATIVE;
			}
		}
		
		double vwapBid = Double.NaN;
		if(bidAmountSum != 0) {
			vwapBid = bidPriceAmountSum / bidAmountSum;
		}
		
		double vwapOffer = Double.NaN;
		if(offerAmountSum != 0) {
			vwapOffer = offerPriceAmountSum / offerAmountSum;
		}
		
		TwoWayPriceImpl result = new TwoWayPriceImpl(instrument, state, vwapBid, bidAmountSum, vwapOffer, offerAmountSum);
		
		return result;
	}

}
