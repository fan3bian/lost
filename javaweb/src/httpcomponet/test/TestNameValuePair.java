package httpcomponet.test;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

public class TestNameValuePair {
	public static void main(String[] args) {
		NameValuePair basicPair = new BasicNameValuePair("zhangshuyi", "quao");
		System.out.println(basicPair.getName());
		System.out.println(basicPair.getValue());
		System.out.println(basicPair.toString());
		System.out.println(basicPair.equals(new BasicNameValuePair("zhangshuyi", "quao")));
	}
}
