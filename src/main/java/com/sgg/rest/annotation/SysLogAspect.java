package com.sgg.rest.annotation;

import com.alibaba.fastjson.JSON;
import com.sgg.rest.model.SysLog;
import com.sgg.rest.service.SysLogService;
import com.sgg.rest.util.HttpContextUtils;
import com.sgg.rest.util.IpUtils;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * 系统日志：切面处理类
 */
@Aspect
@Component
public class SysLogAspect {

    @Autowired
    private SysLogService sysLogService;

    //定义切点 @Pointcut
    //在注解的位置切入代码
    @Pointcut("@annotation( com.sgg.rest.annotation.MyLog)")
    public void logPoinCut() {
    }

    //切面 配置通知
    @AfterReturning("logPoinCut()")
    public void saveSysLog(JoinPoint joinPoint) {
        //保存日志
        SysLog sysLog = new SysLog();

        //从切面织入点处通过反射机制获取织入点处的方法
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        //获取切入点所在的方法
        Method method = signature.getMethod();

        //获取操作
        MyLog myLog = method.getAnnotation(MyLog.class);
        if (myLog != null) {
            String value = myLog.value();
            sysLog.setOperation(value);//保存获取的操作
        }

        //获取请求的类名
        String className = joinPoint.getTarget().getClass().getName();
        //获取请求的方法名
        String methodName = method.getName();
        sysLog.setMethod(className + "." + methodName);

        //请求的参数
        Object[] args = joinPoint.getArgs();
        //将参数所在的数组转换成json
        String params = JSON.toJSONString(args);
        sysLog.setParams(params);

        sysLog.setCreateTime(new Date());
        //获取用户名
        //获取用户名
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            sysLog.setUserName(authentication.getName());
        }

//        sysLog.setUsername(ShiroUtils.getUserEntity().getUsername());
        //获取用户ip地址
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        sysLog.setIp(IpUtils.getIpAddr(request));

        //调用service保存SysLog实体类到数据库
        sysLogService.insert(sysLog);
    }

}