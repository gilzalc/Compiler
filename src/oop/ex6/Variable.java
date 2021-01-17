package oop.ex6;

/**
 * Represents a variable of the S-Java program
 */
public class Variable {

	// This data member indicates whether the object was initialized
	private boolean isInitialized;
	// This data member indicates whether the object was declared final (and can't be assigned a new value
	// if has been initialized)
	private final boolean isFinal;
	// the S-Java variable type of this Variable object
	private final Keywords.Type type;

	/**
	 * A constructor for the Variable object
	 * @param vInitialized indicates if the var was initialized during its first declaration
	 * @param vFinal indicates whether the variable was declared fianl
	 * @param Vtype the S-Java variable type of this variable
	 */
	public Variable(boolean vInitialized, boolean vFinal, Keywords.Type Vtype) {
		isInitialized = vInitialized;
		isFinal = vFinal;
		type = Vtype;
	}

	/**
	 * @return true if the variable was initialized, false O.W
	 */
	public boolean isInitialized() {
		return isInitialized;
	}

	/**
	 * Initializes the variable
	 */
	public void initial() {
		isInitialized = true;
	}

	/**
	 * @return true if the variable was declared final, false O.W
	 */
	public boolean IsFinal() {
		return isFinal;
	}

	/**
	 * A getter for the type of the variable
	 * @return the type of the varble
	 */
	public Keywords.Type getType() {
		return type;
	}
}
