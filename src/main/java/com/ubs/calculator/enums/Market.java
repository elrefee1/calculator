package com.ubs.calculator.enums;

public enum Market {
    MARKET0(0),
    MARKET1(1),
    MARKET2(2),
    MARKET3(3),
    MARKET4(4),
    MARKET5(5),
    MARKET6(6),
    MARKET7(7),
    MARKET8(8),
    MARKET9(9),
    MARKET10(10),
    MARKET11(11),
    MARKET12(12),
    MARKET13(13),
    MARKET14(14),
    MARKET15(15),
    MARKET16(16),
    MARKET17(17),
    MARKET18(18),
    MARKET19(19),
    MARKET20(20),
    MARKET21(21),
    MARKET22(22),
    MARKET23(23),
    MARKET24(24),
    MARKET25(25),
    MARKET26(26),
    MARKET27(27),
    MARKET28(28),
    MARKET29(29),
    MARKET30(30),
    MARKET31(31),
    MARKET32(32),
    MARKET33(33),
    MARKET34(34),
    MARKET35(35),
    MARKET36(36),
    MARKET37(37),
    MARKET38(38),
    MARKET39(39),
    MARKET40(40),
    MARKET41(41),
    MARKET42(42),
    MARKET43(43),
    MARKET44(44),
    MARKET45(45),
    MARKET46(46),
    MARKET47(47),
    MARKET48(48),
    MARKET49(49);
    
    private int value;

	Market(int value) {
		this.value = value;
    }
	
	public int getIndex() {
		return this.value;
	}
}

