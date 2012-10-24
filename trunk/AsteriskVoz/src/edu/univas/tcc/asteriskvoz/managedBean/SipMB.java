package edu.univas.tcc.asteriskvoz.managedBean;



import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.asteriskjava.manager.AuthenticationFailedException;
import org.asteriskjava.manager.ManagerConnection;
import org.asteriskjava.manager.ManagerConnectionFactory;
import org.asteriskjava.manager.TimeoutException;
import org.asteriskjava.manager.action.CommandAction;

import edu.univas.tcc.asteriskvoz.entity.Sip;
import edu.univas.tcc.asteriskvoz.exception.AsteriskClientException;

@ManagedBean
@ViewScoped
@Stateless
public class SipMB {

	private Sip registerSip = new Sip();
	private ManagerConnection managerConnection = null;

	public String registerSip() throws AsteriskClientException, TimeoutException {

		File dir = new File("/etc/asterisk");
		File arq = new File(dir, "sip.conf");

		try {
			FileWriter writeExten = new FileWriter(arq, true);

			PrintWriter printExten = new PrintWriter(writeExten);

			printExten.println();
			printExten.println("[" + registerSip.getExtenNumber() + "]");
			printExten.println("secret = " + registerSip.getSecret());
			printExten.println("callerid = " + registerSip.getCallerid());
			printExten.println("host = " + registerSip.getHost());
			printExten.println("context = " + registerSip.getContext());
			printExten.println("type = " + registerSip.getType());
			printExten.println("disallow = " + registerSip.getDisallow());
			printExten.println("allow = " + registerSip.getAllow());

			printExten.flush();

			printExten.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		try {

			ManagerConnectionFactory managerConnectionFactory = new ManagerConnectionFactory(
					"localhost", "manager", "pa55w0rd");
			managerConnection = managerConnectionFactory
					.createManagerConnection();
			managerConnection.login();

		} catch (IllegalStateException e) {
			throw new AsteriskClientException(
					"Não foi possível estabelecer uma conexão com o servidor.",
					e);
		} catch (IOException e) {
			throw new AsteriskClientException(
					"O servidor não foi encontrado no endereço informado.", e);
		} catch (AuthenticationFailedException e) {
			throw new AsteriskClientException(
					"Usuário ou senha inválidos para o servidor localhost.", e);
		}
		System.out.println(managerConnection.getState());
		CommandAction ca = new CommandAction("sip reload");
		try {
			managerConnection.sendAction(ca);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

		
	}

	public String sipReload() {
		return null;
	}

	public Sip getRegisterSip() {
		return registerSip;
	}

	public void setRegisterSip(Sip registerSip) {
		this.registerSip = registerSip;
	}

}
