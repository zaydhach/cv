package com.resumeapp.business;

import java.util.List;

import org.apache.openjpa.persistence.EntityExistsException;

import com.resumeapp.entities.Activity;
import com.resumeapp.entities.Person;

public interface IPersonManager {

	Person addPerson(Person person) throws EntityExistsException;

	List<Person> showPersons();

	Person showPerson(Person person);

	String MD5(String md5);

	List<Person> findByTitle(String title);

	List<Activity> showActivities(Person p);

	List<Person> findPerson(String nom);

}
