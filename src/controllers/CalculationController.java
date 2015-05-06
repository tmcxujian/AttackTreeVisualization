package controllers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import java.io.OutputStream;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import javax.swing.JOptionPane;

import views.MainView;
import views.NodeView;
import models.CounterMeasure;
import models.MainState;
import models.Node;

/**
 * This one really implements the calculation function of this application. Once
 * user hit the calculate button, the result will show in result Window
 * 
 * @author xujian
 *
 */
public class CalculationController {

	private MainView mainView;
	private MainState mainState;
	double securityScore = 1.0;
	private String fileName = "Report.txt";
	//this list is to keep track of all node's information
	private Map<String,Double> list;

	/**
	 * Constructor for CalculationController
	 * 
	 * @param mainView
	 * @param mainState
	 */
	public CalculationController(MainView mainView, MainState mainState) {
		this.mainView = mainView;
		this.mainState = mainState;
		this.list = new HashMap<String, Double>();
	}

	/**
	 * Real calculation for security condition
	 */
	public void calculateSecrityCondition() {		
		this.list = new HashMap<String, Double>();
		//StringBuilder is trying to save all the info
		StringBuilder str = new StringBuilder();
		str.append("Node ----------------------------------------- Score");
		str.append(System.getProperty("line.separator"));
		//for each node, go through all countermeasures implemented
		for (NodeView nodeView : this.mainView.getNodeViews()) {
			Node node = nodeView.getNode();
			//get rid of those intermedia node
			if (node.isLeaf()) {
				List<String> s = node.getRelavantCounterMeasures();
				if (s.size() == 0.0) {
					this.securityScore = 0.0;
					str.append(node.getAttackNodeName() + "    " + 0.0);
					str.append(System.getProperty("line.separator"));
					this.list.put(node.getAttackNodeName(), 0.0);
					break;
				}

				//below parameter is case-specifc
				//you should create a different calculation function based on your own model and countermeasure
				int matchedItems = 0;
				boolean matchedAccess = false;
				boolean matchedProtection = false;
				boolean matchedBackup = false;
				boolean matchedCryptography = false;
				boolean matchedDetection = false;
				int implementedAuthentication = 0;
				int implementedAccessControl = 0;
				int implementedPhysicalExamination = 0;
				int implementedFirewall = 0;
				int implementedUpdateSecurityPatches = 0;
				int implementedAntivirus = 0;
				int implementedRecovery = 0;
				int implementedCryptography = 0;
				int implementedIntegrity = 0;
				int implementedAuditing = 0;
				int implementedIntrusionDetection = 0;
				int implementedPhysicalDetection = 0;

				Map<String,CounterMeasure> cm = node.getSpecificCounterMeasureMap();
				//Keep track of countermeasure type and number
				for(Map.Entry<String, CounterMeasure> entry: cm.entrySet()){	
					CounterMeasure c = entry.getValue();
					if (c.getGeneralType().equals("Access")) {
						matchedItems = matchedAccess ? matchedItems
								: matchedItems + 1;
						matchedAccess = true;
						switch (c.getSubType()) {
						case "Authentication":
							implementedAuthentication++;
							continue;
						case "AccessControl":
							implementedAccessControl++;
							continue;
						case "PhysicalExamination":
							implementedPhysicalExamination++;
							continue;
						case "Firewall":
							implementedFirewall++;
							continue;
						default:
							continue;
						}
					} else if (c.getGeneralType().equals("Protection")) {
						matchedItems = matchedProtection ? matchedItems
								: matchedItems + 1;
						matchedProtection = true;
						switch (c.getSubType()) {
						case "UpdateSecurePatches":
							implementedUpdateSecurityPatches++;
							continue;
						case "Anti-virus":
							implementedAntivirus++;
							continue;
						default:
							continue;
						}
					} else if (c.getGeneralType().equals("Backup")) {
						matchedItems = matchedBackup ? matchedItems
								: matchedItems + 1;
						matchedBackup = true;
						implementedRecovery++;
					} else if (c.getGeneralType().equals("Cryptography")) {
						matchedItems = matchedCryptography ? matchedItems
								: matchedItems + 1;
						matchedCryptography = true;
						switch (c.getSubType()) {
						case "Cryptography":
							implementedCryptography++;
							continue;
						case "Integrity":
							implementedIntegrity++;
							continue;
						default:
							continue;
						}
					} else if (c.getGeneralType().equals("Detection")){
						matchedItems = matchedDetection ? matchedItems
								: matchedItems + 1;
						matchedDetection = true;
						switch (c.getSubType()) {
						case "Auditing":
							implementedAuditing++;
							continue;
						case "IntrusionDetection":
							implementedIntrusionDetection++;
							continue;
						case "PhysicalDetection":
							implementedPhysicalDetection++;
							continue;
						default:
							continue;
						}
					}
					else{
						continue;
					}
				}
				//calcualte w
				double w = matchedItems * 1.0 / s.size();
				if (w == 0.0) {
					this.securityScore = 0.0;
					str.append(node.getAttackNodeName() + "    " + 0.0);
					str.append(System.getProperty("line.separator"));
					this.list.put(node.getAttackNodeName(), 0.0);
					continue;
				}
				double tempAttackResult = 1.0;
				if (matchedAccess) {
					double temp = 1.0 * implementedAccessControl / 4.0 + 1.0
							* implementedAuthentication / 4.0 + 1.0
							* implementedPhysicalExamination / 1.0 + 1.0
							* implementedFirewall / 1.0;
					temp = 1.0 * temp / 4.0;
					tempAttackResult = tempAttackResult <= temp ? tempAttackResult
							: temp;
					//System.out.println("Access: tempAttackResult =  " + temp);
					//System.out.println("Access: tempAttackResult =  " + tempAttackResult);
				}
				if (matchedProtection) {
					double temp = 1.0 * implementedUpdateSecurityPatches / 2.0
							+ 1.0 * implementedAntivirus / 1.0;
					temp = 1.0 * temp / 2.0;
					tempAttackResult = tempAttackResult <= temp ? tempAttackResult
							: temp;
					//System.out.println("Protection: tempAttackResult =  " + temp);
					//System.out.println("Protection: tempAttackResult =  " + tempAttackResult);
				}
				if (matchedBackup) {
					double temp = 1.0 * implementedRecovery / 3.0;
					temp = 1.0 * temp / 1.0;
					tempAttackResult = tempAttackResult <= temp ? tempAttackResult
							: temp;
					//System.out.println("Backup: tempAttackResult =  " + temp);
					//System.out.println("Backup: tempAttackResult =  " + tempAttackResult);
				}
				if (matchedCryptography) {
					double temp = (1.0 * implementedCryptography / 1.0) + (1.0
							* implementedIntegrity / 1.0);
					temp = 1.0 * temp / 2.0;
					tempAttackResult = tempAttackResult <= temp ? tempAttackResult
							: temp;
					//System.out.println("Cryptgraphy: tempAttackResult =  " + temp);
					//System.out.println("Cryptgraphy: tempAttackResult =  " + tempAttackResult);
				}
				if (matchedDetection) {
					double temp = 1.0 * implementedAuditing / 3.0 + 1.0
							* implementedIntrusionDetection / 1.0 + 1.0
							* implementedPhysicalDetection / 2.0;
					temp = 1.0 * temp / 2.0;
					tempAttackResult = tempAttackResult <= temp ? tempAttackResult
							: temp;
					//System.out.println("Detection: tempAttackResult =  " + temp);
					//System.out.println("Detection: tempAttackResult =  " + tempAttackResult);
				}
				//System.out.println("w = " + w);
				//System.out.println("tempAttackResult =  " + tempAttackResult);
				//System.out.println("Node is " + node.getAttackNodeName());
				//System.out.println();
				double temperarory = w * tempAttackResult;
				this.securityScore = this.securityScore <= temperarory ? this.securityScore
						: temperarory;
				str.append(node.getAttackNodeName() + "    " + tempAttackResult * w);//or use temperarory
				str.append(System.getProperty("line.separator"));
				this.list.put(node.getAttackNodeName(), tempAttackResult * w);
			}
		}
		
		str.append(System.getProperty("line.separator"));
		str.append("Overall system's security is " + this.securityScore);
		str.append(System.getProperty("line.separator"));
		str.append("(With 0 to be the most insecure, while 1 to be the most secure)");
		str.append(System.getProperty("line.separator"));
		str.append(System.getProperty("line.separator"));
		
		//go throught the list to find out which nodes are the most vulnerable ones
		for(Map.Entry<String, Double> entry : this.list.entrySet()){
			if(entry.getValue() == (this.securityScore)){
				this.mainView.setFont(entry.getKey());
				str.append("The least secure node is " + entry.getKey());
				str.append(System.getProperty("line.separator"));
			}
		}
		
		//update the result view due to the new security score
		this.mainView.getResultView().updateLabel(
				"system's security condition = " + this.securityScore);
		JOptionPane.showMessageDialog(null, "Report has been generated");
		this.mainView.refresh();
		
		//write into you own file: name_Report.txt
		String s = main.MainLauncher.loadFileName;
		this.fileName = s + "_Report.txt";
		File reportFile = new File(this.fileName);
		if(!reportFile.exists()){
			//create one if not exist
			try{
				reportFile.createNewFile();
			} catch (Exception e){
				e.printStackTrace();
			}
		}
		OutputStream oos = null;
		try{
			oos = new FileOutputStream(this.fileName);
			oos.write(str.toString().getBytes());
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
