package by.prostrmk.model.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;


@Aspect
public class AspectLogger {

    private static final Logger logger = Logger.getLogger(AspectLogger.class);

    public void initMethod(){
        System.out.println("init method in aspect");
    }

    @Pointcut("within(by.prostrmk.controller..*)")
    public void allAvailableControllers(){
//        System.out.println("pointcut checked method");
    }

//
//    @AfterThrowing(pointcut = "allAvailableControllers()", throwing = "e")
//    public void logException(JoinPoint joinPoint, Exception e){
//        logger.error("Exception was in " + joinPoint.getTarget().getClass().getSimpleName() + ": " + e.getMessage());
//    }

}

