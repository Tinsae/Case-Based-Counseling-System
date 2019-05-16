package tiCBR;

import jcolibri.cbrcore.Attribute;

/**
 * Bean that stores the solution of the case (trip)
 * 
 * @author Juan A. Recio-Garcia
 * @version 1.0
 */
public class StudCaseSolution implements jcolibri.cbrcore.CaseComponent {

	String id;
	String FieldofStudy;

	public String toString() {
		return "(" + id + ";" + FieldofStudy + ")";
	}

	public Attribute getIdAttribute() {

		return new Attribute("id", this.getClass());
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFieldofStudy() {
		return FieldofStudy;
	}

	public void setFieldofStudy(String fieldofStudy) {
		FieldofStudy = fieldofStudy;
	}

}
