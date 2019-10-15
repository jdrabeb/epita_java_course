package fr.epita.quiz.services;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public abstract class DAO<T> {

	@PersistenceContext
	EntityManager em;
	
	public void create(T t) {
		em.persist(t);
		em.close();
	}

	public T getById(Serializable id, Class<T> clazz) {

		return em.find(clazz, id);
	}

	public void update(T t) {
		em.merge(t);
		em.flush();
		em.close();
	}

	public void delete(T t) {
		em.remove(t);
		em.close();
	}
	
	public List<T> search(T criteria){
		
		Query query = em.createQuery(getQueryString());
		Map<String, Object> parameters = new LinkedHashMap<String, Object>();
		fillParametersMap(parameters,criteria);
		
		parameters.forEach((k,v) -> query.setParameter(k,v));
		
		return query.getResultList();
	}

	protected abstract String getQueryString();
	protected abstract void fillParametersMap(Map<String,Object> map, T t);
	
	
}