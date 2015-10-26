package edu.neu.qmjz.bean;

/**
 * Created with Android Studio.
 * Author: Enex Tapper
 * Date: 15/10/25
 * Project: QMJZ
 * Package: edu.neu.qmjz.bean
 */
public class Declare {
	private int id;
	private String customerId;
	private String customerName;
	private String servantId;
	private String servantName;
	private String phoneNo;
	private String declareTime;
	private String serviceTime;
	private String serviceProvince;
	private String serviceCity;
	private String serviceCounty;
	private String serviceAddress;
	private String serviceLongitude;
	private String serviceLatitude;
	private int salary;
	private String serviceType;
	private String remark;
	private boolean isAccepted;
	private int orderNo;

	public Declare() {
	}

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

	public String getDeclareTime() {
		return declareTime;
	}

	public void setDeclareTime(String declareTime) {
		this.declareTime = declareTime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isAccepted() {
		return isAccepted;
	}

	public void setIsAccepted(boolean isAccepted) {
		this.isAccepted = isAccepted;
	}

	public int getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public String getServantId() {
		return servantId;
	}

	public void setServantId(String servantId) {
		this.servantId = servantId;
	}

	public String getServantName() {
		return servantName;
	}

	public void setServantName(String servantName) {
		this.servantName = servantName;
	}

	public String getServiceAddress() {
		return serviceAddress;
	}

	public void setServiceAddress(String serviceAddress) {
		this.serviceAddress = serviceAddress;
	}

	public String getServiceCity() {
		return serviceCity;
	}

	public void setServiceCity(String serviceCity) {
		this.serviceCity = serviceCity;
	}

	public String getServiceCounty() {
		return serviceCounty;
	}

	public void setServiceCounty(String serviceCounty) {
		this.serviceCounty = serviceCounty;
	}

	public String getServiceLatitude() {
		return serviceLatitude;
	}

	public void setServiceLatitude(String serviceLatitude) {
		this.serviceLatitude = serviceLatitude;
	}

	public String getServiceLongitude() {
		return serviceLongitude;
	}

	public void setServiceLongitude(String serviceLongitude) {
		this.serviceLongitude = serviceLongitude;
	}

	public String getServiceProvince() {
		return serviceProvince;
	}

	public void setServiceProvince(String serviceProvince) {
		this.serviceProvince = serviceProvince;
	}

	public String getServiceTime() {
		return serviceTime;
	}

	public void setServiceTime(String serviceTime) {
		this.serviceTime = serviceTime;
	}

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}
}
