package javabase.test;

import java.util.HashMap;
import java.util.Map;

public class TestMap {
	public static void main(String[] args) {
		Map<String,String> map = new HashMap<>();
		map.put(null, null);
		map.put("", null);
		map.put(" ", null);
		System.out.println(map);
		System.out.println(map.containsKey(null));
	}
}
