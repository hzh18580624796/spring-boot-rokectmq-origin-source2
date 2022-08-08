//package com.hzh.app.web;
//
//import com.hzh.app.web.B;
//import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.UndeclaredThrowableException;
//import org.springframework.cglib.core.Signature;
//import org.springframework.cglib.reflect.FastClass;
//
//public class B$$FastClassBySpringCGLIB$$e49bc216
//extends FastClass {
//    public B$$FastClassBySpringCGLIB$$e49bc216(Class clazz) {
//        try {
//            super(clazz);
//            return;
//        }
//        catch (Error | RuntimeException throwable) {
//            throw throwable;
//        }
//        catch (Throwable throwable) {
//            throw new UndeclaredThrowableException(throwable);
//        }
//    }
//
//    public int getIndex(Signature signature) {
//        try {
//            String string = ((Object)signature).toString();
//            switch (string.hashCode()) {
//                case 92004614: {
//                    if (!string.equals("b1()V")) break;
//                    return 0;
//                }
//                case 92034405: {
//                    if (!string.equals("b2()V")) break;
//                    return 1;
//                }
//                case 1826985398: {
//                    if (!string.equals("equals(Ljava/lang/Object;)Z")) break;
//                    return 2;
//                }
//                case 1913648695: {
//                    if (!string.equals("toString()Ljava/lang/String;")) break;
//                    return 3;
//                }
//                case 1984935277: {
//                    if (!string.equals("hashCode()I")) break;
//                    return 4;
//                }
//            }
//            return -1;
//        }
//        catch (Error | RuntimeException throwable) {
//            throw throwable;
//        }
//        catch (Throwable throwable) {
//            throw new UndeclaredThrowableException(throwable);
//        }
//    }
//
//    public int getIndex(String string, Class[] classArray) {
//        try {
//            String string2 = string;
//            Class[] classArray2 = classArray;
//            block1 : switch (string2.hashCode()) {
//                case -1776922004: {
//                    if (!string2.equals("toString")) break;
//                    classArray2 = classArray2;
//                    switch (classArray2.length) {
//                        case 0: {
//                            return 3;
//                        }
//                    }
//                    break;
//                }
//                case -1295482945: {
//                    if (!string2.equals("equals")) break;
//                    classArray2 = classArray2;
//                    switch (classArray2.length) {
//                        case 1: {
//                            classArray2 = classArray2;
//                            if (!classArray2[0].getName().equals("java.lang.Object")) break block1;
//                            return 2;
//                        }
//                    }
//                    break;
//                }
//                case 3087: {
//                    if (!string2.equals("b1")) break;
//                    classArray2 = classArray2;
//                    switch (classArray2.length) {
//                        case 0: {
//                            return 0;
//                        }
//                    }
//                    break;
//                }
//                case 3088: {
//                    if (!string2.equals("b2")) break;
//                    classArray2 = classArray2;
//                    switch (classArray2.length) {
//                        case 0: {
//                            return 1;
//                        }
//                    }
//                    break;
//                }
//                case 147696667: {
//                    if (!string2.equals("hashCode")) break;
//                    classArray2 = classArray2;
//                    switch (classArray2.length) {
//                        case 0: {
//                            return 4;
//                        }
//                    }
//                    break;
//                }
//            }
//            return -1;
//        }
//        catch (Error | RuntimeException throwable) {
//            throw throwable;
//        }
//        catch (Throwable throwable) {
//            throw new UndeclaredThrowableException(throwable);
//        }
//    }
//
//    public int getIndex(Class[] classArray) {
//        try {
//            Class[] classArray2 = classArray;
//            switch (classArray.length) {
//                case 0: {
//                    return 0;
//                }
//            }
//            return -1;
//        }
//        catch (Error | RuntimeException throwable) {
//            throw throwable;
//        }
//        catch (Throwable throwable) {
//            throw new UndeclaredThrowableException(throwable);
//        }
//    }
//
//    public Object invoke(int n, Object object, Object[] objectArray) throws InvocationTargetException {
//        try {
//            B b = (B)object;
//            try {
//                switch (n) {
//                    case 0: {
//                        b.b1();
//                        return null;
//                    }
//                    case 1: {
//                        b.b2();
//                        return null;
//                    }
//                    case 2: {
//                        return new Boolean(b.equals(objectArray[0]));
//                    }
//                    case 3: {
//                        return b.toString();
//                    }
//                    case 4: {
//                        return new Integer(b.hashCode());
//                    }
//                }
//            }
//            catch (Throwable throwable) {
//                throw new InvocationTargetException(throwable);
//            }
//            throw new IllegalArgumentException("Cannot find matching method/constructor");
//        }
//        catch (Error | RuntimeException | InvocationTargetException throwable) {
//            throw throwable;
//        }
//        catch (Throwable throwable) {
//            throw new UndeclaredThrowableException(throwable);
//        }
//    }
//
//    public Object newInstance(int n, Object[] objectArray) throws InvocationTargetException {
//        try {
//            try {
//                switch (n) {
//                    case 0: {
//                        return new B();
//                    }
//                }
//            }
//            catch (Throwable throwable) {
//                throw new InvocationTargetException(throwable);
//            }
//            throw new IllegalArgumentException("Cannot find matching method/constructor");
//        }
//        catch (Error | RuntimeException | InvocationTargetException throwable) {
//            throw throwable;
//        }
//        catch (Throwable throwable) {
//            throw new UndeclaredThrowableException(throwable);
//        }
//    }
//
//    public int getMaxIndex() {
//        try {
//            return 4;
//        }
//        catch (Error | RuntimeException throwable) {
//            throw throwable;
//        }
//        catch (Throwable throwable) {
//            throw new UndeclaredThrowableException(throwable);
//        }
//    }
//}
