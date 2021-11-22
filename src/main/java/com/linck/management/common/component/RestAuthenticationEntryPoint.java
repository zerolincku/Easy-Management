package com.linck.management.common.component;

import cn.hutool.json.JSONUtil;
import com.linck.management.common.api.Result;
import com.linck.management.common.model.constant.Constans;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: MyManagement
 * @description 未登录或者token失效访问接口时，自定义的返回结果
 * @author: linck
 * @create: 2020-08-09 23:31
 **/
@Component
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException {
        httpServletResponse.setCharacterEncoding(Constans.UTF_8);
        httpServletResponse.setContentType(Constans.APPLICATION_JSON);
        httpServletResponse.getWriter().println(JSONUtil.parse(Result.unauthorized(e.getMessage())));
        httpServletResponse.getWriter().flush();
    }
}
