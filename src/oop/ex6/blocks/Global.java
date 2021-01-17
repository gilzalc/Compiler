package oop.ex6.blocks;

import java.util.HashMap;

/**
 * This class represents a global scope object
 */
public class Global extends Block { //singleton
	// Global scope static member (singleton)
	private static Global globalScope;
	// the map of the methods of the program
	private final HashMap<String, Method> methodsMap;


	private Global() {
		methodsMap = new HashMap<>();
	}

	/**
	 * gets the global scope unique instance, if it wasn't created yet - it constructs it
	 * @return the global scope unique instance
	 */
	public static Global getInstance() {
		if (globalScope == null) {
			globalScope = new Global();
		}
		return globalScope;
	}

	// nulls the reference, used between different program compiles
	public static void setNull() {
		globalScope = null;
	}

	/**
	 * adds a method to the methods map
	 * @param methodName String of the method's name
	 * @param method Method object to add
	 */
	public void addMethod(String methodName, Method method) {
		methodsMap.put(methodName, method);
	}

	/**
	 * gets a method object of the methods map
	 * @param methodName name of the method to get
	 * @return the Method object if found, null O.W
	 */
	public Method getMethod(String methodName) {
		return methodsMap.get(methodName);
	}
}
