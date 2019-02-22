package com.gwork.app.others.classloader;

import java.io.IOException;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtConstructor;
import javassist.CtMethod;

public class CodeCompile {

	
	public static byte[] getDemoClassResource(int type) throws CannotCompileException, IOException {
		byte[] res = null;
		ClassPool pool = ClassPool.getDefault();
		CtClass cc= pool.makeClass("com.gwork.app.others.classloader.DemoModule");
		if (type == 1) {
			CtMethod method01 = null;
			method01 = CtMethod.make("public String getName(){return \"aaaaaaaaaa\";}", cc);
			cc.addMethod(method01);
			CtConstructor cons = new CtConstructor(null, cc);
			cons.setBody("{}");
			cc.addConstructor(cons);
			res = cc.toBytecode();
			cc.defrost();
		} else {
			CtMethod method01 = null;
			method01 = CtMethod.make("public String getName(){return \"bbbbbbbbbb\";}", cc);
			cc.addMethod(method01);
			CtConstructor cons = new CtConstructor(null, cc);
			cons.setBody("{}");
			cc.addConstructor(cons);
			res = cc.toBytecode();
			cc.defrost();
		}

		return res;
	}

	public static Class getDemoClass(int type) throws CannotCompileException, IOException {

		ClassPool pool = ClassPool.getDefault();
		CtClass cc = pool.makeClass("com.gwork.app.others.classloader.DemoModule");
		CtMethod method01 = null;
		if (type == 1) {
			method01 = CtMethod.make("public String getName(){return \"aaaaaaaaaa\";}", cc);
		} else {
			method01 = CtMethod.make("public String getName(){return \"bbbbbbbbbb\";}", cc);
		}
		cc.addMethod(method01);
		CtConstructor cons = new CtConstructor(null, cc);
		cons.setBody("{}");
		cc.addConstructor(cons);
		return cc.toClass();
	}

}
