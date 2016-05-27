package com.mindtree.essence.scenarios.tag;

import java.io.Serializable;

public class MetaTag implements Serializable {

	private static final long serialVersionUID = 1L;
	private String name;
	private String property;
	private String content;
	private String httpEquiv;

	public static final int ATTRIB_NAME = 0;
	public static final int ATTRIB_PROPERTY = 1;
	public static final int ATTRIB_CONTENT = 2;
	public static final int ATTRIB_HTTP_EQUIV = 3;

	public MetaTag() {
		super();
	}

	public MetaTag(String name, String property, String content, String httpEquiv) {
		super();
		this.name = name;
		this.property = property;
		this.content = content;
		this.httpEquiv = httpEquiv;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getHttpEquiv() {
		return httpEquiv;
	}

	public void setHttpEquiv(String httpEquiv) {
		this.httpEquiv = httpEquiv;
	}

	public String getAttribValue(int position) {
		switch (position) {
		case ATTRIB_NAME:
			return getName();
		case ATTRIB_PROPERTY:
			return getProperty();
		case ATTRIB_CONTENT:
			return getContent();
		case ATTRIB_HTTP_EQUIV:
			return getHttpEquiv();
		default:
			return null;
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + ((httpEquiv == null) ? 0 : httpEquiv.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((property == null) ? 0 : property.hashCode());
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
		MetaTag other = (MetaTag) obj;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (httpEquiv == null) {
			if (other.httpEquiv != null)
				return false;
		} else if (!httpEquiv.equals(other.httpEquiv))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (property == null) {
			if (other.property != null)
				return false;
		} else if (!property.equals(other.property))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MetaTag [name=" + name + ", property=" + property + ", content=" + content + ", httpEquiv=" + httpEquiv
				+ "]";
	}

}
