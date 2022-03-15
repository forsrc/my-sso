package org.forsrc.utils;

import java.io.Serializable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.springframework.cglib.beans.BeanCopier;
import org.springframework.cglib.core.Converter;
import org.springframework.security.util.FieldUtils;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

public class BeanUtils {

	private static final ConcurrentMap<String, BeanCopier> BEAN_COPIER_MAP = new ConcurrentHashMap<>();
	
	public static <S extends Serializable, T extends Serializable> BeanCopier create(Class<S> source, Class<T> target, boolean useConverter) {
		String key = source.getName() + target.getName();
		BeanCopier beanCopier = BEAN_COPIER_MAP.get(key);
		if (beanCopier == null) {
			synchronized (BEAN_COPIER_MAP) {
				if ((beanCopier = BEAN_COPIER_MAP.get(key)) == null) {
					 beanCopier = BeanCopier.create(source, target, useConverter);
					 BEAN_COPIER_MAP.put(key, beanCopier);
				} 
			}
		}
		return beanCopier;
	}

	public static <S extends Serializable, T extends Serializable>  void copy(S source, T target) {
		BeanCopier beanCopier = create(source.getClass(), target.getClass(), false);
		beanCopier.copy(source, target, null);
	}
	
	public static <S extends Serializable, T extends Serializable>  void copyIgnoreNull(S source, T target) {
		BeanCopier beanCopier = create(source.getClass(), target.getClass(), true);
		beanCopier.copy(source, target, new Converter() {
			
			@Override
			public Object convert(Object sourceValue, Class targetClass, Object context) {
				if (sourceValue == null) {
					String name = ObjectUtils.nullSafeToString(context).substring(3);
					String fieddName = StringUtils.uncapitalize(name);
					try {
						Object value = FieldUtils.getFieldValue(target, fieddName);
						return value;
					} catch (IllegalAccessException e) {
						e.printStackTrace();
						return null;
					}
				}
				if (sourceValue.getClass() == targetClass) {
					return sourceValue;
				}       
				return null;
			}
			
		});
	}
	
	public static <S extends Serializable, T extends Serializable>  void copy(S source, T target, Converter converter) {
		BeanCopier beanCopier = create(source.getClass(), target.getClass(), converter != null);
		beanCopier.copy(source, target, converter);
	}
	
	private static class Test implements Serializable{
		private String name;
		private Integer age;
		
		public Test(String name, Integer age) {
			this.name = name;
			this.age = age;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public Integer getAge() {
			return age;
		}
		public void setAge(Integer age) {
			this.age = age;
		}
		@Override
		public String toString() {
			return "Test [name=" + name + ", age=" + age + "]";
		}
		
	} 

	public static void main(String[] args) {
		Test test1 = new Test("test", null);
		Test test2 = new Test(null, 18);
		BeanUtils.copyIgnoreNull(test1, test2);
		System.out.println(test1.toString());
		System.out.println(test2.toString());
		Assert.isTrue(test2.getAge().equals(18));
	}
}
