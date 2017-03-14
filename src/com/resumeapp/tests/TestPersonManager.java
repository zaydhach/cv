package com.resumeapp.tests;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.embeddable.EJBContainer;
import org.junit.Test;

import com.resumeapp.business.PersonManager;
import com.resumeapp.entities.Activity;
import com.resumeapp.entities.Person;

public class TestPersonManager {

	@EJB
	PersonManager personManager;

	public TestPersonManager() throws Exception {
		EJBContainer.createEJBContainer().getContext().bind("inject", this);
		assertNotNull(personManager);
	}
//
//	@Test
//	public void testAddPerson() {
//
//		Person person = new Person("houmed", "abdorahim", "houmed.hassan@gmail.com", "www.cc.fr",
//				new Date(22 / 10 / 1992), "jamais");
//		personManager.addPerson(person);
//
//	}
//
//	@Test
//	public void testShowPerson() {
//		Person person = new Person();
//		Person showPerson = new Person();
//		person.setId(1);
//		showPerson = personManager.showPerson(person);
//		assertTrue(showPerson.getEmail().equals("houmed.hassan@gmail.com"));
//	}
//
//	@Test
//	public void showAllPersons() {
//		List<Person> persons = personManager.showPersons();
//		assertEquals(persons.size(), 1);
//	}
//
//	@Test
//	public void TestshowPersonActivities() {
//
//		Person p = new Person();
//		p.setId(1);
//		List<Activity> activities = personManager.showActivities(p);
//		assertEquals(activities.size(), 1);
//	}
	
//	 @Test
//	 public void TestFindActivityByTitle(){
//	 List<Activity> activitiesFound=personManager.findByTitle("ee");
//	 assertEquals(activitiesFound.size(),1);
//	 }

}
