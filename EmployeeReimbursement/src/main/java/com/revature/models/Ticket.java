package com.revature.models;

import java.util.Objects;

public class Ticket {

	private int ticketId;
	private double amount;
	private String description;
	private int statusId;
	private int authorId;
	
	public Ticket() {
		// TODO Auto-generated constructor stub
	}

	public Ticket(int ticketId, double amount, String description, int statusId, int authorId) {
		super();
		this.ticketId = ticketId;
		this.amount = amount;
		this.description = description;
		this.statusId = statusId;
		this.authorId = authorId;
	}

	public int getTicketId() {
		return ticketId;
	}

	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	public int getAuthorId() {
		return authorId;
	}

	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(amount, authorId, description, statusId, ticketId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ticket other = (Ticket) obj;
		return Double.doubleToLongBits(amount) == Double.doubleToLongBits(other.amount) && authorId == other.authorId
				&& Objects.equals(description, other.description) && Objects.equals(statusId, other.statusId)
				&& ticketId == other.ticketId;
	}

	@Override
	public String toString() {
		return "Ticket [ticketId=" + ticketId + ", amount=" + amount + ", description=" + description + ", statusId="
				+ statusId + ", authorId=" + authorId + "]";
	}

	
}
