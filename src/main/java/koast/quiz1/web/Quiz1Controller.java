package koast.quiz1.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jayway.jsonpath.DocumentContext;

import koast.quiz1.exception.BuildJsonFailException;
import koast.quiz1.exception.NoDataException;
import koast.quiz1.exception.NoParamException;
import koast.quiz1.lib.SkillScore;
import koast.quiz1.lib.Utils;

@Controller
public class Quiz1Controller {

	@Resource(name = "globals")
	private Properties globals;
	
	@Resource(name = "skillScore")
	private SkillScore skillScore;
	
	@RequestMapping(value = "/scorecard", method = RequestMethod.GET)
	public String scorecardPage() {

		return "scorecard";
	}
	
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value = "/ax/skillscore", method = RequestMethod.POST)
	public Map<String, Object> getSkillscore(@RequestBody Map<String, Object> requestMap) {
	
		String func = (String) requestMap.get("func");
		
		Map<String, Object> args = (Map<String, Object>) requestMap.get("args");
		
		DocumentContext diff = null;
		
		if("diff".equals(func) && args != null) {
			
			try {
				
				diff = getDiff(args);
				
			} catch (NoDataException e) {
				
				return Utils.createResponse("ERROR", "NO_DATA", "데이터가 없습니다.", null);
				
			} catch (NoParamException e) {
				
				return Utils.createResponse("ERROR", "NO_PARAM", "파라미터가 부족합니다." + e.getMessage(), null);
				
			} catch (BuildJsonFailException e) {
				
				return Utils.createResponse("ERROR", "JSON_FAIL", "데이터셋 생성에 실패하였습니다.", null);
			}
		}
		
		return Utils.createResponse("OK", null, null, diff.json());
	}
		
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public DocumentContext getDiff(Map<String, Object> args) throws NoDataException, NoParamException, BuildJsonFailException {
		
		String[] defaultParmKeys = new String[]{"d1", "d2", "centre1", "centre2", "sc", "dom", "par", "t", "s"};
		
		for(String defaultParmKey : defaultParmKeys) {
			
			Object object = args.get(defaultParmKey);
					
			if(object == null) {
				throw new NoParamException(defaultParmKey + "" + args.get(defaultParmKey));	
			} else {
				
				if(object instanceof java.util.List) {
					
					if(((List)object).size() == 0) {
						throw new NoParamException(defaultParmKey + "" + args.get(defaultParmKey));	
					}
				}
				
			}
		}

		String d1 = (String) args.get("d1");
		String d2 = (String) args.get("d2");
		String centre1 = (String) args.get("centre1");
		String centre2 = (String) args.get("centre2");
		List<String> sc = (ArrayList<String>) args.get("sc");
		List<String> dom = (ArrayList<String>) args.get("dom");
		List<String> par = (ArrayList<String>) args.get("par");
		String t = (String) args.get("t");
		List<String> s = (ArrayList<String>) args.get("s");
				
		DocumentContext data1 = this.skillScore.GetData(d1, centre1, sc.toArray(new String[sc.size()]), dom.toArray(new String[dom.size()]), par.toArray(new String[par.size()]), t, s.toArray(new String[s.size()]));
		DocumentContext data2 = this.skillScore.GetData(d2, centre2, sc.toArray(new String[sc.size()]), dom.toArray(new String[dom.size()]), par.toArray(new String[par.size()]), t, s.toArray(new String[s.size()]));
		
		if(data1 == null || data2 == null) {
			throw new BuildJsonFailException(null);
		}
		
		// diff 생성
		DocumentContext diff = this.skillScore.GetDiff(data1, data2, centre1, centre2, sc.toArray(new String[sc.size()]), dom.toArray(new String[dom.size()]), par.toArray(new String[par.size()]), t, s.toArray(new String[s.size()]));
		
		return diff;
	}
}
