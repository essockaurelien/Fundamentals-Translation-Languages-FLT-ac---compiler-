package ast;

import java.util.ArrayList;

public class NodeProgram {
	
	ArrayList<NodeDecSt> decSts;
	
	public NodeProgram(ArrayList<NodeDecSt> decSts) {
		this.decSts = decSts;
	}
	
	public ArrayList<NodeDecSt> getDecSts(){
		return decSts;
	}
	
	public String toString() {
		return decSts.toString();
	}

}
