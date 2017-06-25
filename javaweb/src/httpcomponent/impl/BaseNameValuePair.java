package httpcomponent.impl;

import org.apache.http.NameValuePair;

public class BaseNameValuePair implements NameValuePair {
	private String name;
	private String value;

	public BaseNameValuePair() {

	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public String getValue() {
		return this.value;
	}

}
