package tiCBR;

import jcolibri.cbrcore.Attribute;
//import jcolibri.datatypes.Instance;
import jcolibri.datatypes.Instance;

//Bean that stores the description of the case.
public class StudCaseDescription implements jcolibri.cbrcore.CaseComponent {
	
	String caseId;
	String Stream;
	Integer Duration;
	Instance BackgroundInterest;
	Instance FamilyInterest;
	Integer EntranceResult;
	Instance EntranceTop;
	Instance PastExperience;
	Instance Preparatory1st;
	Instance Preparatory2nd;
	Instance SpecialSkill;
	Integer Realistic;
	Integer Investigative;
	Integer Artistic;
	Integer Social;
	Integer Enterprising;
	Integer Conventional;
	
	
	
	
	
	String FHS;

	public String getFHS() {
		return FHS;
	}

	public void setFHS(String fHS) {
		FHS = fHS;
	}

	public String getStream() {
		return Stream;
	}

	public void setStream(String stream) {
		Stream = stream;
	}

	public String toString() {
		return "(" + caseId + ";" + Stream + ";" + BackgroundInterest + ";"
				+ Duration + ";" + EntranceResult + ";" + EntranceTop + ";"
				+ FamilyInterest + ";" + PastExperience + ";" + Preparatory1st
				+ ";" + Preparatory2nd + ";" + SpecialSkill + ";" + Realistic
				+ ";" + Investigative + ";" + Artistic + ";" + Social + ";"
				+ Enterprising + ";" + Conventional + ";" + ")";
	}

	public String getCaseId() {
		return caseId;
	}

	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}

	public Instance getBackgroundInterest() {
		return BackgroundInterest;
	}

	public void setBackgroundInterest(Instance backgroundInterest) {
		BackgroundInterest = backgroundInterest;
	}

	public Integer getDuration() {
		return Duration;
	}

	public void setDuration(Integer duration) {
		Duration = duration;
	}

	public Integer getEntranceResult() {
		return EntranceResult;
	}

	public void setEntranceResult(Integer entranceResult) {
		EntranceResult = entranceResult;
	}

	public Instance getEntranceTop() {
		return EntranceTop;
	}

	public void setEntranceTop(Instance entranceTop) {
		EntranceTop = entranceTop;
	}

	public Instance getFamilyInterest() {
		return FamilyInterest;
	}

	public void setFamilyInterest(Instance familyInterest) {
		FamilyInterest = familyInterest;
	}

	public Instance getPastExperience() {
		return PastExperience;
	}

	public void setPastExperience(Instance pastExperience) {
		PastExperience = pastExperience;
	}

	public Instance getPreparatory1st() {
		return Preparatory1st;
	}

	public void setPreparatory1st(Instance preparatory1st) {
		Preparatory1st = preparatory1st;
	}

	public Instance getPreparatory2nd() {
		return Preparatory2nd;
	}

	public void setPreparatory2nd(Instance preparatory2nd) {
		Preparatory2nd = preparatory2nd;
	}

	public Instance getSpecialSkill() {
		return SpecialSkill;
	}

	public void setSpecialSkill(Instance specialSkill) {
		SpecialSkill = specialSkill;
	}

	public Integer getRealistic() {
		return Realistic;
	}

	public void setRealistic(Integer realistic) {
		Realistic = realistic;
	}

	public Integer getInvestigative() {
		return Investigative;
	}

	public void setInvestigative(Integer investigative) {
		Investigative = investigative;
	}

	public Integer getArtistic() {
		return Artistic;
	}

	public void setArtistic(Integer artistic) {
		Artistic = artistic;
	}

	public Integer getSocial() {
		return Social;
	}

	public void setSocial(Integer social) {
		Social = social;
	}

	public Integer getEnterprising() {
		return Enterprising;
	}

	public void setEnterprising(Integer enterprising) {
		Enterprising = enterprising;
	}

	public Integer getConventional() {
		return Conventional;
	}

	public void setConventional(Integer conventional) {
		Conventional = conventional;
	}

	public Attribute getIdAttribute() {
		return new Attribute("caseId", this.getClass());
	}

}
