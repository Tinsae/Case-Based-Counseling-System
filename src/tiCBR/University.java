package tiCBR;

public class University implements Comparable<University> {

	private String abbr;
	private String name;
	private String band;
	private Integer capacity;

	public University(String abbr, String name, String band, Integer capacity) {
		this.abbr = abbr;
		this.name = name;
		this.band = band;
		this.capacity = capacity;
	}

	public String getAbbr() {
		return abbr;
	}

	public void setAbbr(String abbr) {
		this.abbr = abbr;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBand() {
		return band;
	}

	public void setBand(String band) {
		this.band = band;
	}

	public Integer getCapacity() {
		return capacity;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}

	public int compareTo(University theUniv) {

		return (new Integer(this.getCapacity())
				.compareTo(theUniv.getCapacity()));
	}

}
