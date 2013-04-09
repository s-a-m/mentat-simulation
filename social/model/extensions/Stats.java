package social.model.extensions;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Hashtable;
import java.util.Set;

import social.attributes.Education;
import social.indiv.AttributedIndiv;
import social.indiv.DemographicIndiv;

import com.csvreader.CsvWriter;

/**
 * It will keep all the information that will be shown at the end
 * 
 * @author samer
 * 
 */
public class Stats {

	// private String cvsFileStats1980 = "output" + File.pathSeparatorChar +
	// "stats1980.csv";

	private String cvsFileStats1980 = "stats1980.csv";

	private String cvsFileStats1990 = "stats1990.csv";

	private String cvsFileStats1999 = "stats1999.csv";

	private String cvsFileStatsGeneric = "statsGen.csv";

	private String cvsFileDMOutput = "statsDMOutput.csv";
	

	/*
	 * 1980, 1990 y 1999 values
	 */
	private Hashtable<String, Object> table1980;

	private Hashtable<String, Object> table1990;

	private Hashtable<String, Object> table1999;

	private Config config;

	private DecimalFormat df;

	/**
	 * Needed for optimization of calculations. This way it has not to be
	 * calculared for every graph. It will count the "comparable agents with
	 * EVS" (loaded from EVS and born, not the children inserted)
	 */
	private double countGrownUp;

	private ArrayList<DemographicIndiv> agentList;

	private double counterGeneric;


	public Stats(Config config) {
		this.config = config;
		this.table1980 = new Hashtable<String, Object>();
		this.table1990 = new Hashtable<String, Object>();
		this.table1999 = new Hashtable<String, Object>();
		df = new DecimalFormat("#,###,##0.00");
		counterGeneric = 0;
	}

	public ArrayList<DemographicIndiv> getAgentList() {
		return agentList;
	}

	public void setAgentList(ArrayList<DemographicIndiv> agentList) {
		this.agentList = agentList;
	}

	public void saveStatValidationYear(String key, Object value,
			Hashtable<String, Object> table) {
		table.put(key, value);
	}

	public double meanEconomy() {
		double mean = 0.0;
		double eco = 0;
		for (int i = 0; i < agentList.size(); i++) {
			// fitering: for comparison, only the comparable ones (removing
			// introduced children or all the children)
			// if (agentList.get(i).isComparableWithEVS()) {
			if (agentList.get(i).grownUp()) {
				eco = agentList.get(i).getEconomy().getDoubleValue();
				mean = mean + eco;
			}
		}
		mean = mean / countGrownUp;
		return mean;
	}

	public double meanEducation() {
		double mean = 0.0;
		for (int i = 0; i < agentList.size(); i++) {
			// fitering: for comparison, only the comparable ones (removing
			// introduced children or all the children)
			if (agentList.get(i).grownUp()) {
				mean += ((AttributedIndiv) agentList.get(i)).getEducation()
						.levelToDouble();
			}
		}
		mean = mean / countGrownUp;
		return mean;
	}

	public double meanCategorizedEducation() {
		double mean = meanEducation();
		return Education.getLevelFromDouble(mean);
	}

	public double meanIdeology() {
		double mean = 0.0;
		counterGeneric = 0;
		for (int i = 0; i < agentList.size(); i++) {
			// fitering: for comparison, only the comparable ones (removing
			// introduced children or all the children)
			if (agentList.get(i).grownUp()
					&& !agentList.get(i).getIdeology().isNoAnswer()) {
				counterGeneric++;
				mean += ((AttributedIndiv) agentList.get(i)).getIdeology()
						.getIdeology();
			}
		}
		mean = mean / counterGeneric;
		return mean;
	}

	public double meanIdeologyLeft() {
		double mean = 0.0;
		counterGeneric = 0;
		for (int i = 0; i < agentList.size(); i++) {
			// fitering: for comparison, only the comparable ones (removing
			// introduced children or all the children)
			if (agentList.get(i).grownUp()) {
				if (agentList.get(i).getIdeology().isLeft()) {
					counterGeneric++;
					mean += agentList.get(i).getIdeology().getIdeology();
				}
			}
		}
		mean = mean / counterGeneric;
		return mean;
	}

	public double meanIdeologyRight() {
		double mean = 0.0;
		counterGeneric = 0;
		for (int i = 0; i < agentList.size(); i++) {
			// fitering: for comparison, only the comparable ones (removing
			// introduced children or all the children)
			if (agentList.get(i).grownUp()) {
				if (agentList.get(i).getIdeology().isRight()) {
					counterGeneric++;
					mean += agentList.get(i).getIdeology().getIdeology();
				}
			}
		}
		mean = mean / counterGeneric;
		return mean;
	}

	public double meanIdeologyCentre() {
		double mean = 0.0;
		counterGeneric = 0;
		for (int i = 0; i < agentList.size(); i++) {
			// fitering: for comparison, only the comparable ones (removing
			// introduced children or all the children)
			if (agentList.get(i).grownUp()) {
				if (agentList.get(i).getIdeology().isCentre()) {
					counterGeneric++;
					mean += agentList.get(i).getIdeology().getIdeology();
				}
			}
		}
		mean = mean / counterGeneric;
		return mean;
	}

	public double meanIdeologyNoAnswer() {
		double mean = 0.0;
		counterGeneric = 0;
		for (int i = 0; i < agentList.size(); i++) {
			// fitering: for comparison, only the comparable ones (removing
			// introduced children or all the children)
			if (agentList.get(i).grownUp()) {
				if (agentList.get(i).getIdeology().isNoAnswer()) {
					counterGeneric++;
					mean += agentList.get(i).getIdeology().getIdeology();
				}
			}
		}
		if (counterGeneric > 0)
			mean = mean / counterGeneric;
		return mean;
	}

	public double meanReligion() {
		double mean = 0.0;
		for (int i = 0; i < agentList.size(); i++) {
			// fitering: for comparison, only the comparable ones (removing
			// introduced children or all the children)
			if (agentList.get(i).grownUp()) {
				mean += agentList.get(i).getReligion().rlgToStndDouble();
			}
		}
		mean = mean / countGrownUp;
		return mean;
	}

	public double religClassif(String type) {
		double number = 0.0;
		for (int i = 0; i < agentList.size(); i++) {
			// fitering: for comparison, only the comparable ones (removing
			// introduced children or all the children)
			if (agentList.get(i).grownUp()) {
				if (((AttributedIndiv) agentList.get(i)).getReligion()
						.isClassif(type))
					number++;
			}
		}
		number = number / countGrownUp * 100;
		return number;
	}

	public double homosexAccPorc(int type) {
		double number = 0.0;
		for (int i = 0; i < agentList.size(); i++) {
			// fitering: for comparison, only the comparable ones (removing
			// introduced children or all the children)
			if (agentList.get(i).grownUp()) {
				if (((AttributedIndiv) agentList.get(i)).getHomosex().isOfType(
						type))
					number += 1;
			}
		}
		number = number / countGrownUp;
		return number;
	}

	public double meanHomosexToleranceNo99() {
		double sum = 0.0;
		counterGeneric = 0;
		for (int i = 0; i < agentList.size(); i++) {
			// fitering: for comparison, only the comparable ones (removing
			// introduced children or all the children)
			if (agentList.get(i).grownUp()) {
				// if not of type 99
				if (!((AttributedIndiv) agentList.get(i)).getHomosex()
						.isOfType(99)) {
					counterGeneric++;
					sum = sum + agentList.get(i).getHomosex().getGrade();
				}
			}
		}
		return sum / counterGeneric;
	}

	public double prosAccPorc(int type) {
		double number = 0.0;
		for (int i = 0; i < agentList.size(); i++) {
			// fitering: for comparison, only the comparable ones (removing
			// introduced children or all the children)
			if (agentList.get(i).grownUp()) {
				if (((AttributedIndiv) agentList.get(i)).getPros().isOfType(
						type))
					number += 1;
			}
		}
		number = number / countGrownUp;
		return number;
	}

	public double meanProsToleranceNo99() {
		double sum = 0.0;
		counterGeneric = 0;
		for (int i = 0; i < agentList.size(); i++) {
			// fitering: for comparison, only the comparable ones (removing
			// introduced children or all the children)
			if (agentList.get(i).grownUp()) {
				// if not of type 99
				if (!((AttributedIndiv) agentList.get(i)).getPros()
						.isOfType(99)) {
					counterGeneric++;
					sum = sum + agentList.get(i).getPros().getGrade();
				}
			}
		}
		return sum / counterGeneric;
	}

	public double abortAccPorc(int type) {
		double number = 0.0;
		for (int i = 0; i < agentList.size(); i++) {
			// fitering: for comparison, only the comparable ones (removing
			// introduced children or all the children)
			if (agentList.get(i).grownUp()) {
				if (((AttributedIndiv) agentList.get(i)).getAbort().isOfType(
						type))
					number += 1;
			}
		}
		number = number / countGrownUp;
		return number;
	}

	public double meanAbortToleranceNo99() {
		double sum = 0.0;
		counterGeneric = 0;
		for (int i = 0; i < agentList.size(); i++) {
			// fitering: for comparison, only the comparable ones (removing
			// introduced children or all the children)
			if (agentList.get(i).grownUp()) {
				// if not of type 99
				if (!((AttributedIndiv) agentList.get(i)).getAbort().isOfType(
						99)) {
					counterGeneric++;
					sum = sum + agentList.get(i).getAbort().getGrade();
				}
			}
		}
		return sum / counterGeneric;
	}

	public double divorAccPorc(int type) {
		double number = 0.0;
		for (int i = 0; i < agentList.size(); i++) {
			// fitering: for comparison, only the comparable ones (removing
			// introduced children or all the children)
			if (agentList.get(i).grownUp()) {
				if (((AttributedIndiv) agentList.get(i)).getDivor().isOfType(
						type))
					number += 1;
			}
		}
		number = number / countGrownUp;
		return number;
	}

	public double meanDivorToleranceNo99() {
		double sum = 0.0;
		counterGeneric = 0;
		for (int i = 0; i < agentList.size(); i++) {
			// fitering: for comparison, only the comparable ones (removing
			// introduced children or all the children)
			if (agentList.get(i).grownUp()) {
				// if not of type 99
				if (!((AttributedIndiv) agentList.get(i)).getDivor().isOfType(
						99)) {
					counterGeneric++;
					sum = sum + agentList.get(i).getDivor().getGrade();
				}
			}
		}
		return sum / counterGeneric;
	}

	public double euthaAccPorc(int type) {
		double number = 0.0;
		for (int i = 0; i < agentList.size(); i++) {
			// fitering: for comparison, only the comparable ones (removing
			// introduced children or all the children)
			if (agentList.get(i).grownUp()) {
				if (((AttributedIndiv) agentList.get(i)).getEutha().isOfType(
						type))
					number += 1;
			}
		}
		number = number / countGrownUp;
		return number;
	}

	public double meanEuthaToleranceNo99() {
		double sum = 0.0;
		counterGeneric = 0;
		for (int i = 0; i < agentList.size(); i++) {
			// fitering: for comparison, only the comparable ones (removing
			// introduced children or all the children)
			if (agentList.get(i).grownUp()) {
				// if not of type 99
				if (!((AttributedIndiv) agentList.get(i)).getEutha().isOfType(
						99)) {
					counterGeneric++;
					sum = sum + agentList.get(i).getEutha().getGrade();
				}
			}
		}
		return sum / counterGeneric;
	}

	public double suiciAccPorc(int type) {
		double number = 0.0;
		for (int i = 0; i < agentList.size(); i++) {
			// fitering: for comparison, only the comparable ones (removing
			// introduced children or all the children)
			if (agentList.get(i).grownUp()) {
				if (((AttributedIndiv) agentList.get(i)).getSuici().isOfType(
						type))
					number += 1;
			}
		}
		number = number / countGrownUp;
		return number;
	}

	public double meanSuiciToleranceNo99() {
		double sum = 0.0;
		counterGeneric = 0;
		for (int i = 0; i < agentList.size(); i++) {
			// fitering: for comparison, only the comparable ones (removing
			// introduced children or all the children)
			if (agentList.get(i).grownUp()) {
				// if not of type 99
				if (!((AttributedIndiv) agentList.get(i)).getSuici().isOfType(
						99)) {
					counterGeneric++;
					sum = sum + agentList.get(i).getSuici().getGrade();
				}
			}
		}
		return sum / counterGeneric;
	}

	private void updatingCountValid() {
		countGrownUp = 0.0;
		for (int i = 0; i < agentList.size(); i++) {
			// if (agentList.get(i).isComparableWithEVS())
			if (agentList.get(i).grownUp())
				countGrownUp++;
		}
	}

	public void printMainStatistics(
			ArrayList<DemographicIndiv> allEverSimulatedAgents,
			int numInitialNumberAgs, int steps) {
		double totalNumFriends = 0;
		double maxNumFriends = -1;
		double minNumFriends = 1000;
		double totalNumRelatives = 0;
		double maxNumRelatives = -1;
		double minNumRelatives = 1000;
		/*
		 * double totalNumMarried = 0; double totalNumShouldBeMarried = 0; int
		 * totalFemaleAgents = 0; int totalFemaleAgentsNow = 0;
		 */
		int numChildrenNow = 0;
		int numAdultsNow = 0;
		int numOldsNow = 0;
		int numChildren = 0;
		int numAdults = 0;
		int numOlds = 0;
		// No vale agentList: necesito un array de todos los individuos
		// que mueran + agentList (el q tengo en LANG)
		for (DemographicIndiv ind : agentList) {
			ind.saveStatistics();
			allEverSimulatedAgents.add(ind);

			// if (ind.female())
			// totalFemaleAgentsNow++;

			if (ind.child())
				numChildrenNow++;
			else if (ind.adult())
				numAdultsNow++;
			else if (ind.old())
				numOldsNow++;
		}

		for (DemographicIndiv ind : allEverSimulatedAgents) {
			totalNumFriends += ind.getStatTotalNumFriends();
			maxNumFriends = Math.max(ind.getStatTotalNumFriends(),
					maxNumFriends);
			minNumFriends = Math.min(ind.getStatTotalNumFriends(),
					minNumFriends);

			totalNumRelatives += ind.getStatTotalNumRelatives();
			maxNumRelatives = Math.max(ind.getStatTotalNumRelatives(),
					maxNumRelatives);
			minNumRelatives = Math.min(ind.getStatTotalNumRelatives(),
					minNumRelatives);

			// totalNumMarried += ind.getStatMarried();
			// if (ind.isShouldBeMarried())
			// totalNumShouldBeMarried += 1;

			// if (ind.female())
			// totalFemaleAgents++;

			if (ind.child())
				numChildren++;
			else if (ind.adult())
				numAdults++;
			else if (ind.old())
				numOlds++;
		}

		print("**** General statistics from simulation ****");
		print(new Date());
		print("After " + steps + " steps");
		print("*");
		print("Number of initial agents: " + numInitialNumberAgs);
		print("Number of agents now: " + agentList.size());
		print("Number of agents born: "
				+ (allEverSimulatedAgents.size() - numInitialNumberAgs));
		print("Number of agents' deaths: "
				+ (allEverSimulatedAgents.size() - agentList.size()));
		print("*");
		print("Maximum Number of friends along simulation: " + maxNumFriends);
		print("Average Number of friends along simulation: "
				+ format(totalNumFriends / allEverSimulatedAgents.size()));
		print("*");
		print("Maximum Number of family along simulation: " + maxNumRelatives);
		print("Average Number of family along simulation: "
				+ format(totalNumRelatives / allEverSimulatedAgents.size()));
		print("*");

		try {

			File f = new File(cvsFileStatsGeneric);
			CsvWriter writer = null;
			String[] s = new String[8];
			// String key = "";
			// Object value = null;

			writer = writeHeaderGeneric(f, s);

			// write the Gen values:
			Calendar c = Calendar.getInstance();
			writer.write(c.getTime() + "");
			writer.write(config.toString());

			/*
			 * File f_mau = new File("mauricio.csv"); FileWriter file_mau =
			 * null; CsvWriter writer_mau = null; String[] s2 = null; if
			 * (!f_mau.exists()) { file_mau = new FileWriter(f_mau, true);
			 * writer_mau = new CsvWriter(file_mau, ';'); s2 = new String[2];
			 * s2[0] = "Sim"; s2[1] = "Fr"; writer_mau.writeRecord(s); } else {
			 * file_mau = new FileWriter(f_mau, true); writer_mau = new
			 * CsvWriter(file_mau, ';'); }
			 */

			// Mean of the similarities of the couples (if any, in any moment):
			double meanSimsCouple = 0;
			// Mean of the friendships (from her point of view) of the couples
			// (if
			// any, in any moment):
			double meanFrsCouple = 0;
			int numPeopleWithCouples = 0;

			// write the table values:
			// Calendar cc = Calendar.getInstance();

			for (DemographicIndiv ind : allEverSimulatedAgents) {
				if (ind.grownUp() && ind.getSimWithLastCoupleIfAny() > 0) {
					numPeopleWithCouples++;
					meanSimsCouple = meanSimsCouple
							+ ind.getSimWithLastCoupleIfAny();
					meanFrsCouple = meanFrsCouple
							+ ind.getFrWithLastCoupleIfAny();

					// Mauricio's HAIS08:
					// writer_mau.write(config.toString());
					// s2[0] = "" + ind.getSimWithLastCoupleIfAny();
					// s2[1] = "" + ind.getFrWithLastCoupleIfAny();
					// writer_mau.writeRecord(s2);
				}
			}
			// Mauricio's HAIS08:
			// writer_mau.close();

			if (numPeopleWithCouples > 0) {
				meanSimsCouple = meanSimsCouple / (double) numPeopleWithCouples;
				meanFrsCouple = meanFrsCouple / (double) numPeopleWithCouples;
			}

			s[0] = "" + (allEverSimulatedAgents.size() - numInitialNumberAgs);
			s[1] = "" + (allEverSimulatedAgents.size() - agentList.size());
			s[2] = "" + maxNumFriends;
			s[3] = "" + format(totalNumFriends / allEverSimulatedAgents.size());
			s[4] = "" + maxNumRelatives;
			s[5] = ""
					+ format(totalNumRelatives / allEverSimulatedAgents.size());

			s[6] = "" + format(meanSimsCouple);
			s[7] = "" + format(meanFrsCouple);

			writer.writeRecord(s);
			writer.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		printValidationYears();
	}

	/**
	 * 
	 */
	private void printValidationYears() {
		print("*");
		print("**** Specific statistics for 1980 ****");
		print("*");
		printValidationYear(table1980);
		print("*");
		print("*");
		print("**** Specific statistics for 1990 ****");
		print("*");
		printValidationYear(table1990);
		print("*");
		print("*");
		print("**** Specific statistics for 1999 ****");
		print("*");
		printValidationYear(table1999);
	}

	/**
	 * @param f
	 * @param s
	 * @return
	 * @throws IOException
	 */
	private CsvWriter writeHeaderGeneric(File f, String[] s) throws IOException {
		FileWriter file;
		CsvWriter writer;
		// write header row if doesn't exist (name of the keys):
		if (!f.exists()) {
			file = new FileWriter(f, true);
			writer = new CsvWriter(file, ';');

			writer.write("Time");
			writer.write("Config");

			s[0] = "Born";
			s[1] = "Deaths";
			s[2] = "maxNumFriends";
			s[3] = "AverageFriends";
			s[4] = "maxNumFamily";
			s[5] = "AverageFamily";
			s[6] = "MEAN SIMILARITY COUPLE";
			s[7] = "MEAN FRIENDSHIP COUPLE";

			writer.writeRecord(s);
		} else {
			file = new FileWriter(f, true);
			writer = new CsvWriter(file, ';');
		}
		return writer;
	}

	public void printValidationYear(Hashtable<String, Object> table) {

		try {
			Set<String> setKeys = table.keySet();
			ArrayList<String> listKeys = new ArrayList<String>(setKeys);
			Collections.sort(listKeys);

			// Write in CSV all the table:

			String name = chooseFileName(table);

			File f = new File(name);
			CsvWriter writer = null;
			String[] s = new String[listKeys.size()];

			writer = writeHeader(listKeys, f, s);
			writeTableValues(table, listKeys, writer, s);
			printValues(table, listKeys);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param table
	 * @return
	 */
	private String chooseFileName(Hashtable<String, Object> table) {
		String name = "";
		if (table.equals(table1980))
			name = cvsFileStats1980;
		else if (table.equals(table1990))
			name = cvsFileStats1990;
		else if (table.equals(table1999))
			name = cvsFileStats1999;
		return name;
	}

	/**
	 * Print on screen the statistical values
	 * 
	 * @param table
	 * @param listKeys
	 */
	private void printValues(Hashtable<String, Object> table,
			ArrayList<String> listKeys) {
		String key;
		Object value;
		for (int i = 0; i < listKeys.size(); i++) {
			key = listKeys.get(i);
			value = table.get(key);
			if (key.length() >= 25) {
				if (key.length() >= 40)
					print(key + ": " + value);
				else
					print(key + ": \t" + value);
			} else if (key.length() < 12)
				print(key + ": \t\t\t" + value);
			else
				print(key + ": \t\t" + value);

			if ((i + 1) % 10 == 0)
				print("");
		}
	}

	/**
	 * @param table
	 * @param listKeys
	 * @param writer
	 * @param s
	 * @throws IOException
	 */
	private void writeTableValues(Hashtable<String, Object> table,
			ArrayList<String> listKeys, CsvWriter writer, String[] s)
			throws IOException {
		String key;
		Object value;
		// write the table values:
		Calendar c = Calendar.getInstance();

		writer.write(c.getTime() + "");
		writer.write(config.toString());
		for (int i = 0; i < listKeys.size(); i++) {
			key = listKeys.get(i);
			value = table.get(key);
			s[i] = value.toString();
		}
		writer.writeRecord(s);
		writer.close();
	}

	/**
	 * @param listKeys
	 * @param f
	 * @param s
	 * @return
	 * @throws IOException
	 */
	private CsvWriter writeHeader(ArrayList<String> listKeys, File f, String[] s)
			throws IOException {
		FileWriter file;
		CsvWriter writer;
		String key;
		// write header row if doesn't exist (name of the keys):
		if (!f.exists()) {
			file = new FileWriter(f, true);
			writer = new CsvWriter(file, ';');

			writer.write("Time");
			writer.write("Config");

			for (int i = 0; i < listKeys.size(); i++) {
				key = listKeys.get(i);
				s[i] = key.toString();
			}
			writer.writeRecord(s);
			// writer.close();
		} else {
			file = new FileWriter(f, true);
			writer = new CsvWriter(file, ';');
		}
		return writer;
	}

	public void print(Object str) {
		System.out.println(str);
	}

	public double format(double num) {
		String s = df.format(num).toString();
		String newString = s.replace(',', '.');
		return Double.parseDouble(newString);
	}

	public void update(ArrayList<DemographicIndiv> agentListNew) {
		setAgentList(agentListNew);
		updatingCountValid();
	}

	public void recordInitialYear() {
		recordValYear(table1980);
	}

	public void record1stValYear() {
		recordValYear(table1990);
	}

	public void record2ndValYear() {
		recordValYear(table1999);
	}

	/**
	 * Records all the information of actual agents (in the moment called) in
	 * the given table
	 * 
	 * @param table
	 */
	public void recordValYear(Hashtable<String, Object> table) {
		double totalFemaleAgentsNow = 0;
		int numChildrenNow = 0;
		int numAdultsNow = 0;
		int numOldsNow = 0;
		double meanChurchAttendance = 0.0;
		int numMothersWithChildren = 0;
		int numPeopleWithCouples = 0;
		double meanNumChildren = 0.0;
		double numChildren = 0.0;
		double meanAge = 0;

		// Mean of the similarities of the couples (if any, in any moment):
		double meanSimsCouple = 0;
		// Mean of the friendships (from her point of view) of the couples (if
		// any, in any moment):
		double meanFrsCouple = 0;
		for (DemographicIndiv ind : agentList) {
			if (ind.child())
				numChildrenNow++;
			else if (ind.adult())
				numAdultsNow++;
			else if (ind.old())
				numOldsNow++;

			// filtro!!
			if (ind.grownUp()) {

				if (ind.female())
					totalFemaleAgentsNow++;
				if (ind.isHadChildren()) {
					numMothersWithChildren++;
					numChildren = numChildren + ind.getNumChildren();
				}

				if (ind.getSimWithLastCoupleIfAny() > 0) {
					numPeopleWithCouples++;
					meanSimsCouple = meanSimsCouple
							+ ind.getSimWithLastCoupleIfAny();
					meanFrsCouple = meanFrsCouple
							+ ind.getFrWithLastCoupleIfAny();
				}

				meanAge = meanAge + ind.getAge().getValue();

				meanChurchAttendance = meanChurchAttendance
						+ ind.getChurchAtt().getAttendance();
			}
		}
		meanChurchAttendance = meanChurchAttendance / countGrownUp;
		if (numMothersWithChildren > 0)
			meanNumChildren = numChildren / (1.0 * numMothersWithChildren);
		meanAge = meanAge / (double) countGrownUp;
		// the mean only between the ones that had couple:
		if (numPeopleWithCouples > 0) {
			meanSimsCouple = meanSimsCouple / (double) numPeopleWithCouples;
			meanFrsCouple = meanFrsCouple / (double) numPeopleWithCouples;
		}
		double numAgs = (double) agentList.size();
		saveStatValidationYear("01 numAgents", numAgs, table);

		saveStatValidationYear("02 totalFemaleAgents", totalFemaleAgentsNow,
				table);
		saveStatValidationYear("03 totalMaleAgents", countGrownUp
				- totalFemaleAgentsNow, table);
		saveStatValidationYear("04 % FemaleAgents", format(totalFemaleAgentsNow
				/ countGrownUp * 100), table);
		saveStatValidationYear("05 % MaleAgents",
				format((countGrownUp - totalFemaleAgentsNow) / countGrownUp
						* 100), table);

		saveStatValidationYear("06 numChildren", numChildrenNow, table);
		saveStatValidationYear("07 numActivePopulation", numAdultsNow, table);
		saveStatValidationYear("08 numOlds", numOldsNow, table);
		saveStatValidationYear("09 % Olds",
				format((numOldsNow / (numAgs - numChildrenNow)) * 100), table);

		int i = 10;
		saveStatValidationYear(i + " meanAge", format(meanAge), table);

		i++;
		saveStatValidationYear(i + " MEAN SIMILARITY COUPLE",
				format(meanSimsCouple), table);

		i++;
		saveStatValidationYear(i + " MEAN FRIENDSHIP (HERS) COUPLE",
				format(meanFrsCouple), table);

		i++;
		saveStatValidationYear(i + " numAliveAdultsMarried",
				numPeopleWithCouples, table);
		i++;
		saveStatValidationYear(i + " % AliveAdultsMarried",
				format(numPeopleWithCouples / countGrownUp * 100), table);
		i++;
		saveStatValidationYear(i + " % AliveAdultsSingle",
				format(100 - (numPeopleWithCouples / countGrownUp * 100)),
				table);
		i++;
		saveStatValidationYear(i + " numCouples now",
				numPeopleWithCouples / 2.0, table);
		i++;
		saveStatValidationYear(
				i + " numMothersHadChildren (during simulation)",
				numMothersWithChildren, table);
		i++;
		saveStatValidationYear(i
				+ " numChildrenHadByAliveMothers (in simulation)",
				(numChildren), table);
		i++;
		saveStatValidationYear(i + " meanNumChildrenPerMother (in simulation)",
				format(meanNumChildren), table);

		double id = meanIdeology();
		i++;
		saveStatValidationYear(i + " meanIdeology", format(id), table);
		i++;
		saveStatValidationYear(i + " meanIdeologyLeft",
				format(meanIdeologyLeft()), table);
		i++;
		saveStatValidationYear(i + " % IdeologyLeft", format(100
				* counterGeneric / countGrownUp), table);
		i++;
		saveStatValidationYear(i + " meanIdeologyRight",
				format(meanIdeologyRight()), table);
		i++;
		saveStatValidationYear(i + " % IdeologyRight", format(100
				* counterGeneric / countGrownUp), table);
		i++;
		saveStatValidationYear(i + " meanIdeologyCentre",
				format(meanIdeologyCentre()), table);
		i++;
		saveStatValidationYear(i + " % IdeologyCentre", format(100
				* counterGeneric / countGrownUp), table);
		i++;
		saveStatValidationYear(i + " meanIdeologyNoAnswer",
				format(meanIdeologyNoAnswer()), table);
		i++;
		saveStatValidationYear(i + " % IdeologyNoAnswer", format(100
				* counterGeneric / countGrownUp), table);

		double ed = meanCategorizedEducation();
		i++;
		saveStatValidationYear(i + " meanEducation", format(ed), table);
		double ec = meanEconomy();
		i++;
		saveStatValidationYear(i + " meanEconomy (StatusR)", format(ec), table);

		double rlEc = religClassif("EC");
		i++;
		saveStatValidationYear(i + " % religClassifECL", format(rlEc), table);
		double rlLow = religClassif("LOW");
		i++;
		saveStatValidationYear(i + " % religClassifLOW", format(rlLow), table);
		double rlAlt = religClassif("ALT");
		i++;
		saveStatValidationYear(i + " % religClassifALT", format(rlAlt), table);
		double rlNon = religClassif("NON");
		i++;
		saveStatValidationYear(i + " % religClassifNON", format(rlNon), table);

		i++;
		saveStatValidationYear(i + " meanAbortToleranceNo99",
				format(meanAbortToleranceNo99()), table);
		i++;
		saveStatValidationYear(i + " meanDivorceToleranceNo99",
				format(meanDivorToleranceNo99()), table);
		i++;
		saveStatValidationYear(i + " meanEuthaToleranceNo99",
				format(meanEuthaToleranceNo99()), table);
		i++;
		saveStatValidationYear(i + " meanHomosexToleranceNo99",
				format(meanHomosexToleranceNo99()), table);
		i++;
		saveStatValidationYear(i + " meanProsToleranceNo99",
				format(meanProsToleranceNo99()), table);
		i++;
		saveStatValidationYear(i + " meanSuicToleranceNo99",
				format(meanSuiciToleranceNo99()), table);

		i++;
		saveStatValidationYear(i + " meanChurchAttendance",
				format(meanChurchAttendance), table);

		i++;
		// FIXME: Muy cutre. Puede que en el futuro esto deje de funcionar
		double totalInitAgs = 2303;
		if (numAgs >= 3000)
			totalInitAgs = 3019;
		saveStatValidationYear(i + " populationGrowth",
				format(((numAgs / totalInitAgs) * 100) - 100), table);
	}

	public void generateDMOutput() {
		try {

			File f = new File(cvsFileDMOutput);
			CsvWriter writer = null;

			
			
			// 10????
			String[] s = new String[10];

			
			
			
			
			

			writer = writeHeaderDM(f, s);

			// write the Gen values:
			/*Calendar c = Calendar.getInstance();
			writer.write(c.getTime() + "");
			writer.write(config.toString());*/

		
			for (DemographicIndiv ind : agentList) {
				
				//To be able to compare with EVS, only the >18 can be counted:
				if (ind.grownUp()){
					
					int i= 0;
					s[i] = "" + ind.getConfChurch();		
					
					i++;
					s[i] = "" + ind.getChurchAtt();
					
					i++;
					s[i] = ""+ ind.getReligPerson();
					
					i++;
					s[i] = "" + ind.getSex();
					
					i++;
					s[i] = "" + ind.getAge();
					
					i++;
					s[i] = "" + ind.getEducation().getAgeEndEduc();
					
					i++;
					s[i] = "" + "-";
					
					i++;
					s[i] = "" + ind.getReligion().getReligiosity();
					
					i++;
					s[i] = "" + ind.getEconomy();
					
					i++;
					s[i] = "" + ind.getIdeology();
					
					writer.writeRecord(s);
				}
			
			}
			
			
			/*
			
			// Mean of the similarities of the couples (if any, in any moment):
			double meanSimsCouple = 0;
			// Mean of the friendships (from her point of view) of the couples
			// (if
			// any, in any moment):
			double meanFrsCouple = 0;
			int numPeopleWithCouples = 0;

			// write the table values:
			// Calendar cc = Calendar.getInstance();

			for (DemographicIndiv ind : allEverSimulatedAgents) {
				if (ind.grownUp() && ind.getSimWithLastCoupleIfAny() > 0) {
					numPeopleWithCouples++;
					meanSimsCouple = meanSimsCouple
							+ ind.getSimWithLastCoupleIfAny();
					meanFrsCouple = meanFrsCouple
							+ ind.getFrWithLastCoupleIfAny();

					// Mauricio's HAIS08:
					// writer_mau.write(config.toString());
					// s2[0] = "" + ind.getSimWithLastCoupleIfAny();
					// s2[1] = "" + ind.getFrWithLastCoupleIfAny();
					// writer_mau.writeRecord(s2);
				}
			}
			// Mauricio's HAIS08:
			// writer_mau.close();

			if (numPeopleWithCouples > 0) {
				meanSimsCouple = meanSimsCouple / (double) numPeopleWithCouples;
				meanFrsCouple = meanFrsCouple / (double) numPeopleWithCouples;
			}

			s[0] = "" + (allEverSimulatedAgents.size() - numInitialNumberAgs);
			s[1] = "" + (allEverSimulatedAgents.size() - agentList.size());
			s[2] = "" + maxNumFriends;
			s[3] = "" + format(totalNumFriends / allEverSimulatedAgents.size());
			s[4] = "" + maxNumRelatives;
			s[5] = ""
					+ format(totalNumRelatives / allEverSimulatedAgents.size());

			s[6] = "" + format(meanSimsCouple);
			s[7] = "" + format(meanFrsCouple);*/

//			writer.writeRecord(s);
			writer.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private CsvWriter writeHeaderDM(File f, String[] s) throws IOException {
		FileWriter file;
		CsvWriter writer;
		// write header row (overwriting):
		//if (!f.exists()) {
			file = new FileWriter(f, false);
			writer = new CsvWriter(file, ';');

//			writer.write("Time");
//			writer.write("Config");

			s[0] = "Conf_Ch";
			s[1] = "Church_Att";
			s[2] = "Relig_pers";
			s[3] = "Gender";
			s[4] = "Age";
			s[5] = "Age_Educ";
			s[6] = "Marital_St";
			s[7] = "TIPORELI";
			s[8] = "STATUSR";
			s[9] = "Ideology";

			writer.writeRecord(s);
//		} else {
//			file = new FileWriter(f, true);
//			writer = new CsvWriter(file, ';');
//		}
		return writer;
	}
}
