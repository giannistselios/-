package net.product.model;

public class Prdct {
	private static String Barcode;
	private static String name;
	private static String colour;
	private static String description;
	
	public static String getName() {
		return name;
	}
	
	public void setName(String name) {
		Prdct.name = name;
	}
	
	public static String getBarcode() {
		return Barcode;
	}
	
	public void setBarcode(String barcode) {
		Barcode = barcode;
	}
	
	public static String getColour() {
		return colour;
	}
	
	public void setColour(String colour) {
		Prdct.colour = colour;
	}
	
	public static String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		Prdct.description = description;
	}
}
