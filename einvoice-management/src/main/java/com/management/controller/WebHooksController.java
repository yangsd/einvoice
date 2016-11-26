package com.management.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.management.commons.base.BaseController;
import com.management.commons.scan.SpringUtils;
import com.management.commons.utils.JsonUtils;
import com.management.commons.utils.StringUtils;
import com.management.event.WebHooksEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * WebHooks 自动更新部署
 * Created by L.cm on 2016/9/23.
 */
@Controller
@SuppressWarnings("unchecked")
public class WebHooksController extends BaseController {
    private static final Logger logger = LogManager.getLogger(WebHooksController.class);
    
    /**
     * git@osc WebHooks 设置
     * WIKI: http://git.oschina.net/oschina/git-osc/wikis/WebHook-%E4%BD%BF%E7%94%A8%E7%AE%80%E4%BB%8B
     * @param  request json字符串
     * @return jsonBean
     */
    @RequestMapping(value = "webhooks", method = RequestMethod.POST)
    @ResponseBody
    public Object hooks(HttpServletRequest request) {
        String hook = request.getParameter("hook");
        if (StringUtils.isBlank(hook)) {
            return renderError("json hook isBlank!");
        }
        logger.info("webhooks json: {}", hook);
        Map<String, Object> hookMap = JsonUtils.parse(hook, Map.class);
        // 发送事件 ThreadPoolTaskExecutor
        SpringUtils.publishEvent(new WebHooksEvent(hookMap));
        return renderSuccess("ok");
    }
}
