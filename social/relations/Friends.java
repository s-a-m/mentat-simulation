/**
 * 
 */
package social.relations;

import java.awt.Color;
import java.util.ArrayList;

import social.indiv.DemographicIndiv;

/**
 * @author Samer
 * 
 */
public class Friends extends Group {

	//random friends around
	//default: 0.0001; 
	private double chance_friends;

	//time to know all the people that passes the threshold similar_limit
	//default: 0.4; 0.001
	private double chance_friends_if_similars; //pero fuzzy_similar_limit est‡ en 1.1

	/**
	 * Used in the fuzzy version
	 */
	// default: 0.01
	private static final Double DEFAULT_GRADE = 0.01;

	/**
	 * It has an array of similarity grades
	 */
	private ArrayList<Double> grades;

	/**
	 * @param ind
	 */
	public Friends(DemographicIndiv ind, double ch_fr, double ch_fr_sim) {
		super(ind);
		grades = new ArrayList<Double>();
		grades.add(1.0);
		chance_friends= ch_fr;
		chance_friends_if_similars= ch_fr_sim;
	}

	@Override
	public Color color() {
		// Color yellow:
		return new Color(255, 255, 224);
	}

	@Override
	public String type() {
		return "Friend";
	}

	@Override
	public String toString() {
		return "FRIENDS:\n" + getGroup().toString();
	}

	public void addIndiv(DemographicIndiv ind) {
		super.addIndiv(ind);
		grades.add(DEFAULT_GRADE);
	}

	public void addIndiv(DemographicIndiv ind, double grade) {
		super.addIndiv(ind);
		grades.add(grade);
	}

	public boolean removeIndiv(DemographicIndiv ind) {
		int index = getGroup().indexOf(ind);
		boolean removed = super.removeIndiv(ind);
		if (removed)
			grades.remove(index);
		return removed;
	}

	public double getGradeOf(int index) {
		return grades.get(index);
	}

	public double getGradeOf(DemographicIndiv ind) throws Exception{
		int index = getGroup().indexOf(ind);
		double gr= grades.get(index);
		if(gr>=0 && gr<=1)
			return gr;
		else
			throw new Exception("grado del individuo siguiente fuera de rango:\n"+ind);
		
	}

	public void setGradeOf(int index, double grade) {
		grades.set(index, grade);
	}

	public void setGradeOf(DemographicIndiv ind, double grade) {
		int index = getGroup().indexOf(ind);
		grades.set(index, grade);
	}

	public boolean becomeFriends(boolean similar) {
		double r = Math.random();
		if (similar) {
			return r < chance_friends_if_similars;
		} else {
			return r < chance_friends;
		}
	}

	public Color getColorFromGrade(DemographicIndiv ind) throws Exception {
		int r = 255;
		int g = 255;
		int b = 224;
		double grade = getGradeOf(ind);
		int gradeRounded = (int) Math.round(grade * 10);
		// gradeRounded is in [0,10]
		// each time that there is a loop,turns darker yellow
		for (int i = 0; i < gradeRounded; i++) {
			b = b - 25;
		}
		if (b < 0)
			b = 0;
		if(g<0 || r<0 || b>255 || r>255 || g>255){
			System.out.println(r+":"+g+":"+b);
			return Color.yellow;
		}
		return new Color(r, g, b);
	}

	/*
	 * public void updateColor(){
	 * 
	 * for(int i=0; i< grade; i++){
	 *  }
	 * 
	 * 
	 * color= new Color(r,g,b); }
	 */

}
