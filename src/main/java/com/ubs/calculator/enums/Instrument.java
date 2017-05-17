package com.ubs.calculator.enums;

public enum Instrument {
    INSTRUMENT0(0),
    INSTRUMENT1(1),
    INSTRUMENT2(2),
    INSTRUMENT3(3),
    INSTRUMENT4(4),
    INSTRUMENT5(5),
    INSTRUMENT6(6),
    INSTRUMENT7(7),
    INSTRUMENT8(8),
    INSTRUMENT9(9),
    INSTRUMENT10(10),
    INSTRUMENT11(11),
    INSTRUMENT12(12),
    INSTRUMENT13(13),
    INSTRUMENT14(14),
    INSTRUMENT15(15),
    INSTRUMENT16(16),
    INSTRUMENT17(17),
    INSTRUMENT18(18),
    INSTRUMENT19(19);
    
    private int value;

	Instrument(int value) {
		this.value = value;
    }
	
	public int getIndex() {
		return this.value;
	}
}


