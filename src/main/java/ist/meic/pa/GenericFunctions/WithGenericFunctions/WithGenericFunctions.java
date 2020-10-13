package ist.meic.pa.GenericFunctions.WithGenericFunctions;

import javassist.Loader;
import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.CtNewMethod;
import javassist.NotFoundException;
import javassist.Translator;
import javassist.expr.ExprEditor;
import javassist.expr.MethodCall;

public class WithGenericFunctions {

	static class GenericTranslator implements Translator {
		public void start(ClassPool pool) throws NotFoundException, CannotCompileException {
			// Do nothing
		}

		public void onLoad(ClassPool pool, String ctClassName) throws NotFoundException, CannotCompileException {
			// Obtain the compile time class
			CtClass ctClass = pool.get(ctClassName);

			try{
				ctClass.instrument(new ExprEditor(){
				    public void edit(MethodCall c) {
				    	try{
							CtClass clazz = pool.get(c.getClassName());
							if(clazz.hasAnnotation(ist.meic.pa.GenericFunctions.GenericFunction.class)){
								c.replace("$_ = ($r) ist.meic.pa.GenericFunctions.GenericHandler.callMethod($0,\"" + c.getClassName() + "\", \"" + c.getMethodName() + "\", $args); ");
							}
				  		} catch(CannotCompileException e){ e.printStackTrace();
				  		} catch (NotFoundException e) { e.printStackTrace();}
				  	}
				});
			}catch(CannotCompileException e){e.printStackTrace();}

		}

	}

	public static void main(String[] args){

		if(args.length < 1) {
            System.err.println("Invalid args");
            System.exit(1);
        }

        Translator translator = new GenericTranslator();
		ClassPool pool = ClassPool.getDefault();
		Loader classLoader = new Loader();
		try{
			classLoader.addTranslator(pool, translator);
		}
		catch(Exception e){e.printStackTrace();}
		String[] restArgs = new String[args.length - 1];
		System.arraycopy(args, 1, restArgs, 0, restArgs.length);
		try{
			classLoader.run(args[0], restArgs);}
		catch(Throwable e){e.printStackTrace();}
	}
}