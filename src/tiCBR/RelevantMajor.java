package tiCBR;

public class RelevantMajor {
	private String majorTitle;

	private String majorDescription;
	private String careerTitle;
	private String careerDescription;
	private String careerFST;

	public RelevantMajor(String majorTitle, String careerTitle, String careerFST) {
		this.majorTitle = majorTitle;
		this.careerTitle = careerTitle;
		this.careerFST = careerFST;
	}

	public String getMajorDescription() {
		return majorDescription;
	}

	public void setMajorDescription(String majorDescription) {
		this.majorDescription = majorDescription;
	}

	public String getCareerDescription() {
		return careerDescription;
	}

	public void setCareerDescription(String careerDescription) {
		this.careerDescription = careerDescription;
	}

	public String getMajorTitle() {
		return majorTitle;
	}

	public void setMajorTitle(String majorTitle) {
		this.majorTitle = majorTitle;
	}

	public String getCareerTitle() {
		return careerTitle;
	}

	public void setCareerTitle(String careerTitle) {
		this.careerTitle = careerTitle;
	}

	public String getCareerFST() {
		return careerFST;
	}

	public void setCareerFST(String careerFST) {
		this.careerFST = careerFST;
	}

}