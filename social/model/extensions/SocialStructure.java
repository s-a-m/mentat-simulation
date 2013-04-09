/**
 * 
 */
package social.model.extensions;

import java.io.File;
import java.util.ArrayList;

import social.attributes.Age;
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
import social.indiv.DemographicIndiv;

import com.csvreader.CsvReader;

/**
 * @author Samer
 * 
 */
public class SocialStructure {

	private static boolean activateEqShouldBeMarried;

	private static boolean activateEqProbHavingChildren;

	/**
	 * controls if the ABM has random initialization or the normal EVS loaded
	 * initial conditions; true= loads from EVS, false= random
	 */
	private static boolean empiricalInitialization;

	private boolean loadArtificialChildren;

	private Config config;

	private int numAdultsLoaded = 0;

	private int numChildrenLoaded = 0;

	//private String cvsFile500 = "input_data" + File.separator + "1980-0500.csv";

	private String cvsFile2300 = "input_data" + File.separator
			+ "1980-2300.csv";

	private String cvsFileUnderage1980 = "input_data" + File.separator
			+ "underage-data1980.csv";

	public SocialStructure(Config cfg) {
		this.config = cfg;
		activateEqShouldBeMarried = (Boolean) config
				.getParam("activateEqShouldBeMarried");

		activateEqProbHavingChildren = (Boolean) config
				.getParam("activateEqProbHavingChildren");

		empiricalInitialization = (Boolean) config
				.getParam("empiricalInitialization");
	}

	public int getNumChildrenLoaded() {
		return numChildrenLoaded;
	}

	public void setNumChildrenLoaded(int numChildrenLoaded) {
		this.numChildrenLoaded = numChildrenLoaded;
	}

	public int getNumAdultsLoaded() {
		return numAdultsLoaded;
	}

	public void setNumAdultsLoaded(int numAdultsLoaded) {
		this.numAdultsLoaded = numAdultsLoaded;
	}

	/**
	 * Chooses old or new method for reading population
	 * 
	 * @param numAgents
	 * @return
	 * @throws Exception
	 */
	public ArrayList<DemographicIndiv> readPopulation(boolean chosen2300,
			boolean loadArtificialChildren) throws Exception {
		this.loadArtificialChildren = loadArtificialChildren;

//		if (chosen2300) {
			if (empiricalInitialization)
				return readPopulation2300Csv();
			else
				return readPopulation2300Random();
//		} else
//			return readPopulation500Csv();
	}
/*
	*//**
	 * Reading the initial population
	 * 
	 * @param numAgents
	 * @return
	 * @throws Exception
	 *//*
	public ArrayList<DemographicIndiv> readPopulation500Csv() throws Exception {

		ArrayList<DemographicIndiv> population = new ArrayList<DemographicIndiv>();
		int i = 0;

		CsvReader reader = new CsvReader(cvsFile500, ';');
		reader.readHeaders();
		while (reader.readRecord()) {
			String relig = reader.get("TIPORELI");
			String ideolog = reader.get("ideolog");
			String sex = reader.get("SEXO");
			String age = reader.get("edad");
			String estudi = reader.get("estudi");
			String econ = reader.get("clase");

			String homosex = reader.get("homosex");
			String prostit = reader.get("prostit");
			String abort = reader.get("aborto");
			String divor = reader.get("divorcio");
			String eutana = reader.get("eutana");
			String suicid = reader.get("suicid");

			String churchAtt = clean(reader.get("misa"));

			DemographicIndiv ind = new DemographicIndiv(config);

			boolean bSex = true;
			if (sex.equals("1"))
				bSex = false;
			ind.getSex().setSex(bSex);

			ind.getAge().setAgeAndYear((int) Double.parseDouble(age));
			generateInternalVars(ind);

			ind.getEducation().setLevelExtended(Integer.parseInt(estudi));
			ind.getMainAtts().generate500Economy(Integer.parseInt(econ));
			// getEconomy().setPositionExtended(Integer.parseInt(econ));

			ind.getReligion().setTurnedReligiosity(Double.parseDouble(relig));
			ind.getIdeology().setLimitedIdeology(Double.parseDouble(ideolog));

			ind.getHomosex().setLimitedGrade(Double.parseDouble(homosex));
			ind.getPros().setLimitedGrade(Double.parseDouble(prostit));
			ind.getAbort().setLimitedGrade(Double.parseDouble(abort));
			ind.getDivor().setLimitedGrade(Double.parseDouble(divor));
			ind.getEutha().setLimitedGrade(Double.parseDouble(eutana));
			ind.getSuici().setLimitedGrade(Double.parseDouble(suicid));

			ind.getChurchAtt().setAttendance(Double.parseDouble(churchAtt));

			population.add(ind);
			i++;
		}

		numAdultsLoaded = i;

		reader.close();
		return population;
	}
*/
	/**
	 * Reading the initial population
	 * 
	 * @param numAgents
	 * @return
	 * @throws Exception
	 */
	public ArrayList<DemographicIndiv> readPopulation2300Csv() throws Exception {

		ArrayList<DemographicIndiv> population = new ArrayList<DemographicIndiv>();
		int i = 0;
		CsvReader reader = new CsvReader(cvsFile2300, ';');
		reader.readHeaders();
		while (reader.readRecord()) {
			String relig = clean(reader.get("TIPORELI"));
			String ideolog = clean(reader.get("v123"));
			String sex = clean(reader.get("v214"));
			String age = clean(reader.get("v216"));
			String educ = clean(reader.get("v218"));
			String econ = clean(reader.get("STATUSR"));

			String homosex = clean(reader.get("v197"));
			String prostit = clean(reader.get("v198"));
			String abort = clean(reader.get("v199"));
			String divor = clean(reader.get("v200"));
			String euthana = clean(reader.get("v201"));
			String suicid = clean(reader.get("v202"));

			String churchAtt = clean(reader.get("v181"));

			String confChurch = clean(reader.get("v135"));
			String religPerson = clean(reader.get("v182"));

			DemographicIndiv ind = new DemographicIndiv(config);

			// female:
			boolean bSex = true;
			// male:
			if (sex.equals("1"))
				bSex = false;
			ind.getSex().setSex(bSex);

			ind.getAge().setAgeAndYear((int) Double.parseDouble(age));
			generateInternalVars(ind);

			ind.getEducation().setLevelExtended(Integer.parseInt(educ));
			ind.getMainAtts().generate2300Economy(Double.parseDouble(econ));

			ind.getReligion().setTurnedReligiosity(Double.parseDouble(relig));
			ind.getIdeology().setLimitedIdeology(Double.parseDouble(ideolog));

			ind.getHomosex().setLimitedGrade(Double.parseDouble(homosex));
			ind.getPros().setLimitedGrade(Double.parseDouble(prostit));
			ind.getAbort().setLimitedGrade(Double.parseDouble(abort));
			ind.getDivor().setLimitedGrade(Double.parseDouble(divor));
			ind.getEutha().setLimitedGrade(Double.parseDouble(euthana));
			ind.getSuici().setLimitedGrade(Double.parseDouble(suicid));

			ind.getChurchAtt().setAttendance(Double.parseDouble(churchAtt));
			ind.getConfChurch().setConfChurch(Double.parseDouble(confChurch));
			ind.getReligPerson().setReligPers(Double.parseDouble(religPerson));

			population.add(ind);
			i++;
		}
		numAdultsLoaded = i;
		reader.close();
		// Adding children in the beginning, artificially:
		if (loadArtificialChildren)
			population.addAll(insertChildrenFrom1980());

		return population;
	}

	/**
	 * Reading the initial population
	 * 
	 * @param numAgents
	 * @return
	 * @throws Exception
	 */
	public ArrayList<DemographicIndiv> readPopulation2300Random()
			throws Exception {
		ArrayList<DemographicIndiv> population = new ArrayList<DemographicIndiv>();
		int i = 0;
		while (i < 2303) {

			DemographicIndiv ind = new DemographicIndiv(config);

			Gender gender = new Gender();
			gender.generateRandomValue();
			ind.setSex(gender);

			Age age = new Age(1980);
			age.generateRandomValue();
			ind.setAge(age);
			generateInternalVars(ind);

			Education ed = new Education();
			ed.generateRandomValue();
			ind.setEducation(ed);

			Economy ec = new EconomicStatus();
			ec.generateRandomValue();
			ind.setEconomy(ec);

			Religion rlg = new Religion();
			rlg.generateRandomValue();
			ind.setReligion(rlg);

			Ideology id = new Ideology(ind);
			id.generateRandomValue();
			ind.setIdeology(id);

			HomosexualityAcc hom = new HomosexualityAcc();
			hom.generateRandomValue();
			ind.setHomosex(hom);

			ProstitAcc pr = new ProstitAcc();
			pr.generateRandomValue();
			ind.setPros(pr);

			DivorceAcc dv = new DivorceAcc();
			dv.generateRandomValue();
			ind.setDivor(dv);

			EuthanaAcc eu = new EuthanaAcc();
			eu.generateRandomValue();
			ind.setEutha(eu);

			AbortAcc ab = new AbortAcc();
			ab.generateRandomValue();
			ind.setAbort(ab);

			SuiciAcc sui = new SuiciAcc();
			sui.generateRandomValue();
			ind.setSuici(sui);

			ChurchAttendance ch = new ChurchAttendance();
			ch.generateRandomValue();
			ind.setChurchAtt(ch);

			ConfChurch cf = new ConfChurch();
			cf.generateRandomValue();
			ind.setConfChurch(cf);

			ReligPerson rp = new ReligPerson();
			rp.generateRandomValue();
			ind.setReligPerson(rp);

			population.add(ind);
			i++;
		}
		numAdultsLoaded = i;
		// Adding children in the beginning, artificially:
		if (loadArtificialChildren)
			population.addAll(insertChildrenRandom());

		return population;
	}

	private ArrayList<DemographicIndiv> insertChildrenFrom1980()
			throws Exception {

		ArrayList<DemographicIndiv> population = new ArrayList<DemographicIndiv>();
		int i = 0;

		CsvReader reader = new CsvReader(cvsFileUnderage1980, ';');
		reader.readHeaders();
		while (reader.readRecord()) {
			String relig = clean(reader.get("tiporeli"));
			String ideolog = clean(reader.get("v123"));
			String sex = clean(reader.get("v214"));
			String age = clean(reader.get("edadnuev")); // !!
			String educ = clean(reader.get("v218"));
			String econ = clean(reader.get("STATUSR"));

			String homosex = clean(reader.get("v197"));
			String prostit = clean(reader.get("v198"));
			String abort = clean(reader.get("v199"));
			String divor = clean(reader.get("v200"));
			String euthana = clean(reader.get("v201"));
			String suicid = clean(reader.get("v202"));

			String churchAtt = clean(reader.get("v181"));

			String confChurch = clean(reader.get("v135"));
			String religPerson = clean(reader.get("v182"));

			DemographicIndiv ind = new DemographicIndiv(config);

			boolean bSex = true;
			if (sex.equals("1"))
				bSex = false;
			ind.getSex().setSex(bSex);

			ind.getAge().setAgeAndYear((int) Double.parseDouble(age));
			generateInternalVars(ind);

			ind.getEducation().setLevelExtended(Integer.parseInt(educ));
			ind.getMainAtts().generate2300Economy(Double.parseDouble(econ));

			ind.getReligion().setTurnedReligiosity(Double.parseDouble(relig));
			ind.getIdeology().setLimitedIdeology(Double.parseDouble(ideolog));

			ind.getHomosex().setLimitedGrade(Double.parseDouble(homosex));
			ind.getPros().setLimitedGrade(Double.parseDouble(prostit));
			ind.getAbort().setLimitedGrade(Double.parseDouble(abort));
			ind.getDivor().setLimitedGrade(Double.parseDouble(divor));
			ind.getEutha().setLimitedGrade(Double.parseDouble(euthana));
			ind.getSuici().setLimitedGrade(Double.parseDouble(suicid));

			ind.getChurchAtt().setAttendance(Double.parseDouble(churchAtt));
			ind.getConfChurch().setConfChurch(Double.parseDouble(confChurch));
			ind.getReligPerson().setReligPers(Double.parseDouble(religPerson));

			ind.notComparableWithEVS();

			population.add(ind);

			i++;
		}
		numChildrenLoaded = i;
		reader.close();

		return population;
	}

	private ArrayList<DemographicIndiv> insertChildrenRandom() throws Exception {

		ArrayList<DemographicIndiv> population = new ArrayList<DemographicIndiv>();
		int i = 0;

		while (i < 716) {

			DemographicIndiv ind = new DemographicIndiv(config);

			Gender gender = new Gender();
			gender.generateRandomValue();
			ind.setSex(gender);

			Age age = new Age(1980);
			age.generateRandomValue();
			ind.setAge(age);
			generateInternalVars(ind);

			Education ed = new Education();
			ed.generateRandomValue();
			ind.setEducation(ed);

			Economy ec = new EconomicStatus();
			ec.generateRandomValue();
			ind.setEconomy(ec);

			Religion rlg = new Religion();
			rlg.generateRandomValue();
			ind.setReligion(rlg);

			Ideology id = new Ideology(ind);
			id.generateRandomValue();
			ind.setIdeology(id);

			HomosexualityAcc hom = new HomosexualityAcc();
			hom.generateRandomValue();
			ind.setHomosex(hom);

			ProstitAcc pr = new ProstitAcc();
			pr.generateRandomValue();
			ind.setPros(pr);

			DivorceAcc dv = new DivorceAcc();
			dv.generateRandomValue();
			ind.setDivor(dv);

			EuthanaAcc eu = new EuthanaAcc();
			eu.generateRandomValue();
			ind.setEutha(eu);

			AbortAcc ab = new AbortAcc();
			ab.generateRandomValue();
			ind.setAbort(ab);

			SuiciAcc sui = new SuiciAcc();
			sui.generateRandomValue();
			ind.setSuici(sui);

			ChurchAttendance ch = new ChurchAttendance();
			ch.generateRandomValue();
			ind.setChurchAtt(ch);
			
			ConfChurch cf = new ConfChurch();
			cf.generateRandomValue();
			ind.setConfChurch(cf);

			ReligPerson rp = new ReligPerson();
			rp.generateRandomValue();
			ind.setReligPerson(rp);

			ind.notComparableWithEVS();

			population.add(ind);

			i++;
		}

		numChildrenLoaded = i;
		return population;
	}

	private String clean(String cleanable) {
		if (!cleanable.contains("#"))
			return cleanable;

		String cleaned = cleanable.substring(2);
		if (cleaned.startsWith("N"))
			return "999";
		else
			return cleanable;
	}

	private void generateInternalVars(DemographicIndiv ind) {
		int age = ind.getAge().getValue();
		ind.setShouldBeMarried(generateShouldBeMarried(age));
		ind.setProbHavingChildren(generateProbHavingChildren(age));
	}

	/**
	 * Function for generating the attribute of "should be married according to
	 * EVS". Instead of reading the EVS variables, a regression equation has
	 * been defined
	 * 
	 * @return
	 */
	private boolean generateShouldBeMarried(int age) {
		// regression equation for knowing probability of being single:
		// y = -0,0014x^3 + 0,2921x^2 - 17,965x + 343,85 for x < 45
		// y = 0 for x >= 45

		double probSingle = 0;

		if (age < 45) {
			probSingle = -0.0014 * Math.pow(age, 3.0) + 0.2921
					* Math.pow(age, 2.0) - 17.965 * age + 343.85;
		}

		double rnd = Math.random() * 100;
		// if probSingle==61% (age 24, year 1980) and rnd==45, rnd<probSingle
		// --> is not married

		// if the agent is single, return married= false
		boolean result = !(rnd < probSingle);

		if (activateEqShouldBeMarried)
			return result;
		else
			return false;
	}

	private boolean generateProbHavingChildren(int age) {
		// regression equation for probability of having children, depending on
		// age:
		// y = -0,0018x^3 + 0,319x^2 - 18,3x + 349,9 for x < 49
		// y = 7.3508 for x >= 49
		double probChildren = 7.3508;
		if (age < 49) {
			probChildren = -0.0018 * Math.pow(age, 3.0) + 0.319
					* Math.pow(age, 2.0) - 18.3 * age + 349.9;
		}
		double rnd = Math.random() * 100;
		// if probChildren==63% (age 25, year 1980) and rnd==45,
		// rnd<probChildren
		// --> can have children

		// the agent can have children
		boolean result = (rnd < probChildren);

		if (activateEqProbHavingChildren)
			return result;
		else
			return true;
	}
}