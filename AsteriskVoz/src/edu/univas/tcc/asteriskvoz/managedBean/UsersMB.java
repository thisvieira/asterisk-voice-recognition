package edu.univas.tcc.asteriskvoz.managedBean;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateful;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;

import edu.univas.tcc.asteriskvoz.ejb.UsersBean;
import edu.univas.tcc.asteriskvoz.entity.Users;

@ViewScoped
@ManagedBean
@Stateful
public class UsersMB {

	private Users users = new Users();
	private List<Object[]> lstUsers = new ArrayList<Object[]>();
	private String name;
	private String password;

	public String loginUsers() {
		
		try {
			InitialContext ini = new InitialContext();
			UsersBean usersBean = (UsersBean) ini.lookup("java:module/UsersBean");
			FacesContext fc = FacesContext.getCurrentInstance();
			
			lstUsers = usersBean.findUsersLogin(users);
			
			for (Object[] ob : lstUsers) {
				name = (String) ob[0];
				password = (String) ob[1];
			}

			if (users.getUser_name().equals(name) && users.getPassw().equals(password)) {
				System.out.println("existe");
				ExternalContext ec = fc.getExternalContext();
				HttpSession session = (HttpSession) ec.getSession(false);
				session.setAttribute("name", this.name);
				return "asteriskconfig";

			}

		} catch (NamingException e) {
			e.printStackTrace();
		}

		return "loginerror";
	}
	
	public String creatUsers(){
		
		try {
			InitialContext ini = new InitialContext();
			UsersBean usersBean = (UsersBean) ini.lookup("java:module/UsersBean");
			
			usersBean.createUsers(users);
			users = new Users();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "registersucess";
	}

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public List<Object[]> getLstUsers() {
		return lstUsers;
	}

	public void setLstUsers(List<Object[]> lstUsers) {
		this.lstUsers = lstUsers;
	}

	

}
