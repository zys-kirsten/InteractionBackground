package com.interaction.vo;

public class ElementsCompare {
	
	private Integer ecid; //比较ID
	private Integer xcompare; //比较的横向因素ID
	private Integer ycompare; //比较的纵向因素ID
	private double  compareValue; // 二者比值
	public Integer getEcid() {
		return ecid;
	}
	public void setEcid(Integer ecid) {
		this.ecid = ecid;
	}
	public Integer getXcompare() {
		return xcompare;
	}
	public void setXcompare(Integer xcompare) {
		this.xcompare = xcompare;
	}
	public Integer getYcompare() {
		return ycompare;
	}
	public void setYcompare(Integer ycompare) {
		this.ycompare = ycompare;
	}
	public double getCompareValue() {
		return compareValue;
	}
	public void setCompareValue(double compareValue) {
		this.compareValue = compareValue;
	}
	@Override
	public String toString() {
		return "ElementsCompare [ecid=" + ecid + ", xcompare=" + xcompare + ", ycompare=" + ycompare + ", compareValue="
				+ compareValue + "]";
	}
}
