package com.jiege.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Aspect
@Component
public class LogAspect {
    private final Logger logger=LoggerFactory.getLogger(this.getClass());
    @Pointcut("execution(* com.jiege.web.*.*(..))")
    public void log(){ }
    @Before("log()")
    public void doBefore(JoinPoint joinPoint){
        //通过Attributes获得requset
        ServletRequestAttributes attributes= (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request=attributes.getRequest();
        //通过request获得url和ip地址
        String url=request.getRequestURL().toString();
        System.out.println(url);
        String ip=request.getRemoteAddr().toString();
        //通过joinPoint获得方法所在的类的全限定类名和方法名，与方法的参数
        String classMethod=joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName();
        Object[] args=joinPoint.getArgs();
        RequsetLog requsetlog=new RequsetLog(url,ip,classMethod,args);
        //将信息输出到控制台与日志文件
        logger.info("Request,{}",requsetlog);
    }

    @After("log()")
    public void doAfter() {
        logger.info("----------doAfter------------");
    }
    @AfterReturning(returning = "result",pointcut = "log()")
    public void doAfterReturn(Object result){
        logger.info("result:{}"+result);
    }
    private class RequsetLog{
        private String url;
        private String ip;
        private String classMethod;
        private Object[] args;

        public RequsetLog(String url, String ip, String classMethod, Object[] args) {
            this.url = url;
            this.ip = ip;
            this.classMethod = classMethod;
            this.args = args;
        }

        @Override
        public String toString() {
            return "RequsetLog{" +
                    "url='" + url + '\'' +
                    ", ip='" + ip + '\'' +
                    ", classMethod='" + classMethod + '\'' +
                    ", args=" + Arrays.toString(args) +
                    '}';
        }
    }

}
