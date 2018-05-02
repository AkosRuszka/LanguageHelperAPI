import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hibernate.exception.ConstraintViolationException;

import entities.Meaning;
import entities.Word;
import manage.WordRepository;

/* 
 * Tervbe van a mondat illetve kifejezés megkülönböztetése.
 * Erre a megkülönböztetésre itt kerülne sor, ezért kell
 * ez az összefoglaló osztály, különben lehetne közvetlenül használni a Repository-kat.
 * */
public class LangHelper {

	private static WordRepository wrep = new WordRepository();
		
//	public static void main(String[] agrs) {
//
//		wrep.getWords().forEach((d)->System.out.println(d));
//		
//	}
	
	/** 
	 * Új bejegyzés hozzáadása az adatbázishoz.
	 * <p>Megfelelő formátum:
	 * <br> idegenszó - jelentés1, jelentés2, jelentés[n]
	 * <br> idegen kifejezes - hosszabb jelentes1, hosszabb jelentes2, ...<p>
	 * @param registry	A felvevenni kívánt idegen szó és jelentései a megfelelő formátumban
	 * @return 			Sikeres mentés esetén: -1.
	 * 					<br>Sikertelen mentés esetén (pl.: nem megfelelő formátum): -2.
	 * 					<br>Sikertelen mentés esetén (pl.: már el lett mente a szó) a már elmentett szó ID-ja. 
	 * */
	public static Long addNewRegistry(String registry) {
		
		String[] bon = registry.split("-");
 		
		/* Rossz a formátum */
		if(bon.length <= 1) {
			return (long) -2;
		}
		
		/* Az idegen szó vagy kifejezés formázott alakban */
		String newWord = wordFormat(bon[0]);	

		/* A jelentések listája nyers formátumba de szétdarabolva! */		
		List<String> myl = Arrays.asList(bon[1].split(","));
		
		/* Jelentések listája "szóköztelenítve" */
		List<String> ccc = new ArrayList<>();		
		myl.forEach((act)->{
			ccc.add(wordFormat(act));
		});
		
		/* Formatált jelentések Meaning-be csomagolása és listába tétele */
		List<Meaning> meaninglist = new ArrayList<>();
		ccc.forEach((act)->meaninglist.add(new Meaning(act)));
				
		/* Rossz formátum */
		if(meaninglist.isEmpty()) {
			return (long) -2;
		}
		
		Long id = null;
		Word w = null;
		try {
			
			/* Ellenőrzött mentés, ha az adatbázis már tartalmazza a szót akkor 
			 * visszatérünk a mentett szó ID-jával. 
			 * */
			if((w = wrep.getWord(newWord)) == null) {
				id = wrep.addWord(newWord,meaninglist);
			} else {
				id = w.getId();
			}

		} catch (ConstraintViolationException e) {
			/* Ha már felvett idegen szót akarunk felvenni */
		}
		
		if(id != null) {
			return (long) -1;
		} else {
			return id;
		}
	}
	
	public static List<Word> getWords() {
		return wrep.getWords();
	}
	
	public static Word getWord(String _word) {
		return wrep.getWord(_word);
	}
	
	private static String wordFormat(String word) {
		
		StringBuilder sb = new StringBuilder(word);
		/* Ha az elején space van, kitörli azt */
		if(sb.substring(0, 1).equals(" ")) {
			sb.delete(0, 1);
		}
		/* Ha az utolsó karakter egy space, kitörli azt */
		if(sb.substring(sb.length()-1, sb.length()).equals(" ")) {
			sb.delete(sb.length()-1, sb.length());
		}
		
		return sb.toString();
	}
	
}
