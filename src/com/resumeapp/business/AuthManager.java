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
public class AuthManager implements IAuthManager {

	@PersistenceContext(unitName = "myMySQLBase")
	private EntityManager em;
	private Person authenPerson = new Person();

	@Override
	public Person login(String email, String password) throws NoResultException {
		Query query = null;
		query = em
				.createQuery(
						"SELECT authperson FROM Person authperson WHERE authperson.email=:email AND authperson.password=:password")
				.setParameter("email", email).setParameter("password", password);
		if (query.getResultList().size() == 1)
			authenPerson = (Person) query.getSingleResult();
		else
			logout();
		return authenPerson;
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void updatePerson(Person person) {
		if (em.find(Person.class, person.getId()) != null)
			em.merge(person);
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public Activity addActivity(Activity activity) {
		em.persist(activity);
		return this.findActivity(activity);
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public Activity updateActivity(Activity activity) {
		return em.merge(activity);
	}

	@Override
	public Activity removeActivity(Activity activity) {
		Activity foundActivity = em.find(Activity.class, activity.getId());
		em.remove(foundActivity);
		return foundActivity;

	}

	@Override
	public Activity findActivity(Activity activity) {
		Activity foundActivity = em.find(Activity.class, activity.getId());
		return foundActivity;
	}

	@Override
	public void removePerson(Person person) {
		Person foundPerson = em.find(Person.class, person.getId());
		em.remove(foundPerson);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Activity> showActivities(Person p) {
		Query query = null;
		if (p.getId() != null) {
			try {
				query = em.createQuery("SELECT a FROM Activity a WHERE a.person.id=:id").setParameter("id", p.getId());
			} catch (Exception e) {
			}
			if (query != null) {
				List<Activity> activities = query.getResultList();
				return activities;
			}
		}
		return null;
	}

	@Override
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
