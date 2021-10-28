package koast.quiz1.lib;

import java.util.HashMap;
import java.util.Map;

public class Utils {

	public static Map<String, Object> createResponse(String level, String code, String msg, Object data) {
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		resultMap.put("result", level);
		resultMap.put("code", code);
		resultMap.put("message", msg);
		resultMap.put("data", data);
		
		return resultMap;
	}
}
