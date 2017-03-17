package com.resumeapp.tests;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.embeddable.EJBContainer;

import org.junit.Before;
import org.junit.Test;

import com.resumeapp.business.IAuthManager;
import com.resumeapp.business.IPersonManager;
import com.resumeapp.entities.Activity;
import com.resumeapp.entities.Nature;
import com.resumeapp.entities.Person;
import com.resumeapp.web.PersonController;

public class TestAuthManager {

	@EJB
	IAuthManager authPersonManager;

	@EJB
	IPersonManager personManager;

	@EJB
	PersonController pc;

	Person person;
	Person nonperson;

	public TestAuthManager() throws Exception {
		EJBContainer.createEJBContainer().getContext().bind("inject", this);
	}

	@Before
	public void setUp() {
		person = new Person();
		nonperson = new Person();
		person.setEmail("houmed.hassan@gmail.com");
		person.setPassword("password");
		nonperson.setEmail("houmed.hassan@gmail.com");
		nonperson.setPassword("condo");
		assertNotNull(authPersonManager);
		assertNotNull(authPersonManager);
	}

	//
	@Test
	public void testAuthentificate() throws Exception {

		Person authenPerson = authPersonManager.login(person.getEmail(), person.getPassword());
		assertNotNull(authenPerson);
		Person nonauthenPerson = authPersonManager.login(nonperson.getEmail(), nonperson.getPassword());
		assertNull(nonauthenPerson);

	}

	//
	// @Test
	// public void TestRemovePerson() {
	//
	// Person person = new Person();
	// person.setId(1);
	// // List<Activity> activities = personManager.showActivities(person);
	// // activityManager.removeActivities(activities);
	// authPersonManager.removePerson(person);
	// assertNull(personManager.showPerson(person));
	// }
	// @Test
	// public void TestRemoveActivy() {
	//
	// Activity activity = new Activity();
	// activity.setId(2);
	// authPersonManager.removeActivity(activity);
	// }
	//
	@Test
	public void TestCreatActivity() {
		Person p = new Person();
		p.setId(1);
		Person person = personManager.showPerson(p);
		Activity c = new Activity();
		c.setTitle("Stage chez Chandamama");
		c.setDescription("playing for change");
		c.setNature(Nature.AUTRE);
		c.setYear(new Date(2011));
		c.setPerson(person);
		c.setWebAddress("www.website.com");
		authPersonManager.addActivity(c);
		
		c = new Activity();
		c.setTitle("football");
		c.setDescription("lklklk llkl kl kl k");
		c.setNature(Nature.AUTRE);
		c.setYear(new Date(2011));
		c.setPerson(person);
		c.setWebAddress("www.website.com");
		authPersonManager.addActivity(c);
		
		c = new Activity();
		c.setTitle("Basket BALL");
		c.setDescription("playing for change");
		c.setNature(Nature.AUTRE);
		c.setYear(new Date(2011));
		c.setPerson(person);
		c.setWebAddress("www.website.com");
		authPersonManager.addActivity(c);

		c = new Activity();
		c.setTitle("Dut Developpement Informatique");
		c.setDescription("fdqsf d sf ezf zd gz g ge  z d sg ds g eg f g eg feg er");
		c.setNature(Nature.FORMATION);
		c.setYear(new Date(2011));
		c.setPerson(person);
		c.setWebAddress("www.website.com");
		authPersonManager.addActivity(c);

		c = new Activity();
		c.setTitle("Master  Informatique");
		c.setDescription("fdqsf d sf ezf zd gz g ge  z d sg ds g eg f g eg feg er");
		c.setNature(Nature.FORMATION);
		c.setYear(new Date(2011));
		c.setPerson(person);
		c.setWebAddress("www.website.com");
		authPersonManager.addActivity(c);

		c = new Activity();
		c.setTitle("Licence Informatique");
		c.setDescription("playing for change");
		c.setNature(Nature.FORMATION);
		c.setYear(new Date(2011));
		c.setPerson(person);
		c.setWebAddress("www.website.com");
		authPersonManager.addActivity(c);

		c = new Activity();
		c.setTitle("Stage chez SANSO");
		c.setDescription("Stage chez SANSO");
		c.setNature(Nature.EXPERIENCE_PROFESSIONNELLE);
		c.setYear(new Date(2011));
		c.setPerson(person);
		c.setWebAddress("www.website.com");
		authPersonManager.addActivity(c);



	}
	// @Test
	// public void updateActivity(){
	// Activity c = new Activity();
	// c.setId(1);
	// c.setTitle("PL/SQL");
	// c.setDescription("SQL/PL LQS/LP");
	// c.setNature(Nature.FORMATION);
	// c.setYear(new Date(2012));
	// authPersonManager.saveActivity(c);
	//
	//
	// }
	//
	// @Test
	// public void testUpdatePerson() {
	// Person p = new Person();
	// p.setId(1);
	// p = personManager.showPerson(p);
	// p.setFirstName("Malika");
	// p.setName("CHOUAFIA");
	// authPersonManager.updatePerson(p);
	// Person personUpdated = personManager.showPerson(p);
	// assertTrue(personUpdated.getFirstName().equals("Malika"));
	//
	// }

}
