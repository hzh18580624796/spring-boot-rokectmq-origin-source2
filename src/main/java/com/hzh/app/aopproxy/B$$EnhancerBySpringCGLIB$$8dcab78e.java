
package com.hzh.app.aopproxy;

import java.lang.reflect.Method;
import java.lang.reflect.UndeclaredThrowableException;
import org.aopalliance.aop.Advice;
import org.springframework.aop.Advisor;
import org.springframework.aop.SpringProxy;
import org.springframework.aop.TargetClassAware;
import org.springframework.aop.TargetSource;
import org.springframework.aop.framework.Advised;
import org.springframework.aop.framework.AopConfigException;
import org.springframework.cglib.core.ReflectUtils;
import org.springframework.cglib.core.Signature;
import org.springframework.cglib.proxy.Callback;
import org.springframework.cglib.proxy.Dispatcher;
import org.springframework.cglib.proxy.Factory;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.cglib.proxy.NoOp;

public class B$$EnhancerBySpringCGLIB$$8dcab78e extends B implements SpringProxy, Advised, Factory {
    private boolean CGLIB$BOUND;
    public static Object CGLIB$FACTORY_DATA;
    private static  ThreadLocal CGLIB$THREAD_CALLBACKS;
    private static  Callback[] CGLIB$STATIC_CALLBACKS;
    private MethodInterceptor CGLIB$CALLBACK_0;
    private MethodInterceptor CGLIB$CALLBACK_1;
    private NoOp CGLIB$CALLBACK_2;
    private Dispatcher CGLIB$CALLBACK_3;
    private Dispatcher CGLIB$CALLBACK_4;
    private MethodInterceptor CGLIB$CALLBACK_5;
    private MethodInterceptor CGLIB$CALLBACK_6;
    private static Object CGLIB$CALLBACK_FILTER;
    private static  Method CGLIB$b1$0$Method;
    private static  MethodProxy CGLIB$b1$0$Proxy;
    private static  Object[] CGLIB$emptyArgs;
    private static  Method CGLIB$b2$1$Method;
    private static  MethodProxy CGLIB$b2$1$Proxy;
    private static  Method CGLIB$equals$2$Method;
    private static  MethodProxy CGLIB$equals$2$Proxy;
    private static  Method CGLIB$toString$3$Method;
    private static  MethodProxy CGLIB$toString$3$Proxy;
    private static  Method CGLIB$hashCode$4$Method;
    private static  MethodProxy CGLIB$hashCode$4$Proxy;
    private static  Method CGLIB$clone$5$Method;
    private static  MethodProxy CGLIB$clone$5$Proxy;

    static void CGLIB$STATICHOOK151() throws ClassNotFoundException {
        CGLIB$THREAD_CALLBACKS = new ThreadLocal();
        CGLIB$emptyArgs = new Object[0];
        Class<?> clazz = Class.forName("com.hzh.app.web.B$$EnhancerBySpringCGLIB$$8dcab78e");
        Class<?> clazz2 = Class.forName("java.lang.Object");
        Method[] methodArray = ReflectUtils.findMethods(new String[]{"equals", "(Ljava/lang/Object;)Z", "toString", "()Ljava/lang/String;", "hashCode", "()I", "clone", "()Ljava/lang/Object;"}, clazz2.getDeclaredMethods());
        CGLIB$equals$2$Method = methodArray[0];
        CGLIB$equals$2$Proxy = MethodProxy.create(clazz2, clazz, "(Ljava/lang/Object;)Z", "equals", "CGLIB$equals$2");
        CGLIB$toString$3$Method = methodArray[1];
        CGLIB$toString$3$Proxy = MethodProxy.create(clazz2, clazz, "()Ljava/lang/String;", "toString", "CGLIB$toString$3");
        CGLIB$hashCode$4$Method = methodArray[2];
        CGLIB$hashCode$4$Proxy = MethodProxy.create(clazz2, clazz, "()I", "hashCode", "CGLIB$hashCode$4");
        CGLIB$clone$5$Method = methodArray[3];
        CGLIB$clone$5$Proxy = MethodProxy.create(clazz2, clazz, "()Ljava/lang/Object;", "clone", "CGLIB$clone$5");
        clazz2 = Class.forName("com.hzh.app.aopproxy.B");
        Method[] methodArray2 = ReflectUtils.findMethods(new String[]{"b1", "()V", "b2", "()V"}, clazz2.getDeclaredMethods());
        CGLIB$b1$0$Method = methodArray2[0];
        CGLIB$b1$0$Proxy = MethodProxy.create(clazz2, clazz, "()V", "b1", "CGLIB$b1$0");
        CGLIB$b2$1$Method = methodArray2[1];
        CGLIB$b2$1$Proxy = MethodProxy.create(clazz2, clazz, "()V", "b2", "CGLIB$b2$1");
    }

     void CGLIB$b1$0() {
        super.b1();
    }

    public  void b1() {
        try {
            MethodInterceptor methodInterceptor = this.CGLIB$CALLBACK_0;
            if (methodInterceptor == null) {
                B$$EnhancerBySpringCGLIB$$8dcab78e.CGLIB$BIND_CALLBACKS(this);
                methodInterceptor = this.CGLIB$CALLBACK_0;
            }
            if (methodInterceptor != null) {
                Object object = methodInterceptor.intercept(this, CGLIB$b1$0$Method, CGLIB$emptyArgs, CGLIB$b1$0$Proxy);
                return;
            }
            super.b1();
            return;
        }
        catch (Error | RuntimeException throwable) {
            throw throwable;
        }
        catch (Throwable throwable) {
            throw new UndeclaredThrowableException(throwable);
        }
    }

     void CGLIB$b2$1() {
        super.b2();
    }

    public  void b2() {
        try {
            System.out.println("ccc");
            MethodInterceptor methodInterceptor = this.CGLIB$CALLBACK_0;
            if (methodInterceptor == null) {
                B$$EnhancerBySpringCGLIB$$8dcab78e.CGLIB$BIND_CALLBACKS(this);
                methodInterceptor = this.CGLIB$CALLBACK_0;
            }
            if (methodInterceptor != null) {
                Object object = methodInterceptor.intercept(this, CGLIB$b2$1$Method, CGLIB$emptyArgs, CGLIB$b2$1$Proxy);
                return;
            }
            super.b2();
            return;
        }
        catch (Error | RuntimeException throwable) {
            throw throwable;
        }
        catch (Throwable throwable) {
            throw new UndeclaredThrowableException(throwable);
        }
    }

     boolean CGLIB$equals$2(Object object) {
        return super.equals(object);
    }

    public  boolean equals(Object object) {
        try {
            MethodInterceptor methodInterceptor = this.CGLIB$CALLBACK_5;
            if (methodInterceptor == null) {
                B$$EnhancerBySpringCGLIB$$8dcab78e.CGLIB$BIND_CALLBACKS(this);
                methodInterceptor = this.CGLIB$CALLBACK_5;
            }
            if (methodInterceptor != null) {
                Object object2 = methodInterceptor.intercept(this, CGLIB$equals$2$Method, new Object[]{object}, CGLIB$equals$2$Proxy);
                return object2 == null ? false : (Boolean)object2;
            }
            return super.equals(object);
        }
        catch (Error | RuntimeException throwable) {
            throw throwable;
        }
        catch (Throwable throwable) {
            throw new UndeclaredThrowableException(throwable);
        }
    }

     String CGLIB$toString$3() {
        return super.toString();
    }

    public  String toString() {
        try {
            MethodInterceptor methodInterceptor = this.CGLIB$CALLBACK_0;
            if (methodInterceptor == null) {
                B$$EnhancerBySpringCGLIB$$8dcab78e.CGLIB$BIND_CALLBACKS(this);
                methodInterceptor = this.CGLIB$CALLBACK_0;
            }
            if (methodInterceptor != null) {
                return (String)methodInterceptor.intercept(this, CGLIB$toString$3$Method, CGLIB$emptyArgs, CGLIB$toString$3$Proxy);
            }
            return super.toString();
        }
        catch (Error | RuntimeException throwable) {
            throw throwable;
        }
        catch (Throwable throwable) {
            throw new UndeclaredThrowableException(throwable);
        }
    }

     int CGLIB$hashCode$4() {
        return super.hashCode();
    }

    public  int hashCode() {
        try {
            MethodInterceptor methodInterceptor = this.CGLIB$CALLBACK_6;
            if (methodInterceptor == null) {
                B$$EnhancerBySpringCGLIB$$8dcab78e.CGLIB$BIND_CALLBACKS(this);
                methodInterceptor = this.CGLIB$CALLBACK_6;
            }
            if (methodInterceptor != null) {
                Object object = methodInterceptor.intercept(this, CGLIB$hashCode$4$Method, CGLIB$emptyArgs, CGLIB$hashCode$4$Proxy);
                return object == null ? 0 : ((Number)object).intValue();
            }
            return super.hashCode();
        }
        catch (Error | RuntimeException throwable) {
            throw throwable;
        }
        catch (Throwable throwable) {
            throw new UndeclaredThrowableException(throwable);
        }
    }

     Object CGLIB$clone$5() throws CloneNotSupportedException {
        return super.clone();
    }

    protected  Object clone() throws CloneNotSupportedException {
        try {
            MethodInterceptor methodInterceptor = this.CGLIB$CALLBACK_0;
            if (methodInterceptor == null) {
                B$$EnhancerBySpringCGLIB$$8dcab78e.CGLIB$BIND_CALLBACKS(this);
                methodInterceptor = this.CGLIB$CALLBACK_0;
            }
            if (methodInterceptor != null) {
                return methodInterceptor.intercept(this, CGLIB$clone$5$Method, CGLIB$emptyArgs, CGLIB$clone$5$Proxy);
            }
            return super.clone();
        }
        catch (CloneNotSupportedException | Error | RuntimeException throwable) {
            throw throwable;
        }
        catch (Throwable throwable) {
            throw new UndeclaredThrowableException(throwable);
        }
    }

    public static MethodProxy CGLIB$findMethodProxy(Signature signature) {
        String string = ((Object)signature).toString();
        switch (string.hashCode()) {
            case -508378822: {
                if (!string.equals("clone()Ljava/lang/Object;")) break;
                return CGLIB$clone$5$Proxy;
            }
            case 92004614: {
                if (!string.equals("b1()V")) break;
                return CGLIB$b1$0$Proxy;
            }
            case 92034405: {
                if (!string.equals("b2()V")) break;
                return CGLIB$b2$1$Proxy;
            }
            case 1826985398: {
                if (!string.equals("equals(Ljava/lang/Object;)Z")) break;
                return CGLIB$equals$2$Proxy;
            }
            case 1913648695: {
                if (!string.equals("toString()Ljava/lang/String;")) break;
                return CGLIB$toString$3$Proxy;
            }
            case 1984935277: {
                if (!string.equals("hashCode()I")) break;
                return CGLIB$hashCode$4$Proxy;
            }
        }
        return null;
    }

    public  int indexOf(Advice advice) {
        try {
            Dispatcher dispatcher = this.CGLIB$CALLBACK_4;
            if (dispatcher == null) {
                B$$EnhancerBySpringCGLIB$$8dcab78e.CGLIB$BIND_CALLBACKS(this);
                dispatcher = this.CGLIB$CALLBACK_4;
            }
            return ((Advised)dispatcher.loadObject()).indexOf(advice);
        }
        catch (Error | RuntimeException throwable) {
            throw throwable;
        }
        catch (Throwable throwable) {
            throw new UndeclaredThrowableException(throwable);
        }
    }

    public  int indexOf(Advisor advisor) {
        try {
            Dispatcher dispatcher = this.CGLIB$CALLBACK_4;
            if (dispatcher == null) {
                B$$EnhancerBySpringCGLIB$$8dcab78e.CGLIB$BIND_CALLBACKS(this);
                dispatcher = this.CGLIB$CALLBACK_4;
            }
            return ((Advised)dispatcher.loadObject()).indexOf(advisor);
        }
        catch (Error | RuntimeException throwable) {
            throw throwable;
        }
        catch (Throwable throwable) {
            throw new UndeclaredThrowableException(throwable);
        }
    }

    public  boolean isFrozen() {
        try {
            Dispatcher dispatcher = this.CGLIB$CALLBACK_4;
            if (dispatcher == null) {
                B$$EnhancerBySpringCGLIB$$8dcab78e.CGLIB$BIND_CALLBACKS(this);
                dispatcher = this.CGLIB$CALLBACK_4;
            }
            return ((Advised)dispatcher.loadObject()).isFrozen();
        }
        catch (Error | RuntimeException throwable) {
            throw throwable;
        }
        catch (Throwable throwable) {
            throw new UndeclaredThrowableException(throwable);
        }
    }

    public  boolean isProxyTargetClass() {
        try {
            Dispatcher dispatcher = this.CGLIB$CALLBACK_4;
            if (dispatcher == null) {
                B$$EnhancerBySpringCGLIB$$8dcab78e.CGLIB$BIND_CALLBACKS(this);
                dispatcher = this.CGLIB$CALLBACK_4;
            }
            return ((Advised)dispatcher.loadObject()).isProxyTargetClass();
        }
        catch (Error | RuntimeException throwable) {
            throw throwable;
        }
        catch (Throwable throwable) {
            throw new UndeclaredThrowableException(throwable);
        }
    }

    public  void setTargetSource(TargetSource targetSource) {
        try {
            Dispatcher dispatcher = this.CGLIB$CALLBACK_4;
            if (dispatcher == null) {
                B$$EnhancerBySpringCGLIB$$8dcab78e.CGLIB$BIND_CALLBACKS(this);
                dispatcher = this.CGLIB$CALLBACK_4;
            }
            ((Advised)dispatcher.loadObject()).setTargetSource(targetSource);
            return;
        }
        catch (Error | RuntimeException throwable) {
            throw throwable;
        }
        catch (Throwable throwable) {
            throw new UndeclaredThrowableException(throwable);
        }
    }

    public  void setExposeProxy(boolean bl) {
        try {
            Dispatcher dispatcher = this.CGLIB$CALLBACK_4;
            if (dispatcher == null) {
                B$$EnhancerBySpringCGLIB$$8dcab78e.CGLIB$BIND_CALLBACKS(this);
                dispatcher = this.CGLIB$CALLBACK_4;
            }
            ((Advised)dispatcher.loadObject()).setExposeProxy(bl);
            return;
        }
        catch (Error | RuntimeException throwable) {
            throw throwable;
        }
        catch (Throwable throwable) {
            throw new UndeclaredThrowableException(throwable);
        }
    }

    public  boolean isExposeProxy() {
        try {
            Dispatcher dispatcher = this.CGLIB$CALLBACK_4;
            if (dispatcher == null) {
                B$$EnhancerBySpringCGLIB$$8dcab78e.CGLIB$BIND_CALLBACKS(this);
                dispatcher = this.CGLIB$CALLBACK_4;
            }
            return ((Advised)dispatcher.loadObject()).isExposeProxy();
        }
        catch (Error | RuntimeException throwable) {
            throw throwable;
        }
        catch (Throwable throwable) {
            throw new UndeclaredThrowableException(throwable);
        }
    }

    public  void setPreFiltered(boolean bl) {
        try {
            Dispatcher dispatcher = this.CGLIB$CALLBACK_4;
            if (dispatcher == null) {
                B$$EnhancerBySpringCGLIB$$8dcab78e.CGLIB$BIND_CALLBACKS(this);
                dispatcher = this.CGLIB$CALLBACK_4;
            }
            ((Advised)dispatcher.loadObject()).setPreFiltered(bl);
            return;
        }
        catch (Error | RuntimeException throwable) {
            throw throwable;
        }
        catch (Throwable throwable) {
            throw new UndeclaredThrowableException(throwable);
        }
    }

    public  boolean isPreFiltered() {
        try {
            Dispatcher dispatcher = this.CGLIB$CALLBACK_4;
            if (dispatcher == null) {
                B$$EnhancerBySpringCGLIB$$8dcab78e.CGLIB$BIND_CALLBACKS(this);
                dispatcher = this.CGLIB$CALLBACK_4;
            }
            return ((Advised)dispatcher.loadObject()).isPreFiltered();
        }
        catch (Error | RuntimeException throwable) {
            throw throwable;
        }
        catch (Throwable throwable) {
            throw new UndeclaredThrowableException(throwable);
        }
    }

    public  void addAdvisor(Advisor advisor) throws AopConfigException {
        try {
            Dispatcher dispatcher = this.CGLIB$CALLBACK_4;
            if (dispatcher == null) {
                B$$EnhancerBySpringCGLIB$$8dcab78e.CGLIB$BIND_CALLBACKS(this);
                dispatcher = this.CGLIB$CALLBACK_4;
            }
            ((Advised)dispatcher.loadObject()).addAdvisor(advisor);
            return;
        }
        catch (Error | RuntimeException throwable) {
            throw throwable;
        }
        catch (Throwable throwable) {
            throw new UndeclaredThrowableException(throwable);
        }
    }

    public  void addAdvisor(int n, Advisor advisor) throws AopConfigException {
        try {
            Dispatcher dispatcher = this.CGLIB$CALLBACK_4;
            if (dispatcher == null) {
                B$$EnhancerBySpringCGLIB$$8dcab78e.CGLIB$BIND_CALLBACKS(this);
                dispatcher = this.CGLIB$CALLBACK_4;
            }
            ((Advised)dispatcher.loadObject()).addAdvisor(n, advisor);
            return;
        }
        catch (Error | RuntimeException throwable) {
            throw throwable;
        }
        catch (Throwable throwable) {
            throw new UndeclaredThrowableException(throwable);
        }
    }

    public  void removeAdvisor(int n) throws AopConfigException {
        try {
            Dispatcher dispatcher = this.CGLIB$CALLBACK_4;
            if (dispatcher == null) {
                B$$EnhancerBySpringCGLIB$$8dcab78e.CGLIB$BIND_CALLBACKS(this);
                dispatcher = this.CGLIB$CALLBACK_4;
            }
            ((Advised)dispatcher.loadObject()).removeAdvisor(n);
            return;
        }
        catch (Error | RuntimeException throwable) {
            throw throwable;
        }
        catch (Throwable throwable) {
            throw new UndeclaredThrowableException(throwable);
        }
    }

    public  boolean removeAdvisor(Advisor advisor) {
        try {
            Dispatcher dispatcher = this.CGLIB$CALLBACK_4;
            if (dispatcher == null) {
                B$$EnhancerBySpringCGLIB$$8dcab78e.CGLIB$BIND_CALLBACKS(this);
                dispatcher = this.CGLIB$CALLBACK_4;
            }
            return ((Advised)dispatcher.loadObject()).removeAdvisor(advisor);
        }
        catch (Error | RuntimeException throwable) {
            throw throwable;
        }
        catch (Throwable throwable) {
            throw new UndeclaredThrowableException(throwable);
        }
    }

    public  boolean replaceAdvisor(Advisor advisor, Advisor advisor2) throws AopConfigException {
        try {
            Dispatcher dispatcher = this.CGLIB$CALLBACK_4;
            if (dispatcher == null) {
                B$$EnhancerBySpringCGLIB$$8dcab78e.CGLIB$BIND_CALLBACKS(this);
                dispatcher = this.CGLIB$CALLBACK_4;
            }
            return ((Advised)dispatcher.loadObject()).replaceAdvisor(advisor, advisor2);
        }
        catch (Error | RuntimeException throwable) {
            throw throwable;
        }
        catch (Throwable throwable) {
            throw new UndeclaredThrowableException(throwable);
        }
    }

    public  void addAdvice(int n, Advice advice) throws AopConfigException {
        try {
            Dispatcher dispatcher = this.CGLIB$CALLBACK_4;
            if (dispatcher == null) {
                B$$EnhancerBySpringCGLIB$$8dcab78e.CGLIB$BIND_CALLBACKS(this);
                dispatcher = this.CGLIB$CALLBACK_4;
            }
            ((Advised)dispatcher.loadObject()).addAdvice(n, advice);
            return;
        }
        catch (Error | RuntimeException throwable) {
            throw throwable;
        }
        catch (Throwable throwable) {
            throw new UndeclaredThrowableException(throwable);
        }
    }

    public  void addAdvice(Advice advice) throws AopConfigException {
        try {
            Dispatcher dispatcher = this.CGLIB$CALLBACK_4;
            if (dispatcher == null) {
                B$$EnhancerBySpringCGLIB$$8dcab78e.CGLIB$BIND_CALLBACKS(this);
                dispatcher = this.CGLIB$CALLBACK_4;
            }
            ((Advised)dispatcher.loadObject()).addAdvice(advice);
            return;
        }
        catch (Error | RuntimeException throwable) {
            throw throwable;
        }
        catch (Throwable throwable) {
            throw new UndeclaredThrowableException(throwable);
        }
    }

    public  boolean removeAdvice(Advice advice) {
        try {
            Dispatcher dispatcher = this.CGLIB$CALLBACK_4;
            if (dispatcher == null) {
                B$$EnhancerBySpringCGLIB$$8dcab78e.CGLIB$BIND_CALLBACKS(this);
                dispatcher = this.CGLIB$CALLBACK_4;
            }
            return ((Advised)dispatcher.loadObject()).removeAdvice(advice);
        }
        catch (Error | RuntimeException throwable) {
            throw throwable;
        }
        catch (Throwable throwable) {
            throw new UndeclaredThrowableException(throwable);
        }
    }

    public  String toProxyConfigString() {
        try {
            Dispatcher dispatcher = this.CGLIB$CALLBACK_4;
            if (dispatcher == null) {
                B$$EnhancerBySpringCGLIB$$8dcab78e.CGLIB$BIND_CALLBACKS(this);
                dispatcher = this.CGLIB$CALLBACK_4;
            }
            return ((Advised)dispatcher.loadObject()).toProxyConfigString();
        }
        catch (Error | RuntimeException throwable) {
            throw throwable;
        }
        catch (Throwable throwable) {
            throw new UndeclaredThrowableException(throwable);
        }
    }

    public  TargetSource getTargetSource() {
        try {
            Dispatcher dispatcher = this.CGLIB$CALLBACK_4;
            if (dispatcher == null) {
                B$$EnhancerBySpringCGLIB$$8dcab78e.CGLIB$BIND_CALLBACKS(this);
                dispatcher = this.CGLIB$CALLBACK_4;
            }
            return ((Advised)dispatcher.loadObject()).getTargetSource();
        }
        catch (Error | RuntimeException throwable) {
            throw throwable;
        }
        catch (Throwable throwable) {
            throw new UndeclaredThrowableException(throwable);
        }
    }

    public  Class[] getProxiedInterfaces() {
        try {
            Dispatcher dispatcher = this.CGLIB$CALLBACK_4;
            if (dispatcher == null) {
                B$$EnhancerBySpringCGLIB$$8dcab78e.CGLIB$BIND_CALLBACKS(this);
                dispatcher = this.CGLIB$CALLBACK_4;
            }
            return ((Advised)dispatcher.loadObject()).getProxiedInterfaces();
        }
        catch (Error | RuntimeException throwable) {
            throw throwable;
        }
        catch (Throwable throwable) {
            throw new UndeclaredThrowableException(throwable);
        }
    }

    public  boolean isInterfaceProxied(Class clazz) {
        try {
            Dispatcher dispatcher = this.CGLIB$CALLBACK_4;
            if (dispatcher == null) {
                B$$EnhancerBySpringCGLIB$$8dcab78e.CGLIB$BIND_CALLBACKS(this);
                dispatcher = this.CGLIB$CALLBACK_4;
            }
            return ((Advised)dispatcher.loadObject()).isInterfaceProxied(clazz);
        }
        catch (Error | RuntimeException throwable) {
            throw throwable;
        }
        catch (Throwable throwable) {
            throw new UndeclaredThrowableException(throwable);
        }
    }

    public  Advisor[] getAdvisors() {
        try {
            Dispatcher dispatcher = this.CGLIB$CALLBACK_4;
            if (dispatcher == null) {
                B$$EnhancerBySpringCGLIB$$8dcab78e.CGLIB$BIND_CALLBACKS(this);
                dispatcher = this.CGLIB$CALLBACK_4;
            }
            return ((Advised)dispatcher.loadObject()).getAdvisors();
        }
        catch (Error | RuntimeException throwable) {
            throw throwable;
        }
        catch (Throwable throwable) {
            throw new UndeclaredThrowableException(throwable);
        }
    }

    public  Class getTargetClass() {
        try {
            Dispatcher dispatcher = this.CGLIB$CALLBACK_4;
            if (dispatcher == null) {
                B$$EnhancerBySpringCGLIB$$8dcab78e.CGLIB$BIND_CALLBACKS(this);
                dispatcher = this.CGLIB$CALLBACK_4;
            }
            return ((TargetClassAware)dispatcher.loadObject()).getTargetClass();
        }
        catch (Error | RuntimeException throwable) {
            throw throwable;
        }
        catch (Throwable throwable) {
            throw new UndeclaredThrowableException(throwable);
        }
    }

    public B$$EnhancerBySpringCGLIB$$8dcab78e() {
        try {
            B$$EnhancerBySpringCGLIB$$8dcab78e b$$EnhancerBySpringCGLIB$$8dcab78e = this;
            B$$EnhancerBySpringCGLIB$$8dcab78e.CGLIB$BIND_CALLBACKS(b$$EnhancerBySpringCGLIB$$8dcab78e);
            return;
        }
        catch (Error | RuntimeException throwable) {
            throw throwable;
        }
        catch (Throwable throwable) {
            throw new UndeclaredThrowableException(throwable);
        }
    }

    public static void CGLIB$SET_THREAD_CALLBACKS(Callback[] callbackArray) {
        CGLIB$THREAD_CALLBACKS.set(callbackArray);
    }

    public static void CGLIB$SET_STATIC_CALLBACKS(Callback[] callbackArray) {
        CGLIB$STATIC_CALLBACKS = callbackArray;
    }

    private static  void CGLIB$BIND_CALLBACKS(Object object) {
        block2: {
            Object object2;
            B$$EnhancerBySpringCGLIB$$8dcab78e b$$EnhancerBySpringCGLIB$$8dcab78e;
            block3: {
                b$$EnhancerBySpringCGLIB$$8dcab78e = (B$$EnhancerBySpringCGLIB$$8dcab78e)object;
                if (b$$EnhancerBySpringCGLIB$$8dcab78e.CGLIB$BOUND) break block2;
                b$$EnhancerBySpringCGLIB$$8dcab78e.CGLIB$BOUND = true;
                object2 = CGLIB$THREAD_CALLBACKS.get();
                if (object2 != null) break block3;
                object2 = CGLIB$STATIC_CALLBACKS;
                if (CGLIB$STATIC_CALLBACKS == null) break block2;
            }
            Callback[] callbackArray = (Callback[])object2;
            B$$EnhancerBySpringCGLIB$$8dcab78e b$$EnhancerBySpringCGLIB$$8dcab78e2 = b$$EnhancerBySpringCGLIB$$8dcab78e;
            b$$EnhancerBySpringCGLIB$$8dcab78e2.CGLIB$CALLBACK_6 = (MethodInterceptor)callbackArray[6];
            b$$EnhancerBySpringCGLIB$$8dcab78e2.CGLIB$CALLBACK_5 = (MethodInterceptor)callbackArray[5];
            b$$EnhancerBySpringCGLIB$$8dcab78e2.CGLIB$CALLBACK_4 = (Dispatcher)callbackArray[4];
            b$$EnhancerBySpringCGLIB$$8dcab78e2.CGLIB$CALLBACK_3 = (Dispatcher)callbackArray[3];
            b$$EnhancerBySpringCGLIB$$8dcab78e2.CGLIB$CALLBACK_2 = (NoOp)callbackArray[2];
            b$$EnhancerBySpringCGLIB$$8dcab78e2.CGLIB$CALLBACK_1 = (MethodInterceptor)callbackArray[1];
            b$$EnhancerBySpringCGLIB$$8dcab78e2.CGLIB$CALLBACK_0 = (MethodInterceptor)callbackArray[0];
        }
    }

    public Object newInstance(Callback[] callbackArray) {
        try {
            B$$EnhancerBySpringCGLIB$$8dcab78e.CGLIB$SET_THREAD_CALLBACKS(callbackArray);
            B$$EnhancerBySpringCGLIB$$8dcab78e b$$EnhancerBySpringCGLIB$$8dcab78e = new B$$EnhancerBySpringCGLIB$$8dcab78e();
            B$$EnhancerBySpringCGLIB$$8dcab78e.CGLIB$SET_THREAD_CALLBACKS(null);
            return b$$EnhancerBySpringCGLIB$$8dcab78e;
        }
        catch (Error | RuntimeException throwable) {
            throw throwable;
        }
        catch (Throwable throwable) {
            throw new UndeclaredThrowableException(throwable);
        }
    }

    public Object newInstance(Callback callback) {
        try {
            throw new IllegalStateException("More than one callback object required");
        }
        catch (Error | RuntimeException throwable) {
            throw throwable;
        }
        catch (Throwable throwable) {
            throw new UndeclaredThrowableException(throwable);
        }
    }

    public Object newInstance(Class[] classArray, Object[] objectArray, Callback[] callbackArray) {
        try {
            B$$EnhancerBySpringCGLIB$$8dcab78e b$$EnhancerBySpringCGLIB$$8dcab78e;
            B$$EnhancerBySpringCGLIB$$8dcab78e.CGLIB$SET_THREAD_CALLBACKS(callbackArray);
            Class[] classArray2 = classArray;
            switch (classArray.length) {
                case 0: {
                    b$$EnhancerBySpringCGLIB$$8dcab78e = new B$$EnhancerBySpringCGLIB$$8dcab78e();
                    break;
                }
                default: {
                    throw new IllegalArgumentException("Constructor not found");
                }
            }
            B$$EnhancerBySpringCGLIB$$8dcab78e.CGLIB$SET_THREAD_CALLBACKS(null);
            return b$$EnhancerBySpringCGLIB$$8dcab78e;
        }
        catch (Error | RuntimeException throwable) {
            throw throwable;
        }
        catch (Throwable throwable) {
            throw new UndeclaredThrowableException(throwable);
        }
    }

    public Callback getCallback(int n) {
        try {
            Callback callback;
            B$$EnhancerBySpringCGLIB$$8dcab78e.CGLIB$BIND_CALLBACKS(this);
            B$$EnhancerBySpringCGLIB$$8dcab78e b$$EnhancerBySpringCGLIB$$8dcab78e = this;
            switch (n) {
                case 0: {
                    callback = b$$EnhancerBySpringCGLIB$$8dcab78e.CGLIB$CALLBACK_0;
                    break;
                }
                case 1: {
                    callback = b$$EnhancerBySpringCGLIB$$8dcab78e.CGLIB$CALLBACK_1;
                    break;
                }
                case 2: {
                    callback = b$$EnhancerBySpringCGLIB$$8dcab78e.CGLIB$CALLBACK_2;
                    break;
                }
                case 3: {
                    callback = b$$EnhancerBySpringCGLIB$$8dcab78e.CGLIB$CALLBACK_3;
                    break;
                }
                case 4: {
                    callback = b$$EnhancerBySpringCGLIB$$8dcab78e.CGLIB$CALLBACK_4;
                    break;
                }
                case 5: {
                    callback = b$$EnhancerBySpringCGLIB$$8dcab78e.CGLIB$CALLBACK_5;
                    break;
                }
                case 6: {
                    callback = b$$EnhancerBySpringCGLIB$$8dcab78e.CGLIB$CALLBACK_6;
                    break;
                }
                default: {
                    callback = null;
                }
            }
            return callback;
        }
        catch (Error | RuntimeException throwable) {
            throw throwable;
        }
        catch (Throwable throwable) {
            throw new UndeclaredThrowableException(throwable);
        }
    }

    public void setCallback(int n, Callback callback) {
        try {
            switch (n) {
                case 0: {
                    this.CGLIB$CALLBACK_0 = (MethodInterceptor)callback;
                    break;
                }
                case 1: {
                    this.CGLIB$CALLBACK_1 = (MethodInterceptor)callback;
                    break;
                }
                case 2: {
                    this.CGLIB$CALLBACK_2 = (NoOp)callback;
                    break;
                }
                case 3: {
                    this.CGLIB$CALLBACK_3 = (Dispatcher)callback;
                    break;
                }
                case 4: {
                    this.CGLIB$CALLBACK_4 = (Dispatcher)callback;
                    break;
                }
                case 5: {
                    this.CGLIB$CALLBACK_5 = (MethodInterceptor)callback;
                    break;
                }
                case 6: {
                    this.CGLIB$CALLBACK_6 = (MethodInterceptor)callback;
                    break;
                }
            }
            return;
        }
        catch (Error | RuntimeException throwable) {
            throw throwable;
        }
        catch (Throwable throwable) {
            throw new UndeclaredThrowableException(throwable);
        }
    }

    public Callback[] getCallbacks() {
        try {
            B$$EnhancerBySpringCGLIB$$8dcab78e.CGLIB$BIND_CALLBACKS(this);
            B$$EnhancerBySpringCGLIB$$8dcab78e b$$EnhancerBySpringCGLIB$$8dcab78e = this;
            return new Callback[]{this.CGLIB$CALLBACK_0, this.CGLIB$CALLBACK_1, this.CGLIB$CALLBACK_2, this.CGLIB$CALLBACK_3, this.CGLIB$CALLBACK_4, this.CGLIB$CALLBACK_5, this.CGLIB$CALLBACK_6};
        }
        catch (Error | RuntimeException throwable) {
            throw throwable;
        }
        catch (Throwable throwable) {
            throw new UndeclaredThrowableException(throwable);
        }
    }

    public void setCallbacks(Callback[] callbackArray) {
        try {
            this.CGLIB$CALLBACK_0 = (MethodInterceptor)callbackArray[0];
            this.CGLIB$CALLBACK_1 = (MethodInterceptor)callbackArray[1];
            this.CGLIB$CALLBACK_2 = (NoOp)callbackArray[2];
            this.CGLIB$CALLBACK_3 = (Dispatcher)callbackArray[3];
            this.CGLIB$CALLBACK_4 = (Dispatcher)callbackArray[4];
            this.CGLIB$CALLBACK_5 = (MethodInterceptor)callbackArray[5];
            Callback[] callbackArray2 = callbackArray;
            B$$EnhancerBySpringCGLIB$$8dcab78e b$$EnhancerBySpringCGLIB$$8dcab78e = this;
            this.CGLIB$CALLBACK_6 = (MethodInterceptor)callbackArray[6];
            return;
        }
        catch (Error | RuntimeException throwable) {
            throw throwable;
        }
        catch (Throwable throwable) {
            throw new UndeclaredThrowableException(throwable);
        }
    }

    static {
        B$$EnhancerBySpringCGLIB$$8dcab78e.CGLIB$STATICHOOK152();
        try {
            B$$EnhancerBySpringCGLIB$$8dcab78e.CGLIB$STATICHOOK151();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    static void CGLIB$STATICHOOK152() {
        try {
            return;
        }
        catch (Error | RuntimeException throwable) {
            throw throwable;
        }
        catch (Throwable throwable) {
            throw new UndeclaredThrowableException(throwable);
        }
    }
}

