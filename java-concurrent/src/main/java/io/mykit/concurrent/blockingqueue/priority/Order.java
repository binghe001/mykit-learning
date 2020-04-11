package io.mykit.concurrent.blockingqueue.priority;

public class Order implements Comparable < Order > {

	 private Integer orderId;
	 private String item;
	 private String shippingAddress;
	 private PRIORITY priority;


	 public Order(Integer orderId, String item, PRIORITY priority) {
	  super();
	  this.orderId = orderId;
	  this.item = item;
	  this.priority = priority;
	 }

	 public String getShippingAddress() {
	  return shippingAddress;
	 }

	 public void setShippingAddress(String shippingAddress) {
	  this.shippingAddress = shippingAddress;
	 }

	 public Integer getOrderId() {
	  return orderId;
	 }

	 public String getItem() {
	  return item;
	 }

	public PRIORITY getPriority() {
		return priority;
	 }

	 @Override
	 public int compareTo(Order o) {
	  return this.priority.value().compareTo(o.getPriority().value());
	 }

	 @Override
	 public String toString() {
	  return "Order [orderId=" + orderId + ", item=" + item + ", shippingAddress=" + shippingAddress + ", priority=" + priority + "]";
	 }
	}