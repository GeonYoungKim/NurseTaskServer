package model;



public class NurseVO {
	private String nurseid;
	private String password;
	private String name;
	private String birth;
	private String phone;
	private String address;
	private String image;
	private String todayschedule;
	private String token;
	
	
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getNurseid() {
		return nurseid;
	}
	public void setNurseid(String nurseid) {
		this.nurseid = nurseid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getTodayschedule() {
		return todayschedule;
	}
	public void setTodayschedule(String todayschedule) {
		this.todayschedule = todayschedule;
	}
	@Override
	public String toString() {
		return "NurseVO [nurseid=" + nurseid + ", password=" + password + ", name=" + name + ", birth=" + birth
				+ ", phone=" + phone + ", address=" + address + ", image=" + image + ", todayschedule=" + todayschedule
				+ "]";
	}

	
	
	
		
		
	
	
}
