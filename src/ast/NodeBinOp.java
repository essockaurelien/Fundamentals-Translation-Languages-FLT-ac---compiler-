package ast;
import visitor.IVisitor; 
public class NodeBinOp extends NodeExpr{
	
	LangOper op;
	NodeExpr left, right;
	
	public NodeBinOp(NodeExpr left, LangOper op, NodeExpr right) {
		this.op = op;
		this.right = right;
		this.left = left;
	}
	
	public void setLeft(NodeExpr left) {
		this.left = left;
	}
	
	public void setRight(NodeExpr right) {
		this.right = right;
	}

	public NodeExpr getLeft() {
		return left;
	}
	
	public NodeExpr getRight() {
		return right;
	}
	
	public LangOper getOper() {
		return op;
	}
	
	public String toString() {
		return "NodeBinOp: "+left.toString()+" "+ op.toString() +" "+right.toString();
	}
	
	@Override
	public void accept(IVisitor visitor) {
		visitor.visit(this);
	}
	
}
