package cn.cd.caoyeung.webapp.model.pojo;

import cn.cd.caoyeung.webapp.model.vo.ResultVO;

public class ResultUtil {

	public static ResultVO success(Object object) {
		ResultVO result = new ResultVO();
		result.setCode(0);
		result.setMsg("SUCCESS");
		result.setData(object);
		return result;
	}
	public static ResultVO success() {
		return success(null);
	}

	public static ResultVO error(String msg) {
		ResultVO result = new ResultVO();
		result.setCode(1);
		result.setMsg(msg);
		return result;
	}

}