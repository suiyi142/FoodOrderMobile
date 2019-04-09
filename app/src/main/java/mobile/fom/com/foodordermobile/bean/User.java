package mobile.fom.com.foodordermobile.bean;

public class User {
	private String account;
	private String name;
	private String password;

	public User() {
		super();
	}

	@Override
	public String toString() {
		return "User [account=" + account + ", name=" + name + ", password="
				+ password + "]";
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public User(String account, String name, String password) {
		super();
		this.account = account;
		this.name = name;
		this.password = password;
	}

}
