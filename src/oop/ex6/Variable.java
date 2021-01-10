package oop.ex6;

public class Variable {

	private final boolean initialized;
	private final boolean isFinal;
	private final String name;
	private final Keywords.Type type;

	public Variable(boolean vInitialized, boolean vFinal, String vName, Keywords.Type Vtype){
		initialized = vInitialized;
		isFinal = vFinal;
		name = vName;
		type = Vtype;
	}

	 public boolean isInitialized(){
		return initialized;
	}

	 public boolean IsFinal(){
		return isFinal;
	}

	 public String getName(){
		return name;
	}

	public Keywords.Type getType() {
		return type;
	}

}
