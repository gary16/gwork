package com.gwork.app.others.classloader;

public class CustomizeClassLoader extends ClassLoader {

	private byte[] src;

	public CustomizeClassLoader(byte[] src) {
		this.src = src;
	}

	@Override
	protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
		Class clazz = this.findLoadedClass(name);
		if (clazz == null) {

			if (name.indexOf("DemoModule") > -1) {
				clazz = this.defineClass(name, src, 0, src.length);
			} else {
				clazz = super.loadClass(name, resolve);
			}

		}
		return clazz;
	}
	
}
