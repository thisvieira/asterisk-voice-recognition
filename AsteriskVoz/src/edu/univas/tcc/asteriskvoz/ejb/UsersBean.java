package edu.univas.tcc.asteriskvoz.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import edu.univas.tcc.asteriskvoz.entity.Users;

@Stateless
public class UsersBean {

	@PersistenceContext
	EntityManager em;

	public void createUsers(Users users) {
		em.persist(users);
	}

	@SuppressWarnings("unchecked")
	public List<Users> findUsers() {
		return em.createQuery("FROM Users").getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> findUsersLogin(Users users) {
		return em.createQuery(
				"select user_name, passw FROM Users where user_name='"
						+ users.getUser_name() + "'").getResultList();
	}
}
