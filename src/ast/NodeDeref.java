package ast;

import visitor.IVisitor;

public class NodeDeref extends NodeExpr{
	
	NodeId id;
	
	public NodeDeref(NodeId id) {
		this.id = id;
	}
	
	public NodeId getNodeId(){
		return id;
	}

	public String toString() {
		return "NodeDeref: "+id.toString();
	}
	
	@Override
	public void accept(IVisitor visitor) {
		visitor.visit(this);
	}
}
