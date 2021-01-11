package oop.ex6;

public class Variable {

	private  boolean initialized;
	private final boolean isFinal;
	//	private final String name;
	private final Keywords.Type type;

	public Variable(boolean vInitialized, boolean vFinal, Keywords.Type Vtype) {
		initialized = vInitialized;
		isFinal = vFinal;
		//		name = vName;
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

	//	 public String getName(){
	//		return name;
	//	}

	public Keywords.Type getType() {
		return type;
	}

}
