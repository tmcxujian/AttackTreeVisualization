package models;

import java.io.Serializable;
/**
 * This is countermeasure mode
 * It contains four part: basicType/generalType/subType/value
 * @author xujian
 *
 */
public class CounterMeasure implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7464233615075765936L;
	private String basicType;
	private String generalType;
	private String subType;
	private String value;
	
	/**
	 * Construct for CounterMeasure Class, contains following part
	 * @param basicType
	 * @param generalType
	 * @param type
	 * @param value
	 */
	public CounterMeasure(String basicType,String generalType, String type, String value){
		this.basicType = basicType;
		this.generalType = generalType;
		this.subType = type;
		this.value = value;
	}

	public String getBasicType() {
		return basicType;
	}

	public void setBasicType(String basicType) {
		this.basicType = basicType;
	}

	public String getGeneralType() {
		return generalType;
	}

	public void setGeneralType(String generalType) {
		this.generalType = generalType;
	}

	public String getSubType() {
		return subType;
	}

	public void setSubType(String subType) {
		this.subType = subType;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
