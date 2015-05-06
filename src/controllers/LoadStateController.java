package controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import common.Constants;
import models.MainState;
import views.MainView;
/**
 * This Class is used Memento Design Pattern to help load state and store state
 * So we can keep track of the state information
 * @author xujian
 *
 */
public class LoadStateController implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4783112272076532027L;
	
	/**
	 * Load the state from the previous state
	 * @param s
	 * @return
	 */
	public LoadMementoController loadState(String s) {
		LoadMementoController loadMementoController = null;
		if (s.equals("")) {
			//default one: load the state from MEMENTO_STORAGE file
			ObjectInputStream ois = null;
			if (new File(Constants.MEMENTO_STORAGE).exists()) {
				try {
					ois = new ObjectInputStream(new FileInputStream(
							Constants.MEMENTO_STORAGE));
					loadMementoController = (LoadMementoController) ois
							.readObject();
					ois.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				if (ois != null) {
					try {
						ois.close();
					} catch (IOException ioe) {
						ioe.printStackTrace();
					}
				}
			}
		} 
		//specific one: load the state from user input file
		else {
			ObjectInputStream ois = null;
			if (new File(s).exists()) {
				try {
					ois = new ObjectInputStream(new FileInputStream(s));
					loadMementoController = (LoadMementoController) ois
							.readObject();
					ois.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				if (ois != null) {
					try {
						ois.close();
					} catch (IOException ioe) {
						ioe.printStackTrace();
					}
				}
			}
		}
		return loadMementoController;
	}
	
	/**
	 * Store current state into the file
	 * @param mainView
	 * @param mainState
	 * @param s
	 */
	public void storeState(MainView mainView, MainState mainState, String s){
		mainView.resetFont();
		if(s.equals("")){
			////default one: store the state into MEMENTO_STORAGE file
			File mementoFile = new File(Constants.MEMENTO_STORAGE);
			if(!mementoFile.exists()){
				try{
					mementoFile.createNewFile();
				} catch (Exception e){
					e.printStackTrace();
				}
			}
			ObjectOutputStream oos = null;
			try{
				oos = new ObjectOutputStream(new FileOutputStream(Constants.MEMENTO_STORAGE));
				oos.writeObject(new LoadMementoController(mainView));
			}catch(Exception e){
				e.printStackTrace();
			}
			if (oos != null) {
	            try {
	                oos.close();
	            } catch (IOException ioe) {
	                ioe.printStackTrace();
	            }
	        }
		}
		//Specific one: store the state into user input file
		else{
			File mementoFile = new File(s);
			if(!mementoFile.exists()){
				try{
					mementoFile.createNewFile();
				} catch (Exception e){
					e.printStackTrace();
				}
			}
			ObjectOutputStream oos = null;
			try{
				oos = new ObjectOutputStream(new FileOutputStream(s));
				oos.writeObject(new LoadMementoController(mainView));
			}catch(Exception e){
				e.printStackTrace();
			}
			if (oos != null) {
	            try {
	                oos.close();
	            } catch (IOException ioe) {
	                ioe.printStackTrace();
	            }
	        }
		}
	}
	
}
