package com.resumeapp.web;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class Navigation {

	public String hello() {
		return "hello";
	}

	public String showPersons() {
		return "showPersons?faces-redirect=true";
	}

	public String authHome() {
		return "authHome?faces-redirect=true";
	}

	public String subscribePerson() {
		return "subscribePerson?faces-redirect=true";
	}

	public String editactivity() {
		return "editActivity?faces-redirect=true";
	}
	
	public String addActivity() {
		return "addActivity?faces-redirect=true";
	}

}