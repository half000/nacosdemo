package com.half.proxy.jdk;
import com.half.proxy.demo.MyInvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.UndeclaredThrowableException;
public final class $MyProxy0  implements com.half.proxy.demo.Person{
MyInvocationHandler h;
public $MyProxy0(MyInvocationHandler h){this.h=h;}
public void sendMail1()throws java.lang.Exception{ 
try {
Method method=com.half.proxy.demo.Person.class.getMethod("sendMail1");
 h.invoke(this,method,null);
} catch ( java.lang.Exception | Error var2) {    throw var2;  } catch (Throwable var3) {    throw new UndeclaredThrowableException(var3); }}
public void sendMail()throws java.lang.RuntimeException{ 
try {
Method method=com.half.proxy.demo.Person.class.getMethod("sendMail");
 h.invoke(this,method,null);
} catch ( java.lang.RuntimeException | Error var2) {    throw var2;  } catch (Throwable var3) {    throw new UndeclaredThrowableException(var3); }}}