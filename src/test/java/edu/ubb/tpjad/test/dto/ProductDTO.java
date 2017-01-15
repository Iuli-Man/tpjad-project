package edu.ubb.tpjad.test.dto;

public class ProductDTO {

	private int id;
	private String name;
	private String type;
	private String description;
	public int getId() {
		return id;
	}
	public ProductDTO setId(int id) {
		this.id = id;
		return this;
	}
	public String getName() {
		return name;
	}
	public ProductDTO setName(String name) {
		this.name = name;
		return this;
	}
	public String getType() {
		return type;
	}
	public ProductDTO setType(String type) {
		this.type = type;
		return this;
	}
	public String getDescription() {
		return description;
	}
	public ProductDTO setDescription(String description) {
		this.description = description;
		return this;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductDTO other = (ProductDTO) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}
	
}
