package com.linck.management.common.component;

import cn.hutool.json.JSONUtil;
import com.linck.management.common.api.CommonResult;
import com.linck.management.common.constant.Constans;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: MyManagement
 * @description 访问接口无权限时，自定义返回结果
 * @author: linck
 * @create: 2020-08-09 23:24
 **/
@Component
public class RestfulAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        httpServletResponse.setCharacterEncoding(Constans.CHARACTER_ENCODING);
        httpServletResponse.setContentType(Constans.CONTENT_TYPE_APPLICATION_JSON);
        httpServletResponse.getWriter().println(JSONUtil.parse(CommonResult.forbidden(e.getMessage())));
        httpServletResponse.getWriter().flush();
    }
}
