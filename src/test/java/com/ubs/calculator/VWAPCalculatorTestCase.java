package com.ubs.calculator;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ubs.calculator.enums.Instrument;
import com.ubs.calculator.enums.Market;
import com.ubs.calculator.enums.State;
import com.ubs.calculator.price.PriceCache;
import com.ubs.calculator.price.TwoWayPrice;
import com.ubs.calculator.price.TwoWayPriceBuilder;

public class VWAPCalculatorTestCase {
	
	@Mock
	private PriceCache mockPriceCache;
	
	@Mock
	private MarketUpdate mockMarketUpdate;
	
	private VWAPCalculator calculator;
	
	@BeforeMethod
	public void beforeMethod() {
		MockitoAnnotations.initMocks(this);
		
		this.calculator = new VWAPCalculator(this.mockPriceCache);
	}
	
	@DataProvider
	public Object[][] validPriceDataProvider() {
		return new Object[][] {
			// all firm and valid prices
			new Object[] {
					new TwoWayPriceBuilder().setBidAmount(100d).setBidPrice(2.0d).setOfferAmount(200.0d).setOfferPrice(4.0d).setState(State.FIRM).setInstrument(Instrument.INSTRUMENT1).build(),
					new TwoWayPriceBuilder().setBidAmount(150d).setBidPrice(290d/150d).setOfferAmount(600d).setOfferPrice(2680d/600d).setState(State.FIRM).setInstrument(Instrument.INSTRUMENT1).build(),
					new TwoWayPrice[] {
							new TwoWayPriceBuilder().setBidAmount(100d).setBidPrice(2.0d).setOfferAmount(200.0d).setOfferPrice(4.0d).setState(State.FIRM).setInstrument(Instrument.INSTRUMENT1).build(),
							new TwoWayPriceBuilder().setBidAmount(50d).setBidPrice(1.8d).setOfferAmount(400.0d).setOfferPrice(4.7d).setState(State.FIRM).setInstrument(Instrument.INSTRUMENT1).build()
			}},
			// one indicative price in valid price list
			new Object[] {
					new TwoWayPriceBuilder().setBidAmount(100d).setBidPrice(2.0d).setOfferAmount(200.0d).setOfferPrice(4.0d).setState(State.INDICATIVE).setInstrument(Instrument.INSTRUMENT1).build(),
					new TwoWayPriceBuilder().setBidAmount(150d).setBidPrice(290d/150d).setOfferAmount(600d).setOfferPrice(2680d/600d).setState(State.INDICATIVE).setInstrument(Instrument.INSTRUMENT1).build(),
					new TwoWayPrice[] {
							new TwoWayPriceBuilder().setBidAmount(100d).setBidPrice(2.0d).setOfferAmount(200.0d).setOfferPrice(4.0d).setState(State.INDICATIVE).setInstrument(Instrument.INSTRUMENT1).build(),
							new TwoWayPriceBuilder().setBidAmount(50d).setBidPrice(1.8d).setOfferAmount(400.0d).setOfferPrice(4.7d).setState(State.FIRM).setInstrument(Instrument.INSTRUMENT1).build()
			}}
		};
	}
	
	@Test(dataProvider="validPriceDataProvider")
	public void validVwapPriceCalculation(TwoWayPrice inputTwoWayPrice, TwoWayPrice expectedTwoWayPrice, TwoWayPrice[] existingPrices){
		Mockito.when(this.mockMarketUpdate.getMarket()).thenReturn(Market.MARKET0);
		Mockito.when(this.mockMarketUpdate.getTwoWayPrice()).thenReturn(inputTwoWayPrice);
		
		Instrument inputInstrument = inputTwoWayPrice.getInstrument();
		Mockito.when(this.mockPriceCache.getPrices(inputInstrument)).thenReturn(existingPrices);

		TwoWayPrice actualTwoWayPrice = this.calculator.applyMarketUpdate(this.mockMarketUpdate);
		
		Mockito.verify(this.mockPriceCache, Mockito.times(1)).addPrice(inputInstrument, Market.MARKET0, inputTwoWayPrice);
		
		assertTwoWayPrice(actualTwoWayPrice, expectedTwoWayPrice);
	}
	
	@DataProvider
	public Object[][] invalidPriceDataProvider() {
		return new Object[][] {
			// input price with 0 amounts
			new Object[] {
					new TwoWayPriceBuilder().setBidAmount(0d).setBidPrice(2.0d).setOfferAmount(0d).setOfferPrice(4.0d).setState(State.FIRM).setInstrument(Instrument.INSTRUMENT1).build(),
					new TwoWayPriceBuilder().setBidAmount(50d).setBidPrice(1.8d).setOfferAmount(400.0d).setOfferPrice(4.7d).setState(State.FIRM).setInstrument(Instrument.INSTRUMENT1).build(),
					new TwoWayPrice[] {
							new TwoWayPriceBuilder().setBidAmount(0d).setBidPrice(2.0d).setOfferAmount(0d).setOfferPrice(4.0d).setState(State.FIRM).setInstrument(Instrument.INSTRUMENT1).build(),
							new TwoWayPriceBuilder().setBidAmount(50d).setBidPrice(1.8d).setOfferAmount(400.0d).setOfferPrice(4.7d).setState(State.FIRM).setInstrument(Instrument.INSTRUMENT1).build()
			}},
			// one side 0 amount
			new Object[] {
					new TwoWayPriceBuilder().setBidAmount(0d).setBidPrice(2.0d).setOfferAmount(200.0d).setOfferPrice(4.0d).setState(State.INDICATIVE).setInstrument(Instrument.INSTRUMENT1).build(),
					new TwoWayPriceBuilder().setBidAmount(50d).setBidPrice(1.8d).setOfferAmount(600d).setOfferPrice(2680d/600d).setState(State.INDICATIVE).setInstrument(Instrument.INSTRUMENT1).build(),
					new TwoWayPrice[] {
							new TwoWayPriceBuilder().setBidAmount(0d).setBidPrice(2.0d).setOfferAmount(200.0d).setOfferPrice(4.0d).setState(State.INDICATIVE).setInstrument(Instrument.INSTRUMENT1).build(),
							new TwoWayPriceBuilder().setBidAmount(50d).setBidPrice(1.8d).setOfferAmount(400.0d).setOfferPrice(4.7d).setState(State.FIRM).setInstrument(Instrument.INSTRUMENT1).build()
			}},
			// input price with 0 amounts to result in no price
			new Object[] {
					new TwoWayPriceBuilder().setBidAmount(0d).setBidPrice(2.0d).setOfferAmount(0d).setOfferPrice(4.0d).setState(State.FIRM).setInstrument(Instrument.INSTRUMENT1).build(),
					new TwoWayPriceBuilder().setBidAmount(0d).setBidPrice(Double.NaN).setOfferAmount(0d).setOfferPrice(Double.NaN).setState(State.FIRM).setInstrument(Instrument.INSTRUMENT1).build(),
					new TwoWayPrice[] {
							new TwoWayPriceBuilder().setBidAmount(0d).setBidPrice(2.0d).setOfferAmount(0d).setOfferPrice(4.0d).setState(State.FIRM).setInstrument(Instrument.INSTRUMENT1).build(),
			}},
		};
	}
	
	@Test(dataProvider="invalidPriceDataProvider")
	public void invalidVwapPriceCalculation(TwoWayPrice inputTwoWayPrice, TwoWayPrice expectedTwoWayPrice, TwoWayPrice[] existingPrices){
		Mockito.when(this.mockMarketUpdate.getMarket()).thenReturn(Market.MARKET0);
		Mockito.when(this.mockMarketUpdate.getTwoWayPrice()).thenReturn(inputTwoWayPrice);
		
		Instrument inputInstrument = inputTwoWayPrice.getInstrument();
		Mockito.when(this.mockPriceCache.getPrices(inputInstrument)).thenReturn(existingPrices);

		TwoWayPrice actualTwoWayPrice = this.calculator.applyMarketUpdate(this.mockMarketUpdate);
		
		Mockito.verify(this.mockPriceCache, Mockito.times(1)).addPrice(inputInstrument, Market.MARKET0, inputTwoWayPrice);
		
		assertTwoWayPrice(actualTwoWayPrice, expectedTwoWayPrice);
	}
	
	
	private void assertTwoWayPrice(TwoWayPrice actualTwoWayPrice, TwoWayPrice expectedTwoWayPrice) {
		Assert.assertEquals(actualTwoWayPrice.getBidAmount(), expectedTwoWayPrice.getBidAmount());
		Assert.assertEquals(actualTwoWayPrice.getBidPrice(), expectedTwoWayPrice.getBidPrice());
		Assert.assertEquals(actualTwoWayPrice.getOfferAmount(), expectedTwoWayPrice.getOfferAmount());
		Assert.assertEquals(actualTwoWayPrice.getOfferPrice(), expectedTwoWayPrice.getOfferPrice());
		Assert.assertEquals(actualTwoWayPrice.getInstrument(), expectedTwoWayPrice.getInstrument());
		Assert.assertEquals(actualTwoWayPrice.getState(), expectedTwoWayPrice.getState());
		
	}

}
