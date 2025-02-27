package ast;

import visitor.IVisitor;

public class NodeAssign extends NodeStm{
	
	NodeId id;
	NodeExpr expr;
	
	public NodeAssign(NodeId id, NodeExpr expr) {
		this.id = id;
		this.expr = expr;
	}

	public NodeExpr getExpr() {
		return expr;
	}
	
	public NodeId getNodeId() {
		return id;
	}
	
	public String toString() {
		return "NodeAssign: "+id.toString()+" = "+expr.toString() +"\n";
	}

	@Override
	public void accept(IVisitor visitor) {
		visitor.visit(this);
	}

	public void setExpr(NodeExpr expr) {
		this.expr = expr;		
	}
}
