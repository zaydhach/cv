package com.resumeapp.business;

import java.util.List;

import javax.persistence.NoResultException;

import com.resumeapp.entities.Activity;
import com.resumeapp.entities.Person;

public interface IAuthManager {

	Person login(String email, String password) throws NoResultException;

	void updatePerson(Person person);

	Activity addActivity(Activity activity);

	Activity updateActivity(Activity activity);

	Activity findActivity(Activity activity);

	Activity removeActivity(Activity activity);

	void removePerson(Person person);

	List<Activity> showActivities(Person p);

	Person logout();

}
