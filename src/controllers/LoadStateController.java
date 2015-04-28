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

	/*public LoadMementoController loadState(String s){
		ObjectInputStream ois = null;
		LoadMementoController loadMementoController = null;
		if(new File(Constants.MEMENTO_STORAGE).exists()){
			try{
				ois = new ObjectInputStream(new FileInputStream(Constants.MEMENTO_STORAGE));
				loadMementoController = (LoadMementoController) ois.readObject();
				ois.close();
			}catch(Exception e){
				e.printStackTrace();
			}
			if(ois != null){
				try {
					ois.close();
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}
			}
		}
		return loadMementoController;
	}*/
	
	/**
	 * Load the state from the previous state
	 * @param s
	 * @return
	 */
	public LoadMementoController loadState(String s) {
		LoadMementoController loadMementoController = null;
		if (s.equals("")) {
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
		} else {
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

	/*public void storeState(MainView mainView, MainState mainState, String s){
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
	}*/
	
	/**
	 * Store current state into the file
	 * @param mainView
	 * @param mainState
	 * @param s
	 */
	public void storeState(MainView mainView, MainState mainState, String s){
		if(s.equals("")){
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
