package com.interaction.vo;

public class EvaluateKeys {
	int keyId;
	String key;
	String value;
	
	public EvaluateKeys(int keyId,String key,String value){
		this.setValue(value);
		this.setKeyId(keyId);
		this.setKey(key);
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public int getKeyId() {
		return keyId;
	}

	public void setKeyId(int keyId) {
		this.keyId = keyId;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
	

}
