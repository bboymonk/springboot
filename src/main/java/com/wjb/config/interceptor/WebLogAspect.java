package com.wjb.config.interceptor;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Enumeration;

/** 实现Web层的日志切面
 * 
 */

@Aspect
@Component
public class WebLogAspect {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    ThreadLocal<Long> startTime = new ThreadLocal<Long>();

    /** 定义一个切入点. 解释下： ~ 第一个 * 代表任意修饰符及任意返回值. ~ 第二个 * 任意包名 ~ 第三个 * 代表任意方法. ~ 第四个 *
     * 定义在web包或者子包 ~ 第五个 * 任意方法 ~ .. 匹配任意数量的参数.
     */
    @Pointcut("execution(public * com.wjb.controller..*.*(..))")
    public void webLog() {
    }

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) {
        startTime.set(System.currentTimeMillis());
        // 接收到请求，记录请求内容
        logger.info("WebLogAspect.doBefore()");
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
            .getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 记录下请求内容
        logger.info("URL : " + request.getRequestURL().toString());
        logger.info("HTTP_METHOD : " + request.getMethod());
        logger.info("IP : " + request.getRemoteAddr());
        logger.info("CLASS_METHOD : "
            + joinPoint.getSignature().getDeclaringTypeName() + "."
            + joinPoint.getSignature().getName());
        logger.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));
        // 获取所有参数方法一：
        Enumeration<String> enu = request.getParameterNames();
        while (enu.hasMoreElements()) {
            String paraName = (String)enu.nextElement();
            System.out
                .println(paraName + ": " + request.getParameter(paraName));
        }
    }
    
//    @Around("controllerAspect()")
//    public Object around(ProceedingJoinPoint pjp) throws Throwable {
////        // 常见日志实体对象
////        Log log = new Log();
////        // 获取系统时间
////        log.setBeginTime(new Date());
//        // 开始时间
//        long start = System.currentTimeMillis();
//        // 拦截的实体类，就是当前正在执行的controller
//        Object target = pjp.getTarget();
//        // 拦截的方法名称。当前正在执行的方法
//        String methodName = pjp.getSignature().getName();
//        // 拦截的方法参数
//        // 拦截的参数类型
//        Signature sig = pjp.getSignature();
//        MethodSignature msig = null;
//        if (!(sig instanceof MethodSignature)) {
//            throw new IllegalArgumentException("该注解只能用于方法");
//        }
//        msig = (MethodSignature) sig;
//        Class[] parameterTypes = msig.getMethod().getParameterTypes();
//        // 返回的参数
//        Object object = null;
//        // 获得被拦截的方法
//        Method method = null;
//        try {
//            method = target.getClass().getMethod(methodName, parameterTypes);
//        } catch (NoSuchMethodException e1) {
//            e1.printStackTrace();
//        } catch (SecurityException e1) {
//            e1.printStackTrace();
//        }
//        // 获取请求Request
//        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
//                .getRequestAttributes()).getRequest();
//        // 获取请求参数
//        Object[] objects = pjp.getArgs();
////        // 参数不能为空
////        if(objects == null || objects.length < 1) {
////            throw new ParamsCheckException("参数");
////        }
////        ParamsDto parmsDto = (ParamsDto) objects[0];
////        this.parmsCheck(parmsDto);
//        object = pjp.proceed(); // 执行连接点方法
//        return ResResult.success(object);
////        logger.info("执行成功:业务系统ID {},请求目标{} ",parmsDto.getSysId(),method.getName());
//        // 获取执行方法的参数
//    }

    @AfterReturning("webLog()")
    public void doAfterReturning(JoinPoint joinPoint) {
        // 处理完请求，返回内容
        logger.info("WebLogAspect.doAfterReturning()");

        logger.info("耗时（毫秒） : "
            + (System.currentTimeMillis() - startTime.get()));
    }

}