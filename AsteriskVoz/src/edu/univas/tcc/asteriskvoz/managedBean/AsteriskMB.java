package edu.univas.tcc.asteriskvoz.managedBean;

import java.io.IOException;

import javax.ejb.Stateful;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


@ViewScoped
@ManagedBean
@Stateful
public class AsteriskMB {

	/* 
	 * Metodo para executar o Verbio e em seguida o Asterisk,
	 * NOTA: primeiro tem que dar chmod 777 em /var/run/asterisk/asterisk.pid
	 * e também para /var/run/asterisk/asterisk.ctl
	 */
	public void exeAsterisk() {
		
		Process p;
		try {

			p = Runtime.getRuntime().exec("/home/altieres/initasterisk.sh");
			p.waitFor();
			
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void initCLI() {
		
		Process p;
		try {

			p = Runtime.getRuntime().exec("/home/altieres/initcli.sh");
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

		p = Runtime.getRuntime().exec("/home/altieres/endcli.sh");
		p.waitFor();
		
		
	} catch (IOException e) {
		e.printStackTrace();
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
}
