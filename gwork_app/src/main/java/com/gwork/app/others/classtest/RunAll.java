package com.gwork.app.others.classtest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

public class RunAll {

	public static void main(String args[]) {
		runB();
	}
	
	private static void runA() {
		Handler1 handler1= new Handler1();
		handler1.printTitle();
	}
	
	private static void runB() {
		MappedBeanSrc src = new MappedBeanSrc();
		src.setName("abcdef");
		src.setVal(100L);
		List<Date> dList = new ArrayList<Date>();
		Date d1 = new Date();
		dList.add(d1);
		src.setDateList(dList);
		MappedBeanDest dest1 = getObject(src,MappedBeanDest.class );
		MappedBeanDest dest2 = getObject(src,MappedBeanDest.class );
		dest1.getName();
	}
	
	public static <B,D> D getObject(B src ,Class<D> destType) {
		MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
		mapperFactory.classMap(src.getClass(),destType).byDefault().register();
		D dest = mapperFactory.getMapperFacade().map(src, destType);
		return dest;
	}
}
