package entities;

import javax.persistence.*;

@Entity
@Table(name = "SENTENCES")
public class Sentence {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(unique = true, nullable = false, updatable = true)
	private String sentence;

	@Column(nullable = false, updatable = true)
	private String meaning;

	public Sentence() {
		/* Hibernate-nek */ }

	public Sentence(String exSentence, String meaning) {
		this.sentence = exSentence;
		this.meaning = meaning;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSentence() {
		return sentence;
	}

	public void setSentence(String exSentence) {
		this.sentence = exSentence;
	}

	public String getMeaning() {
		return meaning;
	}

	public void setMeaning(String meaning) {
		this.meaning = meaning;
	}

	@Override
	public String toString() {
		return id + " : " + sentence + " - " + meaning;
	}
}
