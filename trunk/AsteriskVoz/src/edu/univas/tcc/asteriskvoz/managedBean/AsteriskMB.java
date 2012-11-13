package edu.univas.tcc.asteriskvoz.managedBean;

import java.io.IOException;

import javax.ejb.Stateful;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.asteriskjava.manager.AuthenticationFailedException;
import org.asteriskjava.manager.ManagerConnection;
import org.asteriskjava.manager.ManagerConnectionFactory;
import org.asteriskjava.manager.TimeoutException;
import org.asteriskjava.manager.action.CommandAction;

import edu.univas.tcc.asteriskvoz.exception.AsteriskClientException;


@ViewScoped
@ManagedBean
@Stateful
public class AsteriskMB {

	/* 
	 * Metodo para executar o Verbio e em seguida o Asterisk,
	 * NOTA: primeiro tem que dar chmod 777 em /var/run/asterisk/asterisk.pid
	 * e também para /var/run/asterisk/asterisk.ctl
	 */
	private ManagerConnection managerConnection = null;
	
	public String restartAsterisk() throws AsteriskClientException {
		boolean error = false;
		try {

			ManagerConnectionFactory managerConnectionFactory = new ManagerConnectionFactory(
					"localhost", "manager", "pa55w0rd");
			managerConnection = managerConnectionFactory
					.createManagerConnection();
			managerConnection.login();

		} catch (IllegalStateException e) {
			throw new AsteriskClientException(
					"Não foi possível estabelecer uma conexão com o servidor.", e);
		} catch (IOException e) {
			//throw new AsteriskClientException(
			//		"O servidor não foi encontrado no endereço informado.", e);
			error = true;
		} catch (AuthenticationFailedException e) {
			throw new AsteriskClientException(
					"Usuário ou senha inválidos para o servidor localhost.", e);
		} catch (TimeoutException e) {
			throw new AsteriskClientException(
					"Tempo de conexão expirou", e);
		}

		CommandAction ca = new CommandAction("core restart now");
		try {
			managerConnection.sendAction(ca);
			
			managerConnection.logoff();
			return "restartconfirm";
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
		
		if(error){
			return "startasterisk";
		}
		return null;
	}
	
	public void initCLI() {
		
		Process p;
		try {

			p = Runtime.getRuntime().exec("/home/rafael/initcli.sh");
			p.waitFor();
			
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public void endCLI() {
	
	Process p;
	try {

		p = Runtime.getRuntime().exec("/home/rafael/endcli.sh");
		p.waitFor();
		
		
	} catch (IOException e) {
		e.printStackTrace();
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	
	public String loadVerbio() throws AsteriskClientException {
		boolean error = false;
		try {

			ManagerConnectionFactory managerConnectionFactory = new ManagerConnectionFactory(
					"localhost", "manager", "pa55w0rd");
			managerConnection = managerConnectionFactory
					.createManagerConnection();
			managerConnection.login();

		} catch (IllegalStateException e) {
			throw new AsteriskClientException(
					"Não foi possível estabelecer uma conexão com o servidor.", e);
		} catch (IOException e) {
			//throw new AsteriskClientException(
			//		"O servidor não foi encontrado no endereço informado.", e);
			error = true;
		} catch (AuthenticationFailedException e) {
			throw new AsteriskClientException(
					"Usuário ou senha inválidos para o servidor localhost.", e);
		} catch (TimeoutException e) {
			throw new AsteriskClientException(
					"Tempo de conexão expirou", e);
		}

		CommandAction ca = new CommandAction("module load app_verbio_speech.so");
		try {
			managerConnection.sendAction(ca);
			
			managerConnection.logoff();
			return "restartconfirm";
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
		
		if(error){
			return "startasterisk";
		}
		return null;
	}
}
