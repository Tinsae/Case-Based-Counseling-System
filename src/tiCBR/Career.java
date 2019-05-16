package tiCBR;

public class Career {

	private String SOCTitle;
	private String ISCOTitle;
	private String Description;

	public String toString() {
		return " [ " + SOCTitle + " ] " + " [ " + ISCOTitle + " ] ";

	}

	public Career(String st, String it, String desc) {
		SOCTitle = st;
		ISCOTitle = it;
		Description = desc;

	}

	public String getSOCTitle() {
		return SOCTitle;
	}

	public void setSOCTitle(String sOCTitle) {
		SOCTitle = sOCTitle;
	}

	public String getISCOTitle() {
		return ISCOTitle;
	}

	public void setISCOTitle(String iSCOTitle) {
		ISCOTitle = iSCOTitle;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

}
