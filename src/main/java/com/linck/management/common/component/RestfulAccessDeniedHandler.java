package com.linck.management.common.component;

import cn.hutool.json.JSONUtil;
import com.linck.management.common.api.Result;
import com.linck.management.common.model.constant.Constans;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 访问接口无权限时，自定义返回结果
 * @author linck
 **/
@Component
public class RestfulAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException {
        httpServletResponse.setCharacterEncoding(Constans.UTF_8);
        httpServletResponse.setContentType(Constans.APPLICATION_JSON);
        httpServletResponse.getWriter().println(JSONUtil.parse(Result.forbidden(e.getMessage())));
        httpServletResponse.getWriter().flush();
    }
}
