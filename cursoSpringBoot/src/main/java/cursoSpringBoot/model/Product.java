package cursoSpringBoot.model;

public class Product {
	
	//atributos con tipo de dato referencial de forma correcta
	private Integer id;
	private String name;
	private Double price;
	private Integer stock;
	
	
	public Product(Integer id, String name, Double price, Integer stock) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.stock = stock;
	}


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


	public Double getPrice() {
		return price;
	}


	public void setPrecio(Double price) {
		this.price = price;
	}


	public Integer getStock() {
		return stock;
	}


	public void setStock(Integer stock) {
		this.stock = stock;
	}
	
	

}
