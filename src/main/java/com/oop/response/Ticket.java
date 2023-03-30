package com.oop.response;

import java.time.LocalDate;

import lombok.Data;

@Data

public class Ticket {

	private Integer ticketId;
	private String name;
	private String lname;
	private String fromm;
	private String tto;
	private String trainNum;
	private LocalDate doj;
	private Double ticketCost;
	private String ticketStatus;

}
