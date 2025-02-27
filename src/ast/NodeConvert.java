package ast;

import visitor.IVisitor;

public class NodeConvert extends NodeExpr {

	NodeExpr node;
	
	public NodeConvert(NodeExpr node){
		this.node = node;
		super.setResType(TypeDescriptor.Float);
	}
	
	public NodeExpr getNodeExpr(){
		return node;
	}
	
	@Override
	public void accept(IVisitor visitor) {
		visitor.visit(this);
	}
	
	public String toString() {
		return "NodeConv: "+ node.toString();
	}

}


