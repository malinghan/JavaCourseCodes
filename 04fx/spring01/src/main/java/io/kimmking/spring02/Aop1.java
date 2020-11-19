package io.kimmking.spring02;

import org.aspectj.lang.ProceedingJoinPoint;


/**
 * Aop1使用注解的方式进行织入
 *
 *     <aop:config>
 *         <aop:pointcut expression="execution(* io.kimmking.aop.*.*(..))" id="p1" /> <!--切点-->
 *         <aop:aspect ref="aop1"> <!--切面 -->
 *             <aop:around method="around" pointcut-ref="p1" />
 *             <aop:before method="startTransaction" pointcut-ref="p1" /> <!--前置通知-->
 *             <aop:after-returning method="commitTransaction" pointcut-ref="p1"/> <!--后置通知-->
 *         </aop:aspect>
 *     </aop:config>
 *
 *     around: print
 *     startTransaction: 方法前-before
 *     commitTransaction 方法后-after-returning
 */
public class Aop1 {
    
        //前置通知
        public void startTransaction(){
            System.out.println("    ====>begin ding... ");
        }
        
        //后置通知
        public void commitTransaction(){
            System.out.println("    ====>finish ding... ");
        }
        
        //环绕通知
        public void around(ProceedingJoinPoint joinPoint) throws Throwable{
            System.out.println("    ====>around begin ding");
            //调用process()方法才会真正的执行实际被代理的方法
            joinPoint.proceed();
            
            System.out.println("    ====>around finish ding");
        }
        
}
