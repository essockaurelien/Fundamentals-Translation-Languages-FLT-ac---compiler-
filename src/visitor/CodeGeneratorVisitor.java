package visitor;

import java.util.HashMap;

import ast.LangOper;
import ast.NodeAssign;
import ast.NodeBinOp;
import ast.NodeConvert;
import ast.NodeCost;
import ast.NodeDecSt;
import ast.NodeDecl;
import ast.NodeDeref;
import ast.NodeId;
import ast.NodePrint;
import ast.NodeProgram;

public class CodeGeneratorVisitor implements IVisitor{
	StringBuilder errorLogs;                         //errori
	public StringBuffer codice;                      //codice generato
	static char car;                                 //
	static HashMap<String, Character> map;           //symboltable
	
	public CodeGeneratorVisitor(){
		errorLogs = new StringBuilder();
		car = 'a' - 1;
		codice = new StringBuffer();
		map = new HashMap<String, Character>();
	}
	
	public String errorLogsToString() {
		return errorLogs.toString();
	}
	
	//any node
	 @Override
	public void visit(NodeProgram node) {
		for(NodeDecSt nodeSt : node.getDecSts()) {
			nodeSt.accept(this);                       //visita di tutti
		}
	}	

   	@Override
	public void visit(NodeDecl node) {	
		node.getNodeId().accept(this);
		node.getNodeId().definition.register = newRegister(node.getNodeId().getId());
 	}

	@Override
	public void visit(NodeId node) {
	}

	@Override
	public void visit(NodeCost node) {
		codice.append(node.getValue());
	}
	
	public void visit(NodeConvert node) {
		node.getNodeExpr().accept(this);
		codice.append("5 k "); 																					/*5 cifre decimale per la precisione*/
	}

	@Override
	public void visit(NodeDeref node) {
		node.getNodeId().accept(this);
		codice.append(node.getNodeId().getId() +" ");
		
	}

	@Override
	public void visit(NodeBinOp node) {
		node.getLeft().accept(this);
		node.getRight().accept(this);		
		codice.append(convertOpe(node.getOper()));
	}

   @Override
	public void visit(NodeAssign node) {
		char r = getChar(node.getNodeId().getId());
		node.getExpr().accept(this);
		codice.append(" s"+r);
		codice.append(" 0 k ");
	}

	@Override
	public void visit(NodePrint node) {
		char r = getChar(node.getNodeId().getId());
		codice.append("l"+r);
		codice.append(" p ");
		codice.append("P ");
	}

	private static char newRegister(String id) {
		
		if(!map.containsKey(id)) {
			if(car <= 'y') {
				car++;
				map.put(id, car);
				return (char) car;
			}
			else {
				System.out.println("Variables more than register");	
				return 0;
			}
		}
		return 0;
	}
	
	private static char getChar(String id) {
		return map.get(id);
	}
	
	private String convertOpe(LangOper o) {
		switch(o) {
		
			case PIU:
				return "+";
			case MENO:
				return "-";
			case DIV:
				return "/";
			case PER:
				return "*";
		}
		return null;		
	}

}

