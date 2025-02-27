package ast;
import visitor.IVisitor;

public abstract class NodeAST {
	
	private TypeDescriptor resType;
	
	public abstract void accept(IVisitor visitor);
	
	public void setResType(TypeDescriptor resType) {
		this.resType = resType;
	}
	
	public TypeDescriptor getResType() {
		return this.resType;
	}
}
