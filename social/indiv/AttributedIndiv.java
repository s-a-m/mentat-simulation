package social.indiv;

import java.awt.Color;

import social.attributes.Age;
import social.attributes.Economy;
import social.attributes.Education;
import social.attributes.Ideology;
import social.attributes.Religion;
import social.attributes.Gender;
import social.attributes.degrees.AbortAcc;
import social.attributes.degrees.DivorceAcc;
import social.attributes.degrees.EuthanaAcc;
import social.attributes.degrees.HomosexualityAcc;
import social.attributes.degrees.ProstitAcc;
import social.attributes.degrees.SuiciAcc;
import social.attributes.minor.ChurchAttendance;
import social.attributes.minor.ConfChurch;
import social.attributes.minor.ReligPerson;
import social.indiv.extensions.IndivMainAttributes;
import social.model.extensions.Demography;
import uchicago.src.sim.gui.SimGraphics;

/**
 * @author Samer
 * 
 */
public class AttributedIndiv extends SimpleIndiv {

	/**
	 * Main attributes
	 */
	protected IndivMainAttributes mainAtts;

	public AttributedIndiv() {
		init();
	}

	public void init() {
		mainAtts = new IndivMainAttributes();
		// The only main-attributes that need external info:
		getMainAtts().setAge(new Age(Demography.getYear()));
		getMainAtts().setIdeology(new Ideology(this));

		this.setBorderColor(Color.red);
		this.setBorderWidth(4);
	}

	public Religion getReligion() {
		return getMainAtts().getReligion();
	}

	public void setReligion(Religion religion) {
		this.getMainAtts().setReligion(religion);
	}

	public Education getEducation() {
		return getMainAtts().getEducation();
	}

	public void setEducation(Education education) {
		this.getMainAtts().setEducation(education);
	}

	public Economy getEconomy() {
		return getMainAtts().getEconomy();
	}

	public void setEconomy(Economy economy) {
		this.getMainAtts().setEconomy(economy);
	}

	public HomosexualityAcc getHomosex() {
		return getMainAtts().getHomosex();
	}

	public void setHomosex(HomosexualityAcc homosex) {
		this.getMainAtts().setHomosex(homosex);
	}

	public ProstitAcc getPros() {
		return getMainAtts().getPros();
	}

	public void setPros(ProstitAcc pros) {
		this.getMainAtts().setPros(pros);
	}

	public AbortAcc getAbort() {
		return getMainAtts().getAbort();
	}

	public void setAbort(AbortAcc abort) {
		this.getMainAtts().setAbort(abort);
	}

	public DivorceAcc getDivor() {
		return getMainAtts().getDivor();
	}

	public void setDivor(DivorceAcc divor) {
		this.getMainAtts().setDivor(divor);
	}

	public EuthanaAcc getEutha() {
		return getMainAtts().getEutha();
	}

	public void setEutha(EuthanaAcc euta) {
		this.getMainAtts().setEutha(euta);
	}

	public SuiciAcc getSuici() {
		return getMainAtts().getSuici();
	}

	public void setSuici(SuiciAcc suici) {
		this.getMainAtts().setSuici(suici);
	}

	public Ideology getIdeology() {
		return getMainAtts().getIdeology();
	}

	public void setIdeology(Ideology ideology) {
		this.getMainAtts().setIdeology(ideology);
	}

	public Gender getSex() {
		return getMainAtts().getSex();
	}

	public void setSex(Gender gender) {
		this.getMainAtts().setSex(gender);
	}

	public String getID() {
		return label;
	}

	/**
	 * @return Returns the ageSteps.
	 */
	public Age getAge() {
		return getMainAtts().getAge();
	}

	/**
	 * @param ageSteps
	 *            The ageSteps to set.
	 */
	public void setAge(Age age) {
		this.getMainAtts().setAge(age);
	}

	public boolean female() {
		return getMainAtts().getSex().isFemale();
	}

	public boolean male() {
		return getMainAtts().getSex().isMale();
	}

	public ChurchAttendance getChurchAtt() {
		return getMainAtts().getChurchAtt();
	}

	public void setChurchAtt(ChurchAttendance churchAtt) {
		this.getMainAtts().setChurchAtt(churchAtt);
	}
	
	public ConfChurch getConfChurch() {
		return getMainAtts().getConfChurch();
	}

	public void setConfChurch(ConfChurch cf) {
		this.getMainAtts().setConfChurch(cf);
	}

	public ReligPerson getReligPerson() {
		return getMainAtts().getReligPerson();
	}

	public void setReligPerson(ReligPerson rp) {
		this.getMainAtts().setReligPerson(rp);
	}


	public void setXY(int newX, int newY) {
		this.setX(newX);
		this.setY(newY);
	}

	public void print(Object str) {
		System.out.println(str);
	}

	public boolean child() {
		return getMainAtts().getAge().checkAgeState().equals("child");
	}

	public boolean adult() {
		return getMainAtts().getAge().checkAgeState().equals("adult");
	}

	public boolean old() {
		return getMainAtts().getAge().checkAgeState().equals("old");
	}

	public boolean grownUp() {
		return !child();
	}

	public void setMainAtts(IndivMainAttributes mainAtts) {
		this.mainAtts = mainAtts;
	}

	public IndivMainAttributes getMainAtts() {
		return mainAtts;
	}

	// ****************************************************************
	// Node:
	// ****************************************************************

	public void draw(SimGraphics G) {
		if (child()) {
			if (getMainAtts().getSex().isFemale())
				G.drawFastRoundRect(Color.yellow);
			else
				G.drawFastRoundRect(Color.orange);
		} else if (adult()) {
			if (getMainAtts().getSex().isFemale())
				G.drawFastRoundRect(Color.magenta);
			else
				G.drawFastRoundRect(Color.blue);
		} else {
			if (getMainAtts().getSex().isFemale())
				G.drawFastRoundRect(Color.lightGray);
			else
				G.drawFastRoundRect(Color.gray);
		}
	}

	// ****************************************************************
	// Reporting methods:
	// ****************************************************************

	public void report() {
		print(toString());
	}

	public String toString() {
		String className = getClass().getSimpleName();
		String str = (className + ":" + getID() + " at (" + getX() + ","
				+ getY() + ")" + getMainAtts().toString());
		str += ("\n");

		return str;
	}

	public String toStringNormalizedAtts() {
		String className = getClass().getSimpleName();
		String str = (className + ":" + getID() + " at (" + getX() + ","
				+ getY() + ")" + getMainAtts().toStringNormalizedAtts());
		str += ("\n");
		return str;
	}

}