package com.mmall.common;

import com.mmall.exception.ParamException;
import com.mmall.exception.PermissionException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Slf4j
public class SpringExceptionResolver implements HandlerExceptionResolver {


    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        String url=httpServletRequest.getRequestURL().toString();
        ModelAndView mv = null;
        String defaultMsg="System error";
        if(url.endsWith(".json")){
            if(e instanceof PermissionException||e instanceof ParamException){
                JsonData result=JsonData.fail(e.getMessage());
                mv=new ModelAndView("jsonView",result.toMap());

            }
            else {
                log.error("unknown json exception url:"+url,e);
                JsonData result=JsonData.fail(defaultMsg);
                mv=new ModelAndView("jsonView",result.toMap());

            }
        }else if(url.endsWith(".page")){
            log.error("unknown page exception url:"+url,e);
            JsonData result = JsonData.fail(defaultMsg);
            mv = new ModelAndView("exception", result.toMap());

        }
        else {
            log.error("unknown exception url:"+url,e);
            JsonData result = JsonData.fail(defaultMsg);
            mv = new ModelAndView("exception", result.toMap());

        }

        return mv;
    }
}
