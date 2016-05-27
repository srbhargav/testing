package com.mindtree.essence.scenarios.tag;

public class KeyValuePojo {

	private String key;
	private String value;

	private boolean pairExists;

	public KeyValuePojo() {
		super();
	}

	public KeyValuePojo(String key, String value) {
		super();
		this.key = key;
		this.value = value;
		this.pairExists = false;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public boolean isPairExists() {
		return pairExists;
	}

	public void setPairExists(boolean pairExists) {
		this.pairExists = pairExists;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((key == null) ? 0 : key.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		KeyValuePojo other = (KeyValuePojo) obj;
		if (key == null) {
			if (other.key != null)
				return false;
		} else if (!key.equals(other.key))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "KeyValuePojo [key=" + key + ", value=" + value + "]";
	}

}
