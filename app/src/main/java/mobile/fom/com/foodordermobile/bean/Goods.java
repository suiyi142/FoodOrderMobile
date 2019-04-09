package mobile.fom.com.foodordermobile.bean;

public class Goods {
	private String g_id;
	private String b_id;
	private String name;
	private String price;
	private String photo;

	public Goods() {
		super();
	}

	public Goods(String g_id, String b_id, String name, String price,
			String photo) {
		super();
		this.g_id = g_id;
		this.b_id = b_id;
		this.name = name;
		this.price = price;
		this.photo = photo;
	}

	@Override
	public String toString() {
		return "Goods [g_id=" + g_id + ", b_id=" + b_id + ", name=" + name
				+ ", price=" + price + ", photo=" + photo + "]";
	}

	public String getG_id() {
		return g_id;
	}

	public void setG_id(String g_id) {
		this.g_id = g_id;
	}

	public String getB_id() {
		return b_id;
	}

	public void setB_id(String b_id) {
		this.b_id = b_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

}
