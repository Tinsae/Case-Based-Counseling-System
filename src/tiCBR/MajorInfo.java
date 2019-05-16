package tiCBR;

import java.util.ArrayList;
import jcolibri.cbrcore.CBRCase;

public class MajorInfo implements Comparable<MajorInfo> {

	private String majorTitle;
	private String description;
	private String band;
	private Double evaluation;
	private Double noOfCases;
	private Double totalCases;
	private ArrayList<CBRCase> matchCases;
	private Integer noOfCareers;
	private ArrayList<Career> matchCareers;
	private Double totalScore;
	private ArrayList<University> matchUnivs;

	public MajorInfo(String title, String descp, String _band,Double eval, Double mcasesCount, Double tcases,
			ArrayList<CBRCase> matchCas, Integer noC, ArrayList<Career> matchCar,
			Double totS, ArrayList<University> matUniv) {
		majorTitle = title;
		description = descp;
		band=_band;
		evaluation = eval;
		noOfCases = mcasesCount;
		totalCases=tcases;
		matchCases = matchCas;
		noOfCareers = noC;
		matchCareers = matchCar;
		totalScore = totS;
		matchUnivs = matUniv;
	}

	public ArrayList<University> getMatchUnivs() {
		return matchUnivs;
	}

	public void setMatchUnivs(ArrayList<University> matchUnivs) {
		this.matchUnivs = matchUnivs;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getNoOfCareers() {
		return noOfCareers;
	}

	public void setNoOfCareers(Integer noOfCareers) {
		this.noOfCareers = noOfCareers;
	}

	public ArrayList<Career> getMatchCareers() {
		return matchCareers;
	}

	public void setMatchCareers(ArrayList<Career> matchCareers) {
		this.matchCareers = matchCareers;
	}

	public String toString() {
		return null;
		/*
		 * Iterator it = matchCases.listIterator(); Iterator it2 =
		 * matchCareers.listIterator();
		 * 
		 * String theCases = ""; String theCareers = "";
		 * 
		 * // this loops are useless for the GUI of adapt ranking while
		 * (it.hasNext()) { theCases = theCases + "{" + it.next().toString() +
		 * "}";
		 * 
		 * }
		 * 
		 * while (it.hasNext()) { theCases = theCareers + "{" +
		 * it2.next().toString() + "}";
		 * 
		 * }
		 * 
		 * return "[" + majorTitle + "]" + "[" + evaluation + "]" + "[" +
		 * noOfCases + "]" + theCases + "[" + noOfCareers + "]" + theCareers +
		 * "[" + totalScore + "]";
		 */
	}

	public ArrayList<CBRCase> getMatchCases() {
		return matchCases;
	}

	public void setMatchCases(ArrayList<CBRCase> matchCases) {
		this.matchCases = matchCases;
	}

	public Double getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(Double totalScore) {
		this.totalScore = totalScore;
	}

	public String getMajorTitle() {
		return majorTitle;
	}

	public void setMajorTitle(String majorTitle) {
		this.majorTitle = majorTitle;
	}

	public Double getEvaluation() {
		return evaluation;
	}

	public void setEvaluation(Double evaluation) {
		this.evaluation = evaluation;
	}

	public Double getNoOfCases() {
		return noOfCases;
	}

	
	public void setNoOfCases(Double noOfCases) {
		this.noOfCases = noOfCases;
	}

	public Integer getNoOfOccup() {
		return noOfCareers;
	}

	public void setNoOfOccup(Integer noOfOccup) {
		this.noOfCareers = noOfOccup;
	}

	public int compareTo(MajorInfo theMaj) {

		return (new Double(this.getTotalScore()).compareTo(theMaj
				.getTotalScore()));
	}

	public Double getTotalCases() {
		return totalCases;
	}

	public void setTotalCases(Double totalCases) {
		this.totalCases = totalCases;
	}

	public String getBand() {
		return band;
	}

	public void setBand(String band) {
		this.band = band;
	}

}
