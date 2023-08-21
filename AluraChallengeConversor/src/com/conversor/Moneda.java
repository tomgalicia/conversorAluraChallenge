package com.conversor;

public class Moneda {
	private String code;
	private double value;
	
	public Moneda(String codepais, double valuerate) {
		code=codepais;
		value=valuerate;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}

}
