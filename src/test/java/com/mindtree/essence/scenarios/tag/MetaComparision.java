package com.mindtree.essence.scenarios.tag;

import java.util.Arrays;

public class MetaComparision {

	private String compTitle;
	private String compKeyPropertyNames;
	private String compValuePropertyName;
	private Integer[] compKeyPropertyPositions;

	public MetaComparision(String compTitle, String compKeyPropertyNames, String compValuePropertyName,
			Integer[] compKeyPropertyPositions) {
		super();
		this.compTitle = compTitle;
		this.compKeyPropertyNames = compKeyPropertyNames;
		this.compValuePropertyName = compValuePropertyName;
		this.compKeyPropertyPositions = compKeyPropertyPositions;
	}

	public String getCompTitle() {
		return compTitle;
	}

	public String getCompKeyPropertyNames() {
		return compKeyPropertyNames;
	}

	public String getCompValuePropertyName() {
		return compValuePropertyName;
	}

	public Integer[] getCompKeyPropertyPositions() {
		return compKeyPropertyPositions;
	}

	@Override
	public String toString() {
		return "MetaComparision [compTitle=" + compTitle + ", compKeyPropertyNames=" + compKeyPropertyNames
				+ ", compValuePropertyName=" + compValuePropertyName + ", compKeyPropertyPositions="
				+ Arrays.toString(compKeyPropertyPositions) + "]";
	}

}
