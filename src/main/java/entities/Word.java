package entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "words")
public class Word {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(unique = true, nullable = false, updatable = true)
	private String word;

	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name="Word_id")
	private List<Meaning> meanings = new ArrayList<Meaning>();

	public Word() {
		/* Hibernate-nek */
	}

	public Word(String word, List<Meaning> meanings) {
		this.word = word;
		this.meanings = meanings;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<Meaning> getMeanings() {
		return meanings;
	}

	public void setMeanings(List<Meaning> meanings) {
		this.meanings = meanings;
	}

	public void addMeaning(Meaning meaning) {
		meanings.add(meaning);
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(id + " : " + word + " - ");
		for (Meaning s : meanings) {
			sb.append(s + ", ");
		}
		sb.replace(sb.length() - 2, sb.length(), "");
		return sb.toString();
	}
}
