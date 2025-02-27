package ast;

import visitor.IVisitor;

public class NodeCost extends NodeExpr{
	
	String value;
	LangType type;
	
	public NodeCost(String value, LangType type) {
		this.value = value;
		this.type = type;
	}

	public LangType getType() {
		return type;
	}
	
	public String getValue() {
		return value;
	}
	
	public String toString() {
		return "NodeCost: "+value+" | "+type;
	}
	
	@Override
	public void accept(IVisitor visitor) {
		visitor.visit(this);
	}
}
