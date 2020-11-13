package Model;

import javax.persistence.*;

@Entity
@Table(name="product")

public class Prdct {

	@Column(name="name")
	private String name;
	
	@Id
	@Column(name="Barcode")
	private String Barcode;
	
	@Column(name="colour")
	private String colour;
	
	@Column(name="description")
	private String description;

	public Prdct() {
		super();
		this.name="default";
		this.Barcode="default";
		this.colour="default";
		this.description="default";
	}
	public Prdct(String name, String Barcode, String colour, String description) {
		super();
		this.name = name;
		this.Barcode = Barcode;
		this.colour = colour;
		this.description = description;
	}

	public String getName() {return name;}
	public String getBarcode() {return Barcode;}
	public String getColour() {return colour;}
	public String getDescription() {return description;}
}
