package model;

public abstract class Customer {
	private static int nextId = 0;
	private int id;
	private String name;
	private String tlf;

	public Customer(String name, String tlf) {
		this.id = nextId++;
		this.name = name;
		this.tlf = tlf;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getTlf() {
		return tlf;
	}

}
