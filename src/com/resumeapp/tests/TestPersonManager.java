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

	@Test
	public void testAddPerson() {
		String password=personManager.MD5("password");
		Person person1 = new Person("houmed", "abdorahim", "houmed.hassan@gmail.com", "www.cc.fr",
				new Date(22 / 10 / 1992), password);
		personManager.addPerson(person1);
		Person person2 = new Person("anass", "Mesmudi", "anass.Mesmudi@gmail.com", "www.cc.fr",
				new Date(22 / 10 / 1992), password);
		personManager.addPerson(person2);

		Person person3 = new Person("Maccartney", "paul", "paul.maccartney@gmail.com", "www.cc.fr",
				new Date(22 / 10 / 1992), password);
		personManager.addPerson(person3);

		Person person4 = new Person("BBBB", "King", "BBBB.King@gmail.com", "www.cc.fr",
				new Date(22 / 10 / 1992), password);
		personManager.addPerson(person4);

		Person person5 = new Person("Eddie", "Veder", "Eddie.Veder@gmail.com", "www.cc.fr",
				new Date(22 / 10 / 1992), password);
		personManager.addPerson(person5);

		Person person6 = new Person("janis", "joplin", "janis.joplin@gmail.com", "www.cc.fr",
				new Date(22 / 10 / 1992), password);
		personManager.addPerson(person6);

		
		Person person7 = new Person("Jimmy", "Hendrix", "Jimmy.Hendrix@gmail.com", "www.cc.fr",
				new Date(22 / 10 / 1992), password);
		personManager.addPerson(person7);

		Person person8 = new Person("Khaled", "Cheb", "Khaled.Cheb@gmail.com", "www.cc.fr",
				new Date(22 / 10 / 1992), password);
		personManager.addPerson(person8);
		
		Person person9 = new Person("Arstotle", "descarte", "descarte.Arstotle@gmail.com", "www.cc.fr",
				new Date(22 / 10 / 1992), password);
		personManager.addPerson(person9);

		Person person10 = new Person("camus", "campus", "camus.campus@gmail.com", "www.cc.fr",
				new Date(22 / 10 / 1992), password);
		personManager.addPerson(person10);

		Person person = new Person("alpha", "blondy", "alpha.blondy@gmail.com", "www.cc.fr",
				new Date(22 / 10 / 1992), password);
		personManager.addPerson(person);

		Person person11 = new Person("omega", "six", "omega.six@gmail.com", "www.cc.fr",
				new Date(22 / 10 / 1992), password);
		personManager.addPerson(person11);

		Person person12 = new Person("Haj", "brahim", "Haj.brahim@gmail.com", "www.cc.fr",
				new Date(22 / 10 / 1992), password);
		personManager.addPerson(person);

		Person person13 = new Person("Aitbaha", "moulhanout", "moulhanout.Aitbaha@gmail.com", "www.cc.fr",
				new Date(22 / 10 / 1992), password);
		personManager.addPerson(person13);

		Person person14 = new Person("Hicham", "Elgatem", "Hicham.Elgatem@gmail.com", "www.cc.fr",
				new Date(22 / 10 / 1992), password);
		personManager.addPerson(person14);

		Person person15 = new Person("Salit", "dahrii", "Salit.dahrii@gmail.com", "www.cc.fr",
				new Date(22 / 10 / 1992), password);
		personManager.addPerson(person15);


	}

	@Test
	public void testShowPerson() {
		Person person = new Person();
		Person showPerson = new Person();
		person.setId(1);
		showPerson = personManager.showPerson(person);
		assertTrue(showPerson.getEmail().equals("houmed.hassan@gmail.com"));
	}

	@Test
	public void showAllPersons() {
		List<Person> persons = personManager.showPersons();
		assertEquals(persons.size(), 1);
	}

	@Test
	public void TestshowPersonActivities() {

		Person p = new Person();
		p.setId(1);
		List<Activity> activities = personManager.showActivities(p);
		assertEquals(activities.size(), 1);
	}
	
//	 @Test
//	 public void TestFindActivityByTitle(){
//	 List<Activity> activitiesFound=personManager.findByTitle("ee");
//	 assertEquals(activitiesFound.size(),1);
//	 }

}
