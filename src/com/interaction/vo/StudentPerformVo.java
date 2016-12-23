package com.interaction.vo;

public class StudentPerformVo {

	private double quantization;  //量化指标
	private double unquantization; //非量化指标
	public double getQuantization() {
		return quantization;
	}
	public void setQuantization(double quantization) {
		this.quantization = quantization;
	}
	public double getUnquantization() {
		return unquantization;
	}
	public void setUnquantization(double unquantization) {
		this.unquantization = unquantization;
	}
	@Override
	public String toString() {
		return "StudentPerformVo [quantization=" + quantization + ", unquantization=" + unquantization + "]";
	}
	
	
}
