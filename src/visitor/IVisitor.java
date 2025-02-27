package visitor;
import ast.*;
	public interface IVisitor {
		public void visit(NodeProgram node);
		public void visit(NodeId node);
		public void visit(NodeCost node);
		public void visit(NodeDeref node);
		public void visit(NodeBinOp node);
		public void visit(NodeDecl node);
		public void visit(NodeAssign node);
		public void visit(NodePrint node);
		public void visit(NodeConvert nodeConvert);
	}	
	