package com.gevapps.manningreporter;

public class ManningOffer {
	private String offercode;
	private String readDate;
	private Pack pack;

	public void setReadDate(String readDate)
	{
		this.readDate = readDate;
	}

	public String getReadDate()
	{
		return readDate;
	}

	public void setOffercode(String offercode)
	{
		this.offercode = offercode;
	}

	public String getOffercode()
	{
		return offercode;
	}

	public void setPack(Pack pack)
	{
		this.pack = pack;
	}

	public Pack getPack()
	{
		return pack;
	}
	
}
