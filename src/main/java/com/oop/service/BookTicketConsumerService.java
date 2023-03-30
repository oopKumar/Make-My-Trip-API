package com.oop.service;

import javax.servlet.http.HttpServletResponse;

import com.oop.request.Passenger;
import com.oop.response.Ticket;

import reactor.core.publisher.Mono;

public interface BookTicketConsumerService {
	  public Ticket bookTicket(Passenger passenger);
	  public <T> T checkTicketStatusById(Integer ticketId);
	  public String downloadTicketById(HttpServletResponse response,Integer ticketId)throws Exception;
	  public String cancelTicket(Integer ticketId);


}
