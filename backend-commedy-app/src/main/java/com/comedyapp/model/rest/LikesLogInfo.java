package com.comedyapp.model.rest;

public class LikesLogInfo {
	private int operator;
	private int lastAction;
	public LikesLogInfo(int operator, int lastAction) {
		super();
		this.operator = operator;
		this.lastAction = lastAction;
	}
	public int getOperator() {
		return operator;
	}
	public void setOperator(int operator) {
		this.operator = operator;
	}
	public int getLastAction() {
		return lastAction;
	}
	public void setLastAction(int lastAction) {
		this.lastAction = lastAction;
	}
	
}
