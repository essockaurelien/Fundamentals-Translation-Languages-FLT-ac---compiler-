 package visitor;

import ast.LangType;
import ast.NodeConvert;
import ast.NodeExpr;
import ast.TypeDescriptor;

public class Utilities {
	/*dimmi se tipo*/
	public static TypeDescriptor conversion(LangType type){
		
		switch(type){
		
			case FLOAT:
				return TypeDescriptor.Float;
		
			case INT:
				return TypeDescriptor.Int;
				
		}
		
		return TypeDescriptor.Error;
	}
	
	public static boolean booleancompatibile(TypeDescriptor t1, TypeDescriptor t2){
		
		if(t1 == TypeDescriptor.Error || t2 == TypeDescriptor.Error)
			return false;
		
		if(t1 == t2){
			return true;
		}
		else{
			if(t1 == TypeDescriptor.Float)
				return true;
			else
				return false;
		}
	}
	
	public static NodeExpr converti(NodeExpr node){
		
		if(node.getResType() == TypeDescriptor.Float)
			return node;       
		else {
			NodeConvert convertnode = new NodeConvert(node);
			return convertnode;			
		}
	}

}
