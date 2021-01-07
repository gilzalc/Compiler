package oop.ex6.scopes;

import oop.ex6.Variable;

import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.LinkedList;

class Method {
  private final LinkedList<Type> requiredTypes;
  Method(LinkedList<Type> requiredTypes){
   super();
   this.requiredTypes = requiredTypes;
  }

}
