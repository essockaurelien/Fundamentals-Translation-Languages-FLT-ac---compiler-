package ast;

import visitor.IVisitor;

public class NodeDecl extends NodeDecSt{
	
	NodeId id;
	LangType type;
	
	public NodeDecl(NodeId id, LangType type) {
		this.id = id;
		this.type = type;
	}
	
	public LangType getType() {
		return type;
	}
	
	public NodeId getNodeId() {
		return id;
	}
	
	public String toString() {
		return "NodeDecl: " + type +" " +id.toString() +"\n";
	}
	
	@Override
	public void accept(IVisitor visitor) {
		visitor.visit(this);
	}
}