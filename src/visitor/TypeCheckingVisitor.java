package visitor;

import ast.LangType;
import ast.NodeDecSt;
import ast.NodeExpr;
import ast.TypeDescriptor;
import SymbolTable.Attributes;
import SymbolTable.Symtable;
import ast.NodeAssign;
import ast.NodeBinOp;
import ast.NodeConvert;
import ast.NodeCost;
import ast.NodeDecl;
import ast.NodeDeref;
import ast.NodeId;
import ast.NodePrint;
import ast.NodeProgram;

public class TypeCheckingVisitor implements IVisitor{

	StringBuilder log;
	Symtable symbolTable;
	
	public TypeCheckingVisitor(){
		symbolTable = new Symtable();
		symbolTable.init();
		log = new StringBuilder();
	}
	
	public String logToString() {
		return log.toString();
	}

	public String symbolTableToString() {
		return symbolTable.toString();
	}
	
	@Override
	public void visit(NodeProgram node) {
		for(NodeDecSt nodeSt : node.getDecSts()) {
			nodeSt.accept(this);
		}
	}	

	@Override
	public void visit(NodeDecl node) {		
		
		String id = node.getNodeId().getId();
		LangType type = node.getType();
		Attributes att = new Attributes();
		att.type = Utilities.conversion(type);
		
		boolean result = symbolTable.enter(id, att);
		
		if(result){
			node.setResType(Utilities.conversion(type)); 
			node.getNodeId().setDefinition(att);
		}
		else{
			node.setResType(TypeDescriptor.Error); 
			log.append("[DECL] " + id + " declarated yet\n");
		}
	}

	@Override
	public void visit(NodeId node) {
		
		Attributes attr = symbolTable.lookup(node.getId());
		if(attr == null){
			node.setResType(TypeDescriptor.Error); 			
		}
		else{
			node.setDefinition(attr);
			node.setResType(node.getDefinition().type);
		}
	}

	@Override
	public void visit(NodeCost node) {
		node.setResType(Utilities.conversion(node.getType()));		
	}

	@Override
	public void visit(NodeDeref node) {
		NodeId id = node.getNodeId();
		id.accept(this);
		if(id.getResType() == TypeDescriptor.Error) {
			if(id.getId().toString().contains("."))
				node.setResType(TypeDescriptor.Float);
			else
				node.setResType(TypeDescriptor.Int);			
		}
	}

	@Override
	public void visit(NodeBinOp node) {
		NodeExpr left = node.getLeft();
		left.accept(this);
		NodeExpr right = node.getRight();
		right.accept(this);
		
		
		if(left.getResType() == TypeDescriptor.Error || right.getResType() == TypeDescriptor.Error)
			node.setResType(TypeDescriptor.Error);
		
		if(left.getResType() == right.getResType())
			node.setResType(left.getResType());
		
		if(left.getResType() != right.getResType()){
			node.setResType(TypeDescriptor.Float);
			node.setLeft( Utilities.converti(left));
			node.setRight( Utilities.converti(right));
		}

	}

	@Override
	public void visit(NodeAssign node) {
		NodeExpr expr = node.getExpr();
		
		expr.accept(this);
		NodeId id = node.getNodeId();
		id.accept(this);
				
		if(!Utilities.booleancompatibile(id.getResType(), expr.getResType()))
			log.append("[ASSIGN] " + id.getResType() + " " + node.getNodeId().getId()+ "\n");
		else{
			if(id.getResType() != expr.getResType())
				node.setExpr(Utilities.converti(expr));
		}
		
	}

	@Override
	public void visit(NodePrint node) {
		NodeId id = node.getNodeId();
		id.accept(this);
		
		if(id.getResType() == TypeDescriptor.Error)
			log.append("[PRINT] "+ id.getResType() + " " + node.getNodeId().getId()+ " is not define\n");
	}

	@Override
	public void visit(NodeConvert nodeConvert) {
		nodeConvert.getNodeExpr().accept(this);
		if(nodeConvert.getNodeExpr().getResType() != TypeDescriptor.Int)
			nodeConvert.setResType(TypeDescriptor.Error);
		else
			nodeConvert.setResType(TypeDescriptor.Float);
	}

	
}
