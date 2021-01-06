package oop.ex6;

public class Variable {

	private final boolean initialized;
	private final boolean isFinal;
	private final String type;

	public Variable(boolean vInitialized, boolean vFinal, String vType){
		initialized = vInitialized;
		isFinal = vFinal;
		type = vType;
	}

	public boolean variableInitialized(){
		return initialized;
	}

	public boolean variableIsFinal(){
		return isFinal;
	}

	public String variableType(){
		return type;
	}
}
