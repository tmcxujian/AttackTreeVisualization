package controllers;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import models.CounterMeasure;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
/**
 * Reads information from the existing file.
 * Supporting Two different file format. Both csv and xml are doable.
 * @author xujian
 *
 */
public class DictionaryParserController {

	String targetFile;
	String lineSplitBy = ",";
	String line;
	List<CounterMeasure> counterMeasures;
	Set<String> relevantType;
	List<CounterMeasure> accessType;
	List<CounterMeasure> protectionType;
	List<CounterMeasure> backupType;
	List<CounterMeasure> cryptohraphyType;
	List<CounterMeasure> detectionType;
	
	Map<String,String> countermeasures = new HashMap<String,String>();
	BufferedReader br;
	private Document dom;
	
	//Constructor for DictionaryParserController
	public DictionaryParserController(String fileName) {
		this.targetFile = fileName;
	}
	
	public String getFileName() {
		return this.targetFile;
	}

	/**
	 * CSV Parser for parsing csv file
	 * @return
	 */
	public Map<String, String> csvParse() {
		this.countermeasures.clear();
		try {
			br = new BufferedReader(new FileReader(this.targetFile));
			br.readLine();
			while ((line = br.readLine()) != null) {
				String[] str = line.split(this.lineSplitBy);
				String number = str[0].trim();
				String type = str[1].trim();
				String value = str[2].trim();
				this.countermeasures.put(type + number, value);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return this.countermeasures;
	}
	
	/**
	 * XML Parser for parsing xml file
	 */
	public void xmlParser() {
		String basicType = "";
		String generalType = "";
		String subType = "";
		this.relevantType = new HashSet<String>();
		this.counterMeasures = new ArrayList<CounterMeasure>();
		this.accessType = new ArrayList<CounterMeasure>();
		this.protectionType = new ArrayList<CounterMeasure>();
		this.backupType = new ArrayList<CounterMeasure>();
		this.cryptohraphyType = new ArrayList<CounterMeasure>();
		this.detectionType = new ArrayList<CounterMeasure>();
		
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document document = db.parse(targetFile);
			NodeList users = document.getChildNodes();

			for (int i = 0; i < users.getLength(); i++) {
				Node user = users.item(i);
				NodeList userInfo = user.getChildNodes();
				basicType = user.getNodeName();
				
				for (int j = 0; j < userInfo.getLength(); j++) {
					Node node = userInfo.item(j);
					NodeList userMeta = node.getChildNodes();
					if (!node.getNodeName().equals("#text")) {
						generalType = node.getNodeName();
						subType = node.getAttributes().getNamedItem("Type")
								.getNodeValue();
						if(! this.relevantType.contains(generalType)){
							this.relevantType.add(generalType);
						}						
						// System.out.println(node.getNodeName());
						// System.out.println(node.getAttributes().getNamedItem("Type"));
					}
					for (int k = 0; k < userMeta.getLength(); k++) {
						if (userMeta.item(k).getNodeName() != "#text") {
							CounterMeasure cm = new CounterMeasure(basicType,
									generalType, subType, userMeta.item(k)
											.getTextContent());
							this.counterMeasures.add(cm);
							// System.out.println(userMeta.item(k).getNodeName()
							// + " : " + userMeta.item(k).getTextContent());
						}
					}
					// System.out.println();
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		processCounterMeasure();
		//System.out.println(this.accessType.size());
	}

	/**
	 * Process with the counterMeasures to make them into different groups
	 */
	public void processCounterMeasure(){
		for(CounterMeasure cm: this.counterMeasures){
			switch (cm.getGeneralType()) {
			case "Access":
				this.accessType.add(cm);
				break;
			case "Protection":
				this.protectionType.add(cm);
				break;
			case "Backup":
				this.backupType.add(cm);
				break;
			case "Cryptography":
				this.cryptohraphyType.add(cm);
				break;
			case "Detection":
				this.detectionType.add(cm);
				break;
			default:
				break;
			}
		}
	}
	
	public List<CounterMeasure> getAccessType() {
		return accessType;
	}

	public void setAccessType(List<CounterMeasure> accessType) {
		this.accessType = accessType;
	}

	public List<CounterMeasure> getProtectionType() {
		return protectionType;
	}

	public void setProtectionType(List<CounterMeasure> protectionType) {
		this.protectionType = protectionType;
	}

	public List<CounterMeasure> getBackupType() {
		return backupType;
	}

	public void setBackupType(List<CounterMeasure> backupType) {
		this.backupType = backupType;
	}

	public List<CounterMeasure> getCryptohraphyType() {
		return cryptohraphyType;
	}

	public void setCryptohraphyType(List<CounterMeasure> cryptohraphyType) {
		this.cryptohraphyType = cryptohraphyType;
	}

	public List<CounterMeasure> getDetectionType() {
		return detectionType;
	}

	public void setDetectionType(List<CounterMeasure> detectionType) {
		this.detectionType = detectionType;
	}

	public List<CounterMeasure> getCounterMeasures(){
		return this.counterMeasures;
	}
	
	public Set<String> getFirstType(){
		return this.relevantType;
	}
}
