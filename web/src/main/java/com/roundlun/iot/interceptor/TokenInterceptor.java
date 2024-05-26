package com.roundlun.iot.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import com.roundlun.iot.domain.AjaxResult;
import com.roundlun.iot.domain.model.LoginUser;
import com.roundlun.iot.utils.StringUtils;
import com.roundlun.iot.utils.TokenUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class TokenInterceptor implements HandlerInterceptor {

    @Autowired
    private TokenUtils tokenUtils;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        AjaxResult ajax = new AjaxResult();
        //如果是OPTIONS请求 直接放行
        String method = request.getMethod();
        try {
            if (method.equals("OPTIONS")) {
                return true;
            }
            LoginUser loginUser = tokenUtils.getLoginUser(request);
            if (StringUtils.isNotNull(loginUser)) {
                tokenUtils.verifyToken(loginUser);
                return true;
            } else {
                ajax.put(AjaxResult.CODE_TAG, 401);
            }
        } catch (SignatureException e) {
            ajax.put(AjaxResult.CODE_TAG, 500);
            ajax.put(AjaxResult.MSG_TAG, "令牌不合法");
        } catch (Exception e) {
            e.printStackTrace();
            ajax.put(AjaxResult.CODE_TAG, 500);
            ajax.put(AjaxResult.MSG_TAG, "令牌验签失败");
        }
        String jsonStr = new ObjectMapper().writeValueAsString(ajax);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().println(jsonStr);
        return false;
    }

}
