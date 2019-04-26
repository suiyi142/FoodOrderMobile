package mobile.fom.com.foodordermobile.bean;

public class OrderItem {
	private String g_id;
	private String b_id;
	private String g_name;
	private int num;
	private int total;

	public OrderItem() {
		super();
	}

	@Override
	public String toString() {
		return "OrderItem [g_id=" + g_id + ", b_id=" + b_id + ", g_name="
				+ g_name + ", num=" + num + ", total=" + total + "]";
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

	public String getG_name() {
		return g_name;
	}

	public void setG_name(String g_name) {
		this.g_name = g_name;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	

}
