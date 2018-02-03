/**
 * 
 */
package cn.cd.caoyeung.webapp.controller.base;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.cd.caoyeung.webapp.model.ResultUtil;
import cn.cd.caoyeung.webapp.model.ResultVO;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class BaseController {
	private static final Log LOGGER = LogFactory.getLog(BaseController.class);

	SerializerFeature[] feature = { SerializerFeature.DisableCircularReferenceDetect,
			SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteNullStringAsEmpty,
			SerializerFeature.WriteNullBooleanAsFalse, SerializerFeature.WriteMapNullValue };

	protected String buildSuccessInfo(Object resultData) {
		LOGGER.debug(String.format("enter function, %s", resultData));
		ResultVO resultVO = ResultUtil.success(resultData);
		return JSON.toJSONString(resultVO, feature);
	}

	protected String buildErrorInfo(String failedMsg) {
		LOGGER.debug(String.format("enter function, %s", failedMsg));
		ResultVO resultVo = ResultUtil.error(failedMsg);
		return JSON.toJSONString(resultVo, feature);
	}
	protected String buildResultVOInfo(boolean isSuccess,String msg,Object data) {
		Integer code = (isSuccess == true? 0:1);
		ResultVO resultVo = new ResultVO(code, msg, data);
		return JSON.toJSONString(resultVo, feature);
	}
}
