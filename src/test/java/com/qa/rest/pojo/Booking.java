package com.qa.rest.pojo;

public class Booking {
	
	private String firstname;
	private String lastname;	
	private int totalprice;
	private boolean depositpaid;
	private BookingDates bookingdates;
	private String additionalneeds;
	
	public Booking() {
		
	}
	public Booking(String fname, String lname, String aneeds, int tprice, 
		boolean dpaid, BookingDates dates) {
		
		setFirstname(fname);
		setLastname(lname);		
		setTotalprice(tprice);
		setDepositpaid(dpaid);
		setBookingdates(dates);
		setAdditionalneeds(aneeds);
		
	}

	
	
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getAdditionalneeds() {
		return additionalneeds;
	}
	public void setAdditionalneeds(String additionalneeds) {
		this.additionalneeds = additionalneeds;
	}
	public int getTotalprice() {
		return totalprice;
	}
	public void setTotalprice(int totalprice) {
		this.totalprice = totalprice;
	}
	public boolean isDepositpaid() {
		return depositpaid;
	}
	public void setDepositpaid(boolean depositpaid) {
		this.depositpaid = depositpaid;
	}
	public BookingDates getBookingdates() {
		return bookingdates;
	}
	public void setBookingdates(BookingDates bookingdates) {
		this.bookingdates = bookingdates;
	}

	
	
}
