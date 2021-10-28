package koast.quiz1.lib;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.PathNotFoundException;

@Component("skillScore")
public class SkillScore {

	@Resource(name = "dBLib")
	private DBLib dBLib;
	
	private Gson gson;
	
	public SkillScore() {
		gson = new GsonBuilder().create();
	}
	
	/**
	 * 주어진 args 데이터를 이용해 Json 형태의 TreeMap 을 구현하는 메서드
	 * 
	 * 아래는 예시
	 * args = {"a", {"b","c"}, {"d","e"}}
	 *
	 * a ─ b ─┬─ d
	 *     │  └─ e
	 *     c ─┬─ d
	 *        └─ e
	 *
	 * @param args TreeMap 의 가지를 만들기 위한 Key
	 * @param index 재귀호출 Depth
	 * @param nullValue Tree 의 마지막 Leap 초기화 값
	 * @return
	 */
	public Object recursiveCreateTree(Object[] args, int index, String nullValue) {
		
		if(index > args.length-1) {
			return nullValue;
		}
		
		Object arg = args[index];
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		// ==== @@@@ ====
		// arg 객체의 type 에 따라 recursiveCreateTree 메서드를 재귀 호출
		// arg 객체는 String, String[] 형태를 가짐
		// ==== @@@@ ====
		
		return map;
	}
	
	@SuppressWarnings("unchecked")
	public DocumentContext GetData(String d, String centre, String[] sc, String[] dom, String[] par, String t, String[] s) {
		
		Object obj = recursiveCreateTree(new Object[]{centre, dom, par, sc, t, s}, 0, null);
		
		if(!(obj instanceof java.util.Map)) {
			return null;
		}
		
		Map<String, Object> dataMap = (Map<String, Object>)obj;
			
		List<VrfyVO> vrfyVOList = dBLib.getVrfyData(d, centre, sc, dom, par, t, s);
			
		DocumentContext data = JsonPath.parse(this.gson.toJson(dataMap));
			
		for(int i=0 ; i<vrfyVOList.size() ; i++) {
			
			// ==== @@@@ ====
			// data 의 알맞는 위치에 데이터를 삽입	
			// 삽입 예시 : data.put(jsonPath, key, value);
			// https://github.com/json-path/JsonPath 참조
			// ==== @@@@ ====
		}
		
		return data;
	}
	
	/**
	 * data1, data2 객체를 이용하여 diff 객체에
	 * data1, data2 의 비교 데이터를 세팅하는 메서드
	 * @param diff diff 데이터가 저장될 객체
	 * @param data1 data1 객체
	 * @param data2 data2 객체
	 * @param centre1 centre1 Key
	 * @param centre2 centre2 Key
	 * @param args Json Key Array
	 * @param jsonPath JsonPath 를 사용하여 접근할 Selector 문자열
	 * @param index 재귀호출 Depth
	 */
	public void recursiveSetDiff(DocumentContext diff, DocumentContext data1, DocumentContext data2, String centre1, String centre2, Object[] args, String jsonPath, int index) {
		
		if(index > args.length-1) {			
			
			Double v1 = null;
			Double v2 = null;
			Long ratio = null;
			
			try {
				
				diff.set("$" + jsonPath, JsonPath.parse("{\"v1\" : null, \"v2\" : null, \"ratio\" : null}").json());
				
				v1 = Double.valueOf(data1.read("$." + centre1 + jsonPath).toString());
				v2 = Double.valueOf(data2.read("$." + centre2 + jsonPath).toString());
				
				// ==== @@@@ ====
				// ratio 계산
				// ratio 는 소수점 0자리 까지 기록
				// ratio 는 (v1-v2)와 v2와의 비
				// sc가 rmse, me, mae 일 경우 * -1
				// ==== @@@@ ====
					
			} catch (PathNotFoundException e) {
				//e.printStackTrace();				
			} finally {
				
				diff.set("$" + jsonPath +".ratio", ratio);
				diff.set("$" + jsonPath + ".v1", v1);
				diff.set("$" + jsonPath + ".v2", v2);
			}
			
			return;
		}
		
		Object arg = args[index];
		
		if(arg instanceof java.lang.String) {
			
			recursiveSetDiff(diff, data1, data2, centre1, centre2, args, jsonPath + "." + arg.toString(), index+1);
			
		} else if(arg instanceof java.lang.String[]) {
			
			for(String s : (String[])arg) {
				recursiveSetDiff(diff, data1, data2, centre1, centre2, args, jsonPath + "." + s, index+1);
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	public DocumentContext GetDiff(DocumentContext data1, DocumentContext data2, String centre1, String centre2, String[] sc, String[] dom, String[] par, String t, String[] s) {
		
		Object obj = recursiveCreateTree(new Object[]{dom, par, sc, t, s}, 0, "");
		
		if(!(obj instanceof java.util.Map)) {
			return null;
		}
		
		Map<String, Object> dataMap = (Map<String, Object>)obj;
		
		DocumentContext diff = JsonPath.parse(this.gson.toJson(dataMap));
		
		recursiveSetDiff(diff, data1, data2, centre1, centre2, new Object[]{dom, par, sc, t, s}, "", 0);
	
		return diff;
	}
}
