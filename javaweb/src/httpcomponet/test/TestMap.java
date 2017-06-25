package httpcomponet.test;

import java.util.HashMap;
import java.util.Map;

public class TestMap {
	public static void main(String[] args) {
		Map<String, Object> map = new HashMap<>();
		map.put("", "");
		map.put(null, null);
		map.put(" ", "hah");
		map.put("  ", null);
		System.out.println(map);
	}
}
