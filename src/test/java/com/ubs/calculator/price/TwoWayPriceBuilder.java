package com.ubs.calculator.price;

import com.ubs.calculator.enums.Instrument;
import com.ubs.calculator.enums.State;

public class TwoWayPriceBuilder {
	
	private Instrument instrument = Instrument.INSTRUMENT0;
	private State state = State.INDICATIVE;
	private double bidPrice = 0d;
	private double bidAmount = 0d;
	private double offerPrice = 0d;
	private double offerAmount = 0d;
	
	public TwoWayPriceBuilder() {
		// do nothing
	}
	
	public TwoWayPrice build() {
		TwoWayPriceImpl twoWayPrice = new TwoWayPriceImpl(this.instrument, this.state, this.bidPrice, this.bidAmount, this.offerPrice, this.offerAmount);
		
		return twoWayPrice;
	}

	public TwoWayPriceBuilder setInstrument(Instrument instrument) {
		this.instrument = instrument;
		return this;
	}

	public TwoWayPriceBuilder setState(State state) {
		this.state = state;
		return this;
	}

	public TwoWayPriceBuilder setBidPrice(double bidPrice) {
		this.bidPrice = bidPrice;
		return this;
	}

	public TwoWayPriceBuilder setBidAmount(double bidAmount) {
		this.bidAmount = bidAmount;
		return this;
	}

	public TwoWayPriceBuilder setOfferPrice(double offerPrice) {
		this.offerPrice = offerPrice;
		return this;
	}

	public TwoWayPriceBuilder setOfferAmount(double offerAmount) {
		this.offerAmount = offerAmount;
		return this;
	}
	
	
}
