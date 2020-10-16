package Beans;

public class Doc {

	private String liblle;
	private String decription;
	
	
	public Doc() {
		super();
	}
	
	public Doc(String liblle, String decription) {
		super();
		this.liblle = liblle;
		this.decription = decription;
	}
	public String getLiblle() {
		return liblle;
	}
	public void setLiblle(String liblle) {
		this.liblle = liblle;
	}
	public String getDecription() {
		return decription;
	}
	public void setDecription(String decription) {
		this.decription = decription;
	}
	
	
	
}
