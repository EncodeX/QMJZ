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
public class Order {
	private int id;
	private String orderNo;
	private String customerId;
	private String customerName;
	private String servantId;
	private String servantName;
	private String serviceType;
	private String orderTime;
	private String confirmTime;
	private String serviceContent;
	private String payTime;
	private String commentTime;
	private double servicePrice;
	private double paidAmount;
	private String orderStatus;
	private String payType;
	private boolean isSettled;
	private String settleDate;
	private String remark;
	private String contactAddress;
	private String contactPhone;

	public Order() {
	}

	public Order(String settleDate, String commentTime, String confirmTime, String contactAddress,
	             String contactPhone, String customerId, String customerName, int id,
	             boolean isSettled, String orderNo, String orderStatus, String orderTime,
	             double paidAmount, String payTime, String payType, String remark,
	             String servantId, String servantName, String serviceContent,
	             double servicePrice, String serviceType) {
		this.settleDate = settleDate;
		this.commentTime = commentTime;
		this.confirmTime = confirmTime;
		this.contactAddress = contactAddress;
		this.contactPhone = contactPhone;
		this.customerId = customerId;
		this.customerName = customerName;
		this.id = id;
		this.isSettled = isSettled;
		this.orderNo = orderNo;
		this.orderStatus = orderStatus;
		this.orderTime = orderTime;
		this.paidAmount = paidAmount;
		this.payTime = payTime;
		this.payType = payType;
		this.remark = remark;
		this.servantId = servantId;
		this.servantName = servantName;
		this.serviceContent = serviceContent;
		this.servicePrice = servicePrice;
		this.serviceType = serviceType;
	}

	public String getCommentTime() {
		return commentTime;
	}

	public void setCommentTime(String commentTime) {
		this.commentTime = commentTime;
	}

	public String getConfirmTime() {
		return confirmTime;
	}

	public void setConfirmTime(String confirmTime) {
		this.confirmTime = confirmTime;
	}

	public String getContactAddress() {
		return contactAddress;
	}

	public void setContactAddress(String contactAddress) {
		this.contactAddress = contactAddress;
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isSettled() {
		return isSettled;
	}

	public void setIsSettled(boolean isSettled) {
		this.isSettled = isSettled;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}

	public double getPaidAmount() {
		return paidAmount;
	}

	public void setPaidAmount(double paidAmount) {
		this.paidAmount = paidAmount;
	}

	public String getPayTime() {
		return payTime;
	}

	public void setPayTime(String payTime) {
		this.payTime = payTime;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	public String getServiceContent() {
		return serviceContent;
	}

	public void setServiceContent(String serviceContent) {
		this.serviceContent = serviceContent;
	}

	public double getServicePrice() {
		return servicePrice;
	}

	public void setServicePrice(double servicePrice) {
		this.servicePrice = servicePrice;
	}

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public String getSettleDate() {
		return settleDate;
	}

	public void setSettleDate(String settleDate) {
		this.settleDate = settleDate;
	}
}
