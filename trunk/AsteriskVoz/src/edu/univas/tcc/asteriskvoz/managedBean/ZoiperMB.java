package edu.univas.tcc.asteriskvoz.managedBean;

import java.io.IOException;

import javax.ejb.Stateful;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


@ViewScoped
@ManagedBean
@Stateful
public class ZoiperMB {


	public void exeZoiper() {
		
		Process p;
		try {

			p = Runtime.getRuntime().exec("/home/altieres/zoiper.sh");
			p.waitFor();
			
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}