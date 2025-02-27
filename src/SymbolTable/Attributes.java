package SymbolTable;

import ast.TypeDescriptor;

	public class Attributes {
		public TypeDescriptor type;
		public char register;
		
		public String toString() {
			return "Type: "+ type + " | Register: " + register;
		}
	}


