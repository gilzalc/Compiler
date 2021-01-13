package oop.ex6;

public class Variable {

	private  boolean initialized;
	private final boolean isFinal;
	private final Keywords.Type type;

	public Variable(boolean vInitialized, boolean vFinal, Keywords.Type Vtype) {
		initialized = vInitialized;
		isFinal = vFinal;
		type = Vtype;
	}

	public boolean isInitialized() {
		return initialized;
	}

	public void initial(){
		initialized = true;
	}

	public boolean IsFinal() {
		return isFinal;
	}

	public Keywords.Type getType() {
		return type;
	}

}
