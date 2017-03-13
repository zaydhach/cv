package com.resumeapp.web;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class Navigation {

    public String hello() {
        return "hello";
    }
    
    public String editactivity() {
		return "editActivity?faces-redirect=true";
	}

}