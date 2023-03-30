package com.oop.service;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.databind.JsonNode;
import com.oop.request.Passenger;
import com.oop.response.Ticket;

import reactor.core.publisher.Mono;

@Service
public class BookTicketConsumerServiceImpl implements BookTicketConsumerService{
	String uri="";
	
	WebClient webClient = WebClient.create();
	
	public Ticket bookTicket(Passenger passenger) {
	//	uri = "http://localhost:8080/bookTicket";
		
	Ticket ticket = webClient.post()
				.uri("http://localhost:8080/bookTicket")
				.body(BodyInserters.fromValue(passenger))
				.header("Content-Type","application/json")
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.bodyToMono(Ticket.class)
				.block();
	if(ticket!=null) {
		return ticket;
	}
						
		return null;
	}

	public String cancelTicket(Integer ticketId) {

		 uri = "http://localhost:8080/cancelTicket/{ticketId}";
		String block = webClient.delete() 
				  .uri(uri,ticketId)
				  .retrieve()
				  .bodyToMono(String.class)
				  .block();
		  
		return block;
	}
	

	public String downloadTicketById(HttpServletResponse response, Integer ticketId) throws Exception {

		return null;
	}

	@Override
	public <T> T checkTicketStatusById(Integer ticketId) {
	
		
		 Ticket ticket = webClient.get()
				.uri("http://localhost:8080/checkStatus/{ticketId}",ticketId)
				.accept(MediaType.ALL)
				.retrieve()
				.bodyToMono(Ticket.class)
				.block();
	if(ticket!=null) {
		return (T) ticket;
	}
	else {
		
		
		return null;
	}
 }
}