package mandinet.HttpClient.Model;

import java.util.*;

public class Customer {

	
	private String customerId;
	private String customerName;
	
	private String password;
	private String contactNo;
	
	private String mail;
	
	private String address;
	private String deliveryAddress;
	
	//List of productid
	ArrayList<String> lstProducttoBuy;
	
	//List of orderid
	ArrayList<String> lstProductthatOrdered;
	
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getContactNo() {
		return contactNo;
	}
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDeliveryAddress() {
		return deliveryAddress;
	}
	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}
	public ArrayList<String> getLstProducttoBuy() {
		return lstProducttoBuy;
	}
	public void setLstProducttoBuy(ArrayList<String> lstProducttoBuy) {
		this.lstProducttoBuy = lstProducttoBuy;
	}
	public ArrayList<String> getLstProductthatOrdered() {
		return lstProductthatOrdered;
	}
	public void setLstProductthatOrdered(ArrayList<String> lstProductthatOrdered) {
		this.lstProductthatOrdered = lstProductthatOrdered;
	}
	
}
