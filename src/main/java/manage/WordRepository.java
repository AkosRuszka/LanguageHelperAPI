package manage;

import entities.Meaning;
import entities.Word;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.query.Query;

public class WordRepository {
	private static SessionFactory factory;

	public WordRepository() {
		factory = new Configuration().configure().addAnnotatedClass(Word.class).buildSessionFactory();
	}

	public Long addWord(String word, List<Meaning> meaning) throws ConstraintViolationException {

		Session session = factory.openSession();
		Transaction tr = null;

		Long wordID = null;

		try {

			tr = session.beginTransaction();

			Word wo = new Word(word, meaning);

			meaning.forEach((d) -> session.save(d));

			wordID = (Long) session.save(wo);
		
			tr.commit();
		
		} catch (HibernateException e) {

			if (tr != null) {
				if (tr != null) {
					try {
						tr.rollback();
					} catch (HibernateException k) {
						/* semmi */ }
				}
			}

		} finally {

			if (session != null) {
				try {
					session.close();
				} catch (HibernateException k) {
					/* semmit */ }
			}

		}
		
		return wordID;
	}

	@SuppressWarnings("unchecked")
	public List<Word> getWords() {

		Session session = null;
		Transaction tr = null;

		String query = "from Word";
		List<Word> list = null;

		try {
			session = factory.openSession();
			tr = session.beginTransaction();

			list = session.createQuery(query).getResultList();

			tr.commit();

		} catch (HibernateException e) {

			if (tr != null) {
				if (tr != null) {
					try {
						tr.rollback();
					} catch (HibernateException k) {
						/* semmi */ }
				}
			}

		} finally {

			if (session != null) {
				try {
					session.close();
				} catch (HibernateException k) {
					/* semmit */ }
			}

		}
		
		return list;
	}

	/**
	 * Szó lekérdezése az adatbázisból
	 * 
	 * @param _word
	 *            A lekérdezni kívánt idegen szó
	 * @return ID : ha a szó megtalálható az adatbázisban <br>
	 *         null : ha a szó nem található meg az adatbázisban
	 */
	public Word getWord(String _word) {

		Session session = null;
		Transaction tr = null;

		String queryString = "select w from Word w where word = :se";
		Word word = null;

		try {

			session = factory.openSession();
			tr = session.beginTransaction();

			@SuppressWarnings("rawtypes")
			Query query = session.createQuery(queryString);
			query.setParameter("se", _word);

			word = (Word) query.getSingleResult();

			tr.commit();

		} catch (NoResultException e) {
			word = null;
		} catch (HibernateException e) {

			if (tr != null) {
				try {
					tr.rollback();
				} catch (HibernateException k) {
					/* semmi */ }
			}

		} finally {

			if (session != null) {
				try {
					session.close();
				} catch (HibernateException k) {
					/* semmit */ }
			}
		}

		return word;
	}

}
