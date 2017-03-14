package com.resumeapp.business;

import java.util.List;

import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.resumeapp.entities.Activity;
import com.resumeapp.entities.Person;

@Stateful
public class AuthManager {

	@PersistenceContext(unitName = "myMySQLBase")
	private EntityManager em;
	private Person authenPerson = new Person();

	public Person login(String email, String password) throws NoResultException {
		Query query = null;
		try {
			query = em.createQuery("SELECT authperson FROM Person authperson WHERE authperson.email='" + email
					+ "' AND authperson.password='" + password + "'");

		} catch (NoResultException e) {
			return null;
		}
		if (query.getResultList().size() == 1)
			authenPerson = (Person) query.getSingleResult();
		else
			logout();

		return authenPerson;

	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void updatePerson(Person person) {
		if (em.find(Person.class, person.getId()) != null)
			
			em.merge(person);

	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void saveActivity(Activity activity) {
		if (em.find(Activity.class, activity.getId()) == null) {

			em.persist(activity);
			System.out.println("persist");
		} else {
			em.merge(activity);
			System.out.println("merge");
		}

	}

	public void removeActivity(Activity activity) {
		System.out.println("dkhal");
		Activity foundActivity = em.find(Activity.class, activity.getId());
		em.remove(foundActivity);

	}

	public Activity findActivity(Activity activity) {

		Activity foundActivity = em.find(Activity.class, activity.getId());
		return foundActivity;

	}

	public void removePerson(Person person) {
		Person foundPerson = em.find(Person.class, person.getId());
		em.remove(foundPerson);
	}

	@SuppressWarnings("unchecked")
	public List<Activity> showActivities(Person p) {
		Query query = null;
		if (p.getId() != null) {

			try {
				query = em.createQuery("SELECT a FROM Activity a WHERE a.person.id=" + p.getId() + "");
			} catch (Exception e) {
			}
			if (query != null) {
				List<Activity> activities = query.getResultList();
				return activities;
			}
		}
		return null;
	}


	public Person logout() {
		authenPerson = null;
		return authenPerson;
	}

	public Person getAuthenPerson() {
		return authenPerson;
	}

	public void setAuthenPerson(Person authenPerson) {
		this.authenPerson = authenPerson;
	}

}
