package social.indiv.extensions;

import java.util.Hashtable;

import social.attributes.Age;
import social.attributes.Attribute;
import social.attributes.EconomicClass;
import social.attributes.EconomicStatus;
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
import social.indiv.AttributedIndiv;
import social.indiv.strats.IStrategySimilarity;

public class IndivMainAttributes {

	private Hashtable<String, Attribute> attList;

	private IStrategySimilarity stratSim;

	// private boolean fuzzySimilarityActivated;
	// private double similar_limit;

	/**
	 * 
	 */
	public IndivMainAttributes() {
		init();
	}

	public void initSim(IStrategySimilarity strat) {
		stratSim = strat;
	}

	private void init() {

		attList = new Hashtable<String, Attribute>();
		// age and ideology are missing, as they require additional info
		// They will be initialized later
		attList.put("sex", new Gender());
		attList.put("education", new Education());
		attList.put("economy", new EconomicStatus());
		attList.put("religion", new Religion());
		attList.put("homosex", new HomosexualityAcc());
		attList.put("pros", new ProstitAcc());
		attList.put("abort", new AbortAcc());
		attList.put("divor", new DivorceAcc());
		attList.put("eutha", new EuthanaAcc());
		attList.put("suici", new SuiciAcc());
		attList.put("churchAtt", new ChurchAttendance());
		attList.put("confChurch", new ConfChurch());
		attList.put("religPers", new ReligPerson());
	}

	public Age getAge() {
		return (Age) attList.get("age");
	}

	public void setAge(Age age) {
		attList.put("age", age);
	}

	public Gender getSex() {
		return (Gender) attList.get("sex");
	}

	public void setSex(Gender gender) {
		attList.put("sex", gender);
	}

	public Ideology getIdeology() {
		return (Ideology) attList.get("ideology");
	}

	public void setIdeology(Ideology ideology) {
		attList.put("ideology", ideology);
	}

	public Education getEducation() {
		return (Education) attList.get("education");
	}

	public void setEducation(Education education) {
		attList.put("education", education);
	}

	public Economy getEconomy() {
		return (Economy) attList.get("economy");
	}

	public void setEconomy(Economy economy) {
		attList.put("economy", economy);
	}

	public Religion getReligion() {
		return (Religion) attList.get("religion");
	}

	public void setReligion(Religion religion) {
		attList.put("religion", religion);
	}

	public HomosexualityAcc getHomosex() {
		return (HomosexualityAcc) attList.get("homosex");
	}

	public void setHomosex(HomosexualityAcc homosex) {
		attList.put("homosex", homosex);
	}

	public ProstitAcc getPros() {
		return (ProstitAcc) attList.get("pros");
	}

	public void setPros(ProstitAcc pros) {
		attList.put("pros", pros);
	}

	public AbortAcc getAbort() {
		return (AbortAcc) attList.get("abort");
	}

	public void setAbort(AbortAcc abort) {
		attList.put("abort", abort);
	}

	public DivorceAcc getDivor() {
		return (DivorceAcc) attList.get("divor");
	}

	public void setDivor(DivorceAcc divor) {
		attList.put("divor", divor);
	}

	public EuthanaAcc getEutha() {
		return (EuthanaAcc) attList.get("eutha");
	}

	public void setEutha(EuthanaAcc eutha) {
		attList.put("eutha", eutha);
	}

	public SuiciAcc getSuici() {
		return (SuiciAcc) attList.get("suici");
	}

	public void setSuici(SuiciAcc suici) {
		attList.put("suici", suici);
	}

	public ChurchAttendance getChurchAtt() {
		return (ChurchAttendance) attList.get("churchAtt");
	}

	public void setChurchAtt(ChurchAttendance churchAtt) {
		attList.put("churchAtt", churchAtt);
	}

	public ConfChurch getConfChurch() {
		return (ConfChurch) attList.get("confChurch");
	}

	public void setConfChurch(ConfChurch cf) {
		attList.put("ConfChurch", cf);
	}

	public ReligPerson getReligPerson() {
		return (ReligPerson) attList.get("religPers");
	}

	public void setReligPerson(ReligPerson rp) {
		attList.put("religPers", rp);
	}

	// ****************************************************************
	// Similarity:
	// ****************************************************************

	/**
	 * How similar are two individuals. Doesn't count if they are family or not
	 * 
	 * @param other
	 * @return
	 */
	public double similarity(AttributedIndiv other) {
		return stratSim.similarity(this, other.getMainAtts());
	}

	public boolean isSimilarTo(AttributedIndiv ind) {
		double sim = similarity(ind);
		double sim_limit = stratSim.getSimilarLimit();
		return sim >= sim_limit;
		/*
		 * if (!fuzzySimilarityActivated) return sim >= similar_limit; else
		 * return sim >= fuzzy_similar_limit;
		 */
	}

	/*
	 * public double fuzzySimilarity(AttributedIndiv influencer) { / Tomar el
	 * normalizado de cada uno: At1,At2 Tomar la distancia de cada
	 * uno:d(At1-At2) Negar la distancia para obtener sim(At1-At2)
	 * 
	 * Así con todos los Atts. Devolver T-Norma (sim(Atts)) Qué T-Norma???????
	 * Prod? 0.7 0.3 =0.2 NO... Min? 0.7 , 0.3 =0.3 NO Qué T-Norma???????
	 * lukasiewicz? <Prod NO Mejor agregación: Media: 0.7 , 0.3 = 0.5 SI
	 */
	/*
	 * 
	 * double distAge = distance(getAge().getNormalized(), influencer.getAge()
	 * .getNormalized()); double simAge = neg(distAge);
	 * 
	 * double distIdeol = distance(getIdeology().getNormalized(), influencer
	 * .getIdeology().getNormalized()); double simIdeol = neg(distIdeol);
	 * 
	 * double distEdu = distance(getEducation().getNormalized(), influencer
	 * .getEducation().getNormalized()); double simEdu = neg(distEdu);
	 * 
	 * double distEcon = distance(getEconomy().getNormalized(), influencer
	 * .getEconomy().getNormalized()); double simEcon = neg(distEcon);
	 * 
	 * double distRlg = distance(getReligion().getNormalized(), influencer
	 * .getReligion().getNormalized()); double simRlg = neg(distRlg); // global
	 * sim double sim = (simAge + simIdeol + simEdu + simEcon + simRlg) / 5; //
	 * double sim = (simSex + simAge + simIdeol + simEdu + simEcon + simRlg) // /
	 * 6;
	 * 
	 * return sim; }
	 * 
	 * private double classicalSimilarity(AttributedIndiv influencer) { double
	 * sim = 0; // if they have the same class or not // 1 String className =
	 * this.getClass().getName(); if
	 * (influencer.getClass().getName().equals(className)) sim = sim + 1; else
	 * sim = sim - 0.50; // if they have the same sex, more similarity. No
	 * penalization if not // 0.25 if ((getSex().isFemale() &&
	 * influencer.getSex().isFemale()) || (getSex().isMale() &&
	 * influencer.getSex().isMale())) sim = sim + 0.25; // if they belong to the
	 * same generation, more similarity // 0.25 int diffAges =
	 * Math.abs(getAge().getValue() - influencer.getAge().getValue()); if
	 * (diffAges <= 5) { sim = sim + 0.25; } else if (diffAges >= 15) { sim =
	 * sim - 0.25; } // if they have similar ideology, more similarity // 0.25
	 * double diffIdeologies = Math.abs(getIdeology().getIdeology() -
	 * influencer.getIdeology().getIdeology()); if (diffIdeologies <= 1) { sim =
	 * sim + 0.25; } else if (diffIdeologies >= 2) { sim = sim - 0.25; } // if
	 * they have similar education, more similarity // 0.25 int cmpEduc =
	 * (getEducation().compareTo(influencer.getEducation())); if (cmpEduc == 0)
	 * sim = sim + 0.25; else if (cmpEduc >= 2 || cmpEduc <= -2) sim = sim -
	 * 0.25; // if they have similar education, more similarity // 0.25 int
	 * cmpEco = (getEconomy().compareTo(influencer.getEconomy())); if (cmpEco ==
	 * 0) sim = sim + 0.25; else if (cmpEco >= 2 || cmpEco <= -2) sim = sim -
	 * 0.25; // if they have similar religion, more similarity // 0.25 int
	 * cmpRel = (getReligion().compareTo(influencer.getReligion())); if (cmpRel ==
	 * 0) sim = sim + 0.25; else if (cmpRel >= 2 || cmpRel <= -2) sim = sim -
	 * 0.25;
	 * 
	 * return sim; }
	 * 
	 * private double distance(double normalized, double normalized2) { if
	 * (normalized <= 1 && normalized >= 0 && normalized2 <= 1 && normalized2 >=
	 * 0) return Math.abs(normalized - normalized2); else return 0; }
	 * 
	 * private double neg(double dist) { if (dist > 1 || dist < 0) return 0;
	 * else return 1 - dist; }
	 */

	// ****************************************************************
	// Output, auxiliary:
	// ****************************************************************
	public String toString() {
		String str = (" was born in " + getAge().getBirthYear() + " (is "
				+ getAge().checkAgeState() + ")" + " and has an age of "
				+ getAge() + ", an ideology of " + getIdeology()
				+ ", an education of " + getEducation() + ", an economy of "
				+ getEconomy().getDoubleValue() + ", a religion of "
				+ getReligion() + "(" + getReligion().myReligType() + "), and is a ");

		if (getSex().isFemale())
			str += ("female.");
		else
			str += ("male.");

		return str;
	}

	public String toStringNormalizedAtts() {
		String str = (" was born in " + getAge().getBirthYear() + " (is "
				+ getAge().checkAgeState() + ")" + " and has an age of "
				+ getAge().getNormalized() + ", an ideology of "
				+ getIdeology().getNormalized() + ", an education of "
				+ getEducation().getNormalized() + ", an economy of "
				+ getEconomy().getNormalized() + ", a religion of "
				+ getReligion().getNormalized() + "("
				+ getReligion().myReligType() + "), and is a ");
		if (getSex().isFemale())
			str += ("female, " + getSex().getNormalized());
		else
			str += ("male, " + getSex().getNormalized());
		str += ("\n");
		str += ("  Its opinion about Abortion is " + getAbort().getNormalized()
				+ " and church att is " + getChurchAtt().getNormalized());
		return str;
	}

	// ****************************************************************
	// Crossing:
	// ****************************************************************

	public void cross(IndivMainAttributes mainAttsMother,
			IndivMainAttributes mainAttsFather) {

		if (mainAttsFather == null)
			clone(mainAttsMother);

		else {
			// Education:
			getEducation().cross(mainAttsMother.getEducation(),
					mainAttsFather.getEducation());

			// Economy:
			// generate a default one, that now will be modified... just to have
			// the "new" done.
			if (mainAttsMother.getEconomy() instanceof EconomicStatus)
				generate2300Economy(1);
			else
				generate500Economy(4);

			getEconomy().cross(mainAttsMother.getEconomy(),
					mainAttsFather.getEconomy());

			// Religion:
			double sonRlg = (mainAttsMother.getReligion().getReligiosity() + mainAttsFather
					.getReligion().getReligiosity()) / 2;
			getReligion().setReligiosity(sonRlg);

			// Ideology:
			double sonIdl = (mainAttsMother.getIdeology().getIdeology() + mainAttsFather
					.getIdeology().getIdeology()) / 2;
			getIdeology().setIdeology(sonIdl);

			// Acceptance grades:
			getHomosex().cross(mainAttsMother.getHomosex(),
					mainAttsFather.getHomosex());
			getPros().cross(mainAttsMother.getPros(), mainAttsFather.getPros());
			getAbort().cross(mainAttsMother.getAbort(),
					mainAttsFather.getAbort());
			getDivor().cross(mainAttsMother.getDivor(),
					mainAttsFather.getDivor());
			getEutha().cross(mainAttsMother.getEutha(),
					mainAttsFather.getEutha());
			getSuici().cross(mainAttsMother.getSuici(),
					mainAttsFather.getSuici());
		}
	}

	public void generate500Economy(int econ) {
		setEconomy(new EconomicClass());
		((EconomicClass) getEconomy()).setPositionExtended(econ);
	}

	public void generate2300Economy(double econ) {
		setEconomy(new EconomicStatus());
		((EconomicStatus) getEconomy()).setStatusr(econ);
	}

	void clone(IndivMainAttributes mother) {
		// Education:
		setEducation(mother.getEducation());

		// Economy:
		setEconomy(mother.getEconomy());

		// Religion:
		getReligion().setReligiosity(mother.getReligion().getReligiosity());

		// Ideology:
		getIdeology().setIdeology(mother.getIdeology().getIdeology());

		// Acceptance grades:
		setHomosex(mother.getHomosex());
		setPros(mother.getPros());
		setAbort(mother.getAbort());
		setDivor(mother.getDivor());
		setEutha(mother.getEutha());
		setSuici(mother.getSuici());
	}

}