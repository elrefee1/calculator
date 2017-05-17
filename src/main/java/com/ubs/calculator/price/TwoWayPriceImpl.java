package com.ubs.calculator.price;

import com.ubs.calculator.enums.Instrument;
import com.ubs.calculator.enums.State;

public class TwoWayPriceImpl implements TwoWayPrice {

	private final Instrument instrument;
	private final State state;
	private final double bidPrice;
	private final double bidAmount;
	private final double offerPrice;
	private final double offerAmount;
	
	public TwoWayPriceImpl(Instrument instrument, State state, double bidPrice, double bidAmount, double offerPrice, double offerAmount) {
		this.instrument = instrument;
		this.state = state;
		this.bidPrice = bidPrice;
		this.bidAmount = bidAmount;
		this.offerPrice = offerPrice;
		this.offerAmount = offerAmount;
	}

	@Override
	public Instrument getInstrument() {
		return this.instrument;
	}

	@Override
	public State getState() {
		return this.state;
	}

	@Override
	public double getBidPrice() {
		return this.bidPrice;
	}

	@Override
	public double getOfferAmount() {
		return this.offerAmount;
	}

	@Override
	public double getOfferPrice() {
		return this.offerPrice;
	}

	@Override
	public double getBidAmount() {
		return this.bidAmount;
	}

}
