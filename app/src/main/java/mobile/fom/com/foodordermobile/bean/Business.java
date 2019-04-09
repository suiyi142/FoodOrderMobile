package mobile.fom.com.foodordermobile.bean;

public class Business {
	private String b_id;
	private String address;
	private String password;
	private int current_seats;
	private int max_seats;
	private String other;

	public Business() {
		super();
	}

	public Business(String b_id, String address, String password,
			int current_seats, int max_seats, String other) {
		super();
		this.b_id = b_id;
		this.address = address;
		this.password = password;
		this.current_seats = current_seats;
		this.max_seats = max_seats;
		this.other = other;
	}

	@Override
	public String toString() {
		return "Business [b_id=" + b_id + ", address=" + address
				+ ", password=" + password + ", current_seats=" + current_seats
				+ ", max_seats=" + max_seats + ", other=" + other + "]";
	}

	public String getB_id() {
		return b_id;
	}

	public void setB_id(String b_id) {
		this.b_id = b_id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getCurrent_seats() {
		return current_seats;
	}

	public void setCurrent_seats(int current_seats) {
		this.current_seats = current_seats;
	}

	public int getMax_seats() {
		return max_seats;
	}

	public void setMax_seats(int max_seats) {
		this.max_seats = max_seats;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

}
