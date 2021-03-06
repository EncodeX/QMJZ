package edu.neu.qmjz.bean;

import org.json.JSONException;
import org.json.JSONObject;

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
	private double salary;
	private String serviceType;
	private String remark;
	private boolean isAccepted;
	private boolean isDirected;
	private String orderNo;

	public Declare() {
	}

	public Declare(JSONObject jsonObject) throws JSONException {
		this.id = jsonObject.getInt("id");
		this.customerId = jsonObject.getString("customerID");
		this.customerName = jsonObject.getString("customerName");
		this.servantId = jsonObject.getString("servantID");
		this.servantName = jsonObject.getString("servantName");
		this.phoneNo = jsonObject.getString("phoneNo");
		this.declareTime = jsonObject.getString("declareTime");
		this.serviceTime = jsonObject.getString("serviceTime");
		this.serviceProvince = jsonObject.getString("serviceProvince");
		this.serviceCity = jsonObject.getString("serviceCity");
		this.serviceCounty = jsonObject.getString("serviceCounty");
		this.serviceAddress = jsonObject.getString("serviceAddress");
		this.serviceLongitude = jsonObject.getString("serviceLongitude");
		this.serviceLatitude = jsonObject.getString("serviceLatitude");
		this.salary = jsonObject.getDouble("salary");
		this.serviceType = jsonObject.getString("serviceType");
		this.remark = jsonObject.getString("remarks");
		this.isAccepted = jsonObject.getInt("isAccepted") == 1;
		this.isDirected = jsonObject.getInt("isDirected") == 1;
		this.orderNo = jsonObject.getString("orderNo");
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

	public boolean isDirected() {
		return isDirected;
	}

	public void setIsDirected(boolean isDirected) {
		this.isDirected = isDirected;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
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

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
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
