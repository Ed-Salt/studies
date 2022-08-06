package projectAG;

public class Noun {
	
	private String noun;
	private String translation;
	private String gender;

	public Noun(String noun, String translation, String string) {
		super();
		this.noun = noun;
		this.translation = translation;
		this.gender = string;
	}

	public String getNoun() {
		return noun;
	}

	public void setNoun(String noun) {
		this.noun = noun;
	}

	public String getTranslation() {
		return translation;
	}

	public void setTranslation(String translation) {
		this.translation = translation;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		return noun + "; " + translation + "; " + gender;
	}
	
	public String toUpdateSQL() {
		return String.format("UPDATE Noun SET "
				+ "'translation' = '%s',"
				+ "'gender' = '%s',"
				+ "WHERE 'noun' = '%s';", translation, gender, noun);
	}
}
