package ist.meic.pa.GenericFunctions;

import java.util.*;
import java.lang.reflect.Method;

public class GenericHandler {
	
	public static Object callMethod(Object target, String className, String methodName, Object[] args){

		try{
			Class<?> clazz = Class.forName(className);
			Method[] methods = clazz.getDeclaredMethods();
			Class<?>[] parameterType = new Class<?>[args.length];
			Class<?>[] parameterTypeCopy = new Class<?>[args.length];//copy because the other is changed frequently

			//bestMethod
			int bestCost = -1;
			Method bestMethod =null;
			int bestMethodNr=-1;
			Class<?>[] parameterTypeBestMethod = new Class<?>[args.length];

			for (int i = 0; i < args.length; i++) {
						parameterType[i] = args[i].getClass();
			}
			try{
				bestMethod =clazz.getDeclaredMethod(methodName, parameterType);
			}catch(NoSuchMethodException e){}

			//call if there is a method with those specific args
			if( bestMethod!= null && bestMethod.getAnnotation(ist.meic.pa.GenericFunctions.BeforeMethod.class)==null && bestMethod.getAnnotation(ist.meic.pa.GenericFunctions.AfterMethod.class)==null){
				bestMethod.setAccessible(true);
				parameterTypeCopy=parameterType.clone();
				callBefore(methods,target, args, parameterTypeCopy);
				parameterTypeCopy=parameterType.clone();
				Object result = bestMethod.invoke(target, args);
				parameterTypeCopy=parameterType.clone();
				callAfter(methods,target, args, parameterTypeCopy);
				return result;
			}
			int i;
			int count=0;
			//cycle to find the best method
			for(Method m: methods){
				parameterTypeCopy=parameterType.clone();
				count++;
				Class<?>[] parameterTypeMethod = m.getParameterTypes();
				if(parameterTypeMethod.length!=parameterTypeCopy.length || m.getAnnotation(ist.meic.pa.GenericFunctions.BeforeMethod.class)!=null || m.getAnnotation(ist.meic.pa.GenericFunctions.AfterMethod.class)!=null){
					continue;
				}
				i=calcCost(parameterTypeMethod,parameterTypeCopy);					
				if(i==-1){//-1 means that this method doesnt fit with those args
					continue;
				}
				if(i<bestCost || bestCost==-1){
					parameterTypeBestMethod=parameterTypeMethod.clone();
					bestMethodNr=count;
					bestCost=i;
					bestMethod=m;
				}
				i=-1;
			}
			if( bestMethod!= null && bestCost!=-1){
				bestMethod.setAccessible(true);
				callBefore(methods,target, args, parameterTypeCopy);
				parameterTypeCopy=parameterType.clone();
				Object result = bestMethod.invoke(target, args);
				parameterTypeCopy=parameterType.clone();
				callAfter(methods,target, args, parameterTypeCopy);
				return result;
			}
			return null;

		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

	public static void callBefore(Method[] methods, Object target, Object[] args, Class<?>[] parameterType){
		Map<Method,Integer> methodsPossible = new HashMap<Method,Integer>();
		Class<?>[] parameterTypeCopy=parameterType.clone();
		int maxCost=0;
		int i =-1;
		for(Method m: methods){
			parameterTypeCopy=parameterType.clone();
			if(m.getAnnotation(ist.meic.pa.GenericFunctions.BeforeMethod.class)!=null && m.getParameterTypes().length==parameterType.length){
					i=calcCost(m.getParameterTypes(),parameterTypeCopy);
					if(i>maxCost)maxCost=i;
					if(i==-1)continue;
					methodsPossible.put(m,i);
				}
		}
		for(int h=0; h<=maxCost;h++){
			for(Method m: methods){
				if(methodsPossible.get(m)!=null && methodsPossible.get(m)==h){
					try{
						m.invoke(target, args);
					}catch(Exception e){}
				}
			}
		}
	}
	public static void callAfter(Method[] methods, Object target, Object[] args, Class<?>[] parameterType){
		Map<Method,Integer> methodsPossible = new HashMap<Method,Integer>();
		Class<?>[] parameterTypeCopy=parameterType.clone();
		int maxCost=0;
		int i =-1;
		for(Method m: methods){
			parameterTypeCopy=parameterType.clone();
			if(m.getAnnotation(ist.meic.pa.GenericFunctions.AfterMethod.class)!=null && m.getParameterTypes().length==parameterType.length){
					i=calcCost(m.getParameterTypes(),parameterTypeCopy);
					if(i>maxCost)maxCost=i;
					if(i==-1)continue;
					methodsPossible.put(m,i);
				}
		}
		for(int h=maxCost; h>=0;h--){
			for(Method m: methods){
				if(methodsPossible.get(m)!=null && methodsPossible.get(m)==h){
					try{
						m.invoke(target, args);
					}catch(Exception e){}
				}
			}
		}
	}
	//for each arg, it is going to check if it fits on the method parameters
	public static int calcCost(Class<?>[] parameterTypeMethod, Class<?>[] parameterType){
		int cost = 0;
		Outer:
		for(int i=0;i<parameterType.length;i++){
			if(parameterTypeMethod[i]==parameterType[i]){
				continue;
			}
			for(Class<?> in: parameterType[i].getInterfaces()){
				if(parameterTypeMethod[i]==in){
					continue Outer;
				}
			}
			while(parameterType[i]!=null){//this cycle casts the arg to its superclass and checks if it fits
				if(parameterType[i].getSuperclass()==null){
					return -1;
				}
				parameterType[i]=parameterType[i].getSuperclass();
				cost++;
				if(parameterTypeMethod[i]==parameterType[i]){
					break;
				}
				}
		}
		return cost;
		
	}
}