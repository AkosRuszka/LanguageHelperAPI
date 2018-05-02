package manage;

import entities.Sentence;

import org.hibernate.HibernateError;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

@NamedQueries({ @NamedQuery(name = SentenceRepository.QUERY_GET_SENTENCES, query = "from Sentence"),
		@NamedQuery(name = SentenceRepository.QUERY_GET_SENTENCE_FOR_ENTITYMANAGER, query = "select s from Sentence s where sentence = :se"),
		@NamedQuery(name = SentenceRepository.QUERY_GET_SENTENCE_FOR_SESSION, query = "from Sentence where sentence = :se") })

public class SentenceRepository {

	protected static final String QUERY_GET_SENTENCES = "GetSentences";
	protected static final String QUERY_GET_SENTENCE_FOR_ENTITYMANAGER = "get_sentence_by_sentence_for_entityManager";
	protected static final String QUERY_GET_SENTENCE_FOR_SESSION = "get_sentence_by_sentence_for_session";

	private static SessionFactory factory;
	private static EntityManager entityManager;

	public SentenceRepository() {
		factory = new Configuration().configure().addAnnotatedClass(Sentence.class).buildSessionFactory();
		entityManager = factory.createEntityManager();
	}

	public Integer addSentence(String sentence, String meaning) {
		Session session = factory.openSession();
		Integer sentenceID = null;

		try {
			Sentence es = new Sentence(sentence, meaning);

			sentenceID = (Integer) session.save(es);

		} catch (HibernateError e) {
			e.printStackTrace();
		} catch (Error e) {
			// e.printStackTrace();
			// A hibernate ERROR-al jelzi ha egy unique kényszer nem teljesül, így itt ezt
			// csak elkapjuk, semmit nem csinálunk vele.
		} finally {
			session.close();
		}
		return sentenceID;
	}

	@SuppressWarnings("unchecked")
	public List<Sentence> getSentences() {

		/* Hibernate Query API */
		Session session = factory.openSession();
		String hq1 = "from Sentence";
		List<Sentence> list = new ArrayList<Sentence>();

		try {
			list = session.createQuery(hq1).getResultList();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		/* JPA NamedQuery API */
		// javax.persistence.Query q1 = entityManager
		// .createNamedQuery("get_sentences");
		// List<Sentence> list = q1.getResultList();

		return list;
	}

	public Sentence getSentence(String sentence) {
		// Session session = factory.openSession();

		// String hq1 = "from Sentence where sentence = :se";
		/* Ugyan az csak entityManager-hez. (JPA API) */
		String hq2 = "select s from Sentence s where sentence = :se";

		/* JPA Query */
		// javax.persistence.Query q2 = entityManager.createQuery(hq2);
		// q2.setParameter("se", sentence);

		/* Tipusellenőrzött JPA Query */
		TypedQuery<Sentence> q1 = entityManager.createQuery(hq2, Sentence.class);
		q1.setParameter("se", sentence);
		//
		/* Típusellenőrzött JPA NamedQuery */
		// javax.persistence.Query q3 = entityManager
		// .createNamedQuery(SentenceRepository.QUERY_GET_SENTENCE_FOR_ENTITYMANAGER)
		// .setParameter("se", sentence);

		/* Hibernate Query */
		// Query query = session.createQuery(hq1);
		// query.setParameter("se", sentence);

		Sentence result;

		try {
			// result = (Sentence)query.getSingleResult();
			result = q1.getSingleResult();
			// result = (Sentence)q3.getSingleResult();
		} catch (NoResultException e) {
			result = null;
		}

		// session.close();
		return result;
	}

}
