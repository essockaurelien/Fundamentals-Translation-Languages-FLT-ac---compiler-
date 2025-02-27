package ast;

import visitor.IVisitor;

public class NodePrint extends NodeStm{
	
	NodeId id;
	
	public NodePrint(NodeId id) {
		this.id = id;
	}

	public NodeId getNodeId(){
		return id;
	}
	
	public String toString() {
		return "NodePrint: "+id.toString()+"\n";
	}
	
	@Override
	public void accept(IVisitor visitor) {
		visitor.visit(this);
	}
}
