package br.com.genericdelivery.model.dao.generic;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

/**
 * @author Renan Johannsen de Paula
 * 
 * @param <T>
 *            entity class
 */
public class JPAGenericDAO<T extends Serializable> implements GenericDAO<T> {

	@PersistenceContext
	private EntityManager entityManager;

	private Class<T> persistentClass;

	@SuppressWarnings("unchecked")
	public JPAGenericDAO() {
		this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	public JPAGenericDAO(EntityManager entityManager) {
		this();
		this.entityManager = entityManager;
	}

	protected Class<T> getPersistentClass() {
		if (this.persistentClass == null)
			throw new IllegalStateException("PersistentClass has not been set on DAO before usage");
		return this.persistentClass;
	}

	protected void setPersistentClass(Class<T> persistentClass) {
		this.persistentClass = persistentClass;
	}

	protected EntityManager getEntityManager() {
		if (this.entityManager == null)
			throw new IllegalStateException("EntityManager has not been set on DAO before usage");
		return this.entityManager;
	}

	protected void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	private Query createQuery(String JPQL, Object... parans) {
		Query query = this.getEntityManager().createQuery(JPQL);
		int i = 0;
		for (Object object : parans) {
			query.setParameter(++i, object);
		}
		return query;
	}

	@Override
	@Transactional
	public void save(T t) {
		this.getEntityManager().persist(t);
	}

	@Override
	@Transactional
	public void update(T t) {
		this.getEntityManager().merge(t);
	}

	@Override
	@Transactional
	public void remove(Integer id) {
		this.getEntityManager().remove(this.getEntityManager().getReference(getPersistentClass(), id));
	}

	@Override
	public T findById(Serializable id) {
		return this.entityManager.find(getPersistentClass(), id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll() {
		return (List<T>) this.findByJPQL("FROM " + this.getPersistentClass().getSimpleName());
	}

	@Override
	public List<?> findByJPQL(String jpql, Object... parans) {
		return this.createQuery(jpql, parans).getResultList();
	}

	@Override
	public List<?> findByJPQL(String jpql) {
		return this.createQuery(jpql).getResultList();
	}

	@Override
	public Object findSingleResultByJPQL(String jpql, Object... parans) {
		return this.createQuery(jpql, parans).getSingleResult();
	}
	
	@Override
	public Object findSingleResultByJPQL(String jpql) {
		return this.createQuery(jpql).getSingleResult();
	}

	@Override
	public int count() {
		String jpql = new String("SELECT COUNT(*) FROM " + this.getPersistentClass().getSimpleName());
		return Integer.parseInt(this.createQuery(jpql).getSingleResult().toString());
	}

}
