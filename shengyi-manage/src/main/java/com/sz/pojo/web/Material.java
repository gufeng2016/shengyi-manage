package com.sz.pojo.web;

import java.util.Date;

/**
 * 
 * @author goff.yin 
 * @date 2017-10-17   下午5:52:14 
 * @version 1.0.0 
 */
public class Material {
	
	private Integer id;
	
	private String name;
	
	private String guiGe;
	
	private Double price1;
	
	private Double price2 ;
	private Double price3 ;
	private Double price4  ;
	private Double price5  ;
	private Double price6  ;
	
	private Double profit;
	
	private Integer count;
	
	private Double salePrice;
	
	private Double allPrice;
	
	private Double allProfit;
	
	private Date createTime;
	
	private Date updateTime;
	
	
	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getGuiGe() {
		return guiGe;
	}



	public void setGuiGe(String guiGe) {
		this.guiGe = guiGe;
	}



	public Double getPrice1() {
		return price1;
	}



	public void setPrice1(Double price1) {
		this.price1 = price1;
	}



	public Double getPrice2() {
		return price2;
	}



	public void setPrice2(Double price2) {
		this.price2 = price2;
	}



	public Double getPrice3() {
		return price3;
	}



	public void setPrice3(Double price3) {
		this.price3 = price3;
	}



	public Double getPrice4() {
		return price4;
	}



	public void setPrice4(Double price4) {
		this.price4 = price4;
	}



	public Double getPrice5() {
		return price5;
	}



	public void setPrice5(Double price5) {
		this.price5 = price5;
	}



	public Double getPrice6() {
		return price6;
	}



	public void setPrice6(Double price6) {
		this.price6 = price6;
	}



	public Double getProfit() {
		return profit;
	}



	public void setProfit(Double profit) {
		this.profit = profit;
	}



	public Integer getCount() {
		return count;
	}



	public void setCount(Integer count) {
		this.count = count;
	}



	public Double getSalePrice() {
		return salePrice;
	}



	public void setSalePrice(Double salePrice) {
		this.salePrice = salePrice;
	}



	public Double getAllPrice() {
		return allPrice;
	}



	public void setAllPrice(Double allPrice) {
		this.allPrice = allPrice;
	}



	public Double getAllProfit() {
		return allProfit;
	}



	public void setAllProfit(Double allProfit) {
		this.allProfit = allProfit;
	}



	public Date getCreateTime() {
		return createTime;
	}



	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}



	public Date getUpdateTime() {
		return updateTime;
	}



	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}



	@Override
	public String toString() {
		String res = " , name:"+name + " , guiGe:"+guiGe
				+" , price1"+price1+" , price2"+price2+" , price3"+price3+" , price4"+price4+" , price5"+price5+" , price6"+price6
				+" , profit"+profit+" , count"+count+" , salePrice"+salePrice+" , allPrice"+allPrice+" , allProfit"+allProfit;
		return res;
	}
	
}
