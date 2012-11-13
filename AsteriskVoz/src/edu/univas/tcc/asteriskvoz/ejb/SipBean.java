package edu.univas.tcc.asteriskvoz.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import edu.univas.tcc.asteriskvoz.entity.Sip;

@Stateless
public class SipBean {
	
	@PersistenceContext
	EntityManager em;
	
	public void createSip(Sip sip){
		em.persist(sip);
	}
	
	@SuppressWarnings("unchecked")
	public List<Sip> findSip() {
		return em.createQuery("FROM Sip").getResultList();
	}
	@SuppressWarnings("unchecked")
	public List<Sip> findEditSip(int id) {
		return em.createQuery("FROM Sip where id = " + id).getResultList();
	}

}
