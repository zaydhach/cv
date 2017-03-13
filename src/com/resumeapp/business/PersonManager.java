package com.resumeapp.business;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.resumeapp.entities.Activity;
import com.resumeapp.entities.Person;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class PersonManager {

	@PersistenceContext(unitName = "myMySQLBase")
	private EntityManager em;

	@PostConstruct()
	public void debut() {
		System.out.println("Starting " + this);
	}

	@PreDestroy
	public void fin() {
		System.out.println("Stopping " + this);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void addPerson(Person person) {
		if (em.find(Person.class, person.getId()) == null) {
			em.persist(person);
			System.out.println("persist");
		}
	}

	@SuppressWarnings("unchecked")
	public List<Person> showPersons() {
		Query query = em.createQuery("SELECT p FROM Person p");
		List<Person> persons = query.getResultList();
		return persons;
	}

	public Person showPerson(Person person) {
		Query query = em.createQuery("SELECT p FROM Person p WHERE p.id=" + person.getId() + "");
		if (query.getResultList().size() == 0)
			return null;

		Person shownPerson = (Person) query.getSingleResult();
		return shownPerson;
	}

	public List<Person> findPerson(String nom) {
		Query query = em.createQuery("SELECT p FROM Person p WHERE p.name LIKE '%" + nom + "%'");
		if (query.getResultList().size() == 0)
			return null;

		List<Person> shownPerson = (List<Person>) query.getResultList();
		return shownPerson;
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

	public List<Person> findByTitle(String title) {
		Query query = null;
		System.out.println("Find by title method");
		try {
			query = em.createQuery("SELECT DISTINCT p FROM Person p,Activity a WHERE a.title LIKE'%" + title
					+ "%' AND  a.person.id=p.id");

		} catch (NoResultException e) {
			return null;
		}
		if (query != null) {
			@SuppressWarnings("unchecked")
			List<Person> persons = query.getResultList();

			System.out.println(persons.size());

			return persons;

		}
		return null;

	}

}
