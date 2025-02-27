package ast;

import SymbolTable.Attributes;
import visitor.IVisitor;


public class NodeId extends NodeAST{
	
	String id;
	public Attributes definition;
	
	public NodeId(String id) {
		this.id = id;
		if(this.definition == null) {
			this.definition = new Attributes();
		}
	}
	
	public String getId() {
		return id;
	}

	public Attributes getDefinition() {
		return definition;
	}
	
	public void setDefinition(Attributes definition) {
		this.definition = definition;
	}

	public String toString() {
		return "NodeId: "+id + " Definition: "+ definition;
	}
	
	
	@Override
	public void accept(IVisitor visitor) {
		visitor.visit(this);
	}
}
