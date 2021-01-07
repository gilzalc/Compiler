package oop.ex6;

public class Variable {

	private final boolean initialized;
	private final boolean isFinal;
	private final String name;

	public Variable(boolean vInitialized, boolean vFinal, String vName){
		initialized = vInitialized;
		isFinal = vFinal;
		name = vName;
	}

	 public boolean variableInitialized(){
		return initialized;
	}

	 public boolean variableIsFinal(){
		return isFinal;
	}

	 public String getName(){
		return name;
	}
}
