package com.resumeapp.web;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;

import com.resumeapp.business.AuthManager;
import com.resumeapp.business.PersonManager;
import com.resumeapp.entities.Activity;
import com.resumeapp.entities.Nature;
import com.resumeapp.entities.Person;

@ManagedBean(name = "auth")
@SessionScoped
public class AuthController {

	@EJB
	AuthManager am;

	@EJB
	PersonManager pm;

	private PersonValidator validatePerson = new PersonValidator();
	private ActivityValidator validateActivity = new ActivityValidator();
	private List<Nature> nature = new ArrayList<Nature>();
	private Person authPerson,thePerson;
	private Activity activity;
	private List<Activity> activities,findedActivities;

	public PersonValidator getValidatePerson() {
		return validatePerson;
	}

	public void setValidatePerson(PersonValidator validatePerson) {
		this.validatePerson = validatePerson;
	}

	public ActivityValidator getValidateActivity() {
		return validateActivity;
	}

	public void setValidateActivity(ActivityValidator validateActivity) {
		this.validateActivity = validateActivity;
	}

	public List<Activity> getActivities() {
		return activities;
	}

	public void setActivities(List<Activity> activities) {
		this.activities = activities;
	}

	@PostConstruct
	public void init() {
		nature.add(Nature.AUTRE);
		nature.add(Nature.EXPERIENCE_PROFESSIONNELLE);
		nature.add(Nature.FORMATION);

		validateActivity.setTitle(null);
		validateActivity.setDescription(null);
		// activity.setPerson(validateActivity.getPerson());
		validateActivity.setNature(null);
		validateActivity.setYear(null);
		validateActivity.setId(null);
	}

	String email, password;

	// -----------------------Getters & Setters------------------------------

	public Person getAuthPerson() {
		return authPerson;
	}

	public void setAuthPerson(Person authPerson) {
		this.authPerson = authPerson;
	}

	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Nature> getNature() {
		return nature;
	}

	public void setNature(List<Nature> nature) {
		this.nature = nature;
	}

	// -----------------------Les méthodes------------------------------

	public String authentification() {

		System.out.println(email + "##" + password);

		if (am.login(email, password) != null) {
			authPerson = am.login(email, password);
			activities = am.showActivities(getAuthPerson());
			authPerson.setActivities(activities);

			return "authHome?faces-redirect=true";
		} else
			return "login?faces-redirect=true";

	}



	public String addActivity() {
		
		Activity activity = new Activity();
		if (validateActivity.getTitle() != null) {
			activity.setTitle(validateActivity.getTitle());
			activity.setDescription(validateActivity.getDescription());
			// activity.setPerson(validateActivity.getPerson());
			activity.setNature(validateActivity.getNature());
			activity.setYear(validateActivity.getYear());
			activity.setId(validateActivity.getId());
			activity.setWebAddress(validateActivity.getWebAddress());
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "ajout d'activité OK", "");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			System.out.println(authPerson.getEmail());
			activity.setPerson(authPerson);
			System.out.println("*****************************" + activity.getId());
			am.saveActivity(activity);

			return "authHome?faces-redirect=true";
		}

		return null;
	}

	public String chargeActivity(Activity activity) {
		
		this.activity = activity;
		this.validateActivity.setDescription(this.activity.getDescription());
		this.validateActivity.setNature(this.activity.getNature());
		this.validateActivity.setTitle(this.activity.getTitle());
		this.validateActivity.setYear(this.activity.getYear());
		this.validateActivity.setId(this.activity.getId());
		this.validateActivity.setWebAddress(this.activity.getWebAddress());
		System.out.println("------------------->" + this.activity.getId());
		return "addActivity?faces-redirect=true";
	}

	public String deleteActivity(Activity activity) {

		am.removeActivity(activity);

		return "authHome?faces-redirect=true";

	}

	public String desactivateProfil() {
		am.removePerson(authPerson);
		return "hello?faces-redirect=true";

	}

	public String updateProfile() {

		validatePerson.setBirthday(authPerson.getBirthday());
		validatePerson.setEmail(authPerson.getEmail());
		validatePerson.setFirstName(authPerson.getFirstName());
		validatePerson.setPassword(authPerson.getPassword());
		validatePerson.setName(authPerson.getName());
		validatePerson.setWebAddress(authPerson.getWebAddress());
		
		

		return "updateProfile?faces-redirect=true";

	}

	public String updatePerson() {

		Person person = new Person();
		person.setBirthday(validatePerson.getBirthday());
		person.setEmail(validatePerson.getEmail());
		person.setFirstName(validatePerson.getFirstName());
		person.setName(validatePerson.getName());
		person.setWebAddress(validatePerson.getWebAddress());
		person.setId(authPerson.getId());
		System.out.println("--------------pleaaaaaaaaaaaaaaaaaaaaaaase---------------->" + validatePerson.getName());
		am.updatePerson(person);
		System.out.println("--------------pleaaaaaaaaaaaaaaaaaaaaaaase---------------->" + validatePerson.getName());
		this.authPerson = person;
		return "authHome?faces-redirect=true";

	}
	public String leave() {
		setAuthPerson(am.logout());
		return "showPersons?faces-redirect=true";
	}
	
	public String showResume() {
		thePerson = pm.showPerson(authPerson);
		findedActivities = pm.showActivities(authPerson);
		System.out.println("___________________-------------------"+thePerson.getEmail());
		return "showResume?faces-redirect=true";
	}

	public Person getThePerson() {
		return thePerson;
	}

	public void setThePerson(Person thePerson) {
		this.thePerson = thePerson;
	}

	public List<Activity> getFindedActivities() {
		return findedActivities;
	}

	public void setFindedActivities(List<Activity> findedActivities) {
		this.findedActivities = findedActivities;
	}

}
