package com.resumeapp.web;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.resumeapp.business.PersonManager;
import com.resumeapp.entities.Activity;
import com.resumeapp.entities.Person;

/**
 * 
 * @author Zayd HACHCHAM
 * @author Zakaria CHOUAFIA
 * 
 *         This class is used to access non connected user informations
 */
@ManagedBean(name = "person")
@SessionScoped
public class PersonController {

	@EJB
	PersonManager pm;

	PersonValidator validatePerson = new PersonValidator();
	private String name, title;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	Activity foundActivity = new Activity();

	public Activity getFoundActivity() {
		return foundActivity;
	}

	public void setFoundActivity(Activity foundActivity) {
		this.foundActivity = foundActivity;
	}

	Person thePerson = new Person();
	List<Person> findedPersons;

	Activity act = new Activity();
	List<Activity> findedActivities;

	public PersonValidator getValidatePerson() {
		return validatePerson;
	}

	public void setValidatePerson(PersonValidator validatePerson) {
		this.validatePerson = validatePerson;
	}

	public List<Activity> getFindedActivities() {
		return findedActivities;
	}

	public void setFindedActivities(List<Activity> findedActivities) {
		this.findedActivities = findedActivities;
	}

	@PostConstruct
	public void init() {
		System.out.println("Create " + this);
		if (pm == null)
			System.out.println("NULL");
	}

	public Person getThePerson() {
		return thePerson;
	}

	public void setThePerson(Person thePerson) {
		this.thePerson = thePerson;
	}

	public List<Person> getPersons() {
		return pm.showPersons();
	}

	public List<Activity> getActivities() {

		if (thePerson != null) {
			return pm.showActivities(thePerson);
		}

		return null;

	}

	public void setFindedPersons(List<Person> findedPersons) {
		this.findedPersons = findedPersons;
	}

	public Activity getAct() {
		return act;
	}

	// registering new user
	public String save() {

		thePerson.setName(validatePerson.getName());
		thePerson.setFirstName(validatePerson.getFirstName());
		thePerson.setBirthday(validatePerson.getBirthday());
		thePerson.setWebAddress(validatePerson.getWebAddress());
		thePerson.setEmail(validatePerson.getEmail());
		thePerson.setPassword(pm.MD5(validatePerson.getPassword()));
		try{
			pm.addPerson(thePerson);
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Inscription OK", "");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			validatePerson.setName(null);
			validatePerson.setFirstName(null);
			validatePerson.setBirthday(null);
			validatePerson.setEmail(null);
			validatePerson.setWebAddress(null);
			validatePerson.setPassword(null);
			}catch(org.apache.openjpa.persistence.EntityExistsException e){
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Votre e-mail existe deja", "");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}

		return "welcome?faces-redirect=true";
	}

	public String showResume(Person person) {
		thePerson = pm.showPerson(person);
		findedActivities = pm.showActivities(person);
		return "showResume?faces-redirect=true";
	}

	public String showPersonActivities(Person person) {
		setFindedActivities(pm.showActivities(person));
		return "searchResults?faces-redirect=true";
	}

	public List<Person> getFindedPersons() {
		return findedPersons;
	}

	public String findByTitle() {

		setFindedPersons(pm.findByTitle(title));

		return "searchResults?faces-redirect=true";
	}

}
