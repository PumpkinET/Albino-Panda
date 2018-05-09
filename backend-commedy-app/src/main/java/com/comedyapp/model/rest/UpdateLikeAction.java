package com.comedyapp.model.rest;

public class UpdateLikeAction {
	int postAction;
	public UpdateLikeAction(int postAction) {
		super();
		this.postAction = postAction;
	}
	public int getPostAction() {
		return postAction;
	}
	public void setPostAction(int postAction) {
		this.postAction = postAction;
	}	
}
