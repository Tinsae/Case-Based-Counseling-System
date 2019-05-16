package tiCBR;

public class Major {
	private String majorTitle;
	private String Band;
	private String Description;

	

	public Major(String mt, String bd, String desc) {
		majorTitle = mt;
		Band = bd;
		Description=desc;
		
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}


	public String getMajorTitle() {
		return majorTitle;
	}

	public void setMajorTitle(String majorTitle) {
		this.majorTitle = majorTitle;
	}

	public String getBand() {
		return Band;
	}

	public void setBand(String band) {
		Band = band;
	}

	

}
