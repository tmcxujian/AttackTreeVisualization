package controllers;

import java.util.List;
import java.util.Set;

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

	/**
	 * Constructor for CalculationController
	 * 
	 * @param mainView
	 * @param mainState
	 */
	public CalculationController(MainView mainView, MainState mainState) {
		this.mainView = mainView;
		this.mainState = mainState;
	}

	/**
	 * Real calculation for security condition
	 */
	public void calculateSecrityCondition() {
		for (NodeView nodeView : this.mainView.getNodeViews()) {
			Node node = nodeView.getNode();
			if (node.isLeaf()) {
				List<String> s = node.getRelavantCounterMeasures();
				if (s.size() == 0.0) {
					this.securityScore = 0.0;
					break;
				}

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

				Set<CounterMeasure> cm = node.getSpecificCounterMeasures();
				for (CounterMeasure c : cm) {
					
					if (c.getGeneralType().equals("Access")) {
						System.out.println(c.getValue());
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
						System.out.println(c.getValue());
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
						System.out.println(c.getValue());
						matchedItems = matchedBackup ? matchedItems
								: matchedItems + 1;
						matchedBackup = true;
						implementedRecovery++;
					} else if (c.getGeneralType().equals("Cryptography")) {
						System.out.println(c.getValue());
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
						System.out.println(c.getValue());
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
				
				System.out.println("1" + implementedAuthentication + " " + matchedAccess);
				System.out.println(implementedAccessControl);
				System.out.println(implementedPhysicalExamination);
				System.out.println(implementedFirewall);
				System.out.println("2" + implementedUpdateSecurityPatches + " " + matchedProtection);
				System.out.println(implementedAntivirus);
				System.out.println("3" + implementedRecovery + " " + matchedBackup);
				System.out.println("4" + implementedCryptography + " " + matchedCryptography);
				System.out.println(implementedIntegrity);
				System.out.println("5" + implementedAuditing + " " + matchedDetection);
				System.out.println(implementedIntrusionDetection);
				System.out.println(implementedPhysicalDetection);
				
				
				double w = matchedItems * 1.0 / s.size();
				// System.out.println(matchedItems + "  " + s.size());
				if (w == 0.0) {
					this.securityScore = 0.0;
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
					System.out.println("Access: tempAttackResult =  " + temp);
					System.out.println("Access: tempAttackResult =  " + tempAttackResult);
				}
				if (matchedProtection) {
					double temp = 1.0 * implementedUpdateSecurityPatches / 2.0
							+ 1.0 * implementedAntivirus / 1.0;
					temp = 1.0 * temp / 2.0;
					tempAttackResult = tempAttackResult <= temp ? tempAttackResult
							: temp;
					System.out.println("Protection: tempAttackResult =  " + temp);
					System.out.println("Protection: tempAttackResult =  " + tempAttackResult);
				}
				if (matchedBackup) {
					double temp = 1.0 * implementedRecovery / 3.0;
					temp = 1.0 * temp / 1.0;
					tempAttackResult = tempAttackResult <= temp ? tempAttackResult
							: temp;
					System.out.println("Backup: tempAttackResult =  " + temp);
					System.out.println("Backup: tempAttackResult =  " + tempAttackResult);
				}
				if (matchedCryptography) {
					double temp = (1.0 * implementedCryptography / 1.0) + (1.0
							* implementedIntegrity / 1.0);
					temp = 1.0 * temp / 2.0;
					tempAttackResult = tempAttackResult <= temp ? tempAttackResult
							: temp;
					System.out.println("Cryptgraphy: tempAttackResult =  " + temp);
					System.out.println("Cryptgraphy: tempAttackResult =  " + tempAttackResult);
				}
				if (matchedDetection) {
					double temp = 1.0 * implementedAuditing / 3.0 + 1.0
							* implementedIntrusionDetection / 1.0 + 1.0
							* implementedPhysicalDetection / 2.0;
					temp = 1.0 * temp / 2.0;
					tempAttackResult = tempAttackResult <= temp ? tempAttackResult
							: temp;
					System.out.println("Detection: tempAttackResult =  " + temp);
					System.out.println("Detection: tempAttackResult =  " + tempAttackResult);
				}
				System.out.println("w = " + w);
				System.out.println("tempAttackResult =  " + tempAttackResult);
				System.out.println("Node is " + node.getAttackNodeName());
				System.out.println();
				double temperarory = w * tempAttackResult;
				this.securityScore = this.securityScore <= temperarory ? this.securityScore
						: temperarory;
			}
		}
		this.mainView.getResultView().updateLabel(
				"Quantification Result = " + this.securityScore);
		this.mainView.refresh();
	}

	public void print(){
		
		
		
	}
	
}
