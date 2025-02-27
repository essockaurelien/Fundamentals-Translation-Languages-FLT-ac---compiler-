package Parser;

import java.io.IOException;
import java.util.ArrayList;

import ast.LangOper;
import ast.LangType;
import ast.NodeAssign;
import ast.NodeBinOp;
import ast.NodeDecSt;
import ast.NodeDecl;
import ast.NodeDeref;
import ast.NodeExpr;
import ast.NodeId;
import ast.NodePrint;
import ast.NodeProgram;
import ast.NodeStm;
import scanner.LexicalException;
import scanner.Scanner;
import token.Token;
import token.TokenType;

/*
 * match doit vérifier si le prochain token a un certain type, 
 * auquel a token cas il le consommerait autrement donne erreur.
 * */
public class Parser {

	// private static final String string = null;
	private Scanner scanner;
	private Token nextT;

	public Parser(Scanner scanner) {
		this.scanner = scanner;
	}

	void match(TokenType type) throws SyntacticException, LexicalException, IOException {
		if (type.equals(scanner.peekToken().getTipo()))
			nextT = scanner.nextToken();
		else
			throw new SyntacticException(Integer.toString(scanner.peekToken().getRiga()));
	}

	// Prg -> DSs eof
	public NodeProgram parsePrg() throws SyntacticException, LexicalException, IOException {
		Token token = scanner.peekToken();

		switch (token.getTipo()) {
		case FLOAT:
		case INT:
		case ID:
		case PRINT:
			ArrayList<NodeDecSt> array = parseDSs();
			match(TokenType.EOF);
			return new NodeProgram(array);
		default:
			throw new SyntacticException(Integer.toString(token.getRiga()));
		}
	}

	// DSs -> Dcl DSs
	// DSs -> Stm DSs
	// DSs -> e
	private ArrayList<NodeDecSt> parseDSs() throws SyntacticException, LexicalException, IOException {
		Token token = scanner.peekToken();
		ArrayList<NodeDecSt> nodes = new ArrayList<NodeDecSt>();

		switch (token.getTipo()) {
		case FLOAT:
		case INT:
			nodes.add(parseDcl());
			nodes.addAll(parseDSs());
			return nodes;
		case ID:
		case PRINT:
			nodes.add(parseStm());
			nodes.addAll(parseDSs());
			return nodes;
		case EOF:
			return nodes;
		default:
			throw new SyntacticException(Integer.toString(token.getRiga()));
		}
	}

	// ok
	private NodeDecl parseDcl() throws SyntacticException, LexicalException, IOException {
		Token token = scanner.peekToken();
		Token tokenId;

		switch (token.getTipo()) {
		case FLOAT:
			match(TokenType.FLOAT);// per togliere il float grazie alla peektoken
			match(TokenType.ID);// metto nel tokenId
			tokenId = nextT;
			match(TokenType.SEMI);
			return new NodeDecl(new NodeId(tokenId.getVal()), LangType.FLOAT); // lo ritorno come valore del nodo

		case INT:
			match(TokenType.INT);
			match(TokenType.ID);
			tokenId = nextT;
			match(TokenType.SEMI);
			return new NodeDecl(new NodeId(tokenId.getVal()), LangType.INT);

		default:
			throw new SyntacticException(Integer.toString(token.getRiga()));
		}
	}

	private NodeStm parseStm() throws SyntacticException, LexicalException, IOException {
		Token token = scanner.peekToken();
		Token tokenId;
		switch (token.getTipo()) {
		case ID:
			match(TokenType.ID);
			tokenId = nextT;
			match(TokenType.ASSIGN);
			NodeExpr expr = parseExp();
			match(TokenType.SEMI);
			return new NodeAssign(new NodeId(tokenId.getVal()), expr);
		case PRINT:
			match(TokenType.PRINT);
			match(TokenType.ID);
			tokenId = nextT;
			match(TokenType.SEMI);
			return new NodePrint(new NodeId(tokenId.getVal()));
		default:
			throw new SyntacticException(Integer.toString(token.getRiga()));
		}
	}

	private NodeExpr parseExp() throws SyntacticException, LexicalException, IOException {
		Token token = scanner.peekToken();
		switch (token.getTipo()) {
		case TYINT:
		case TYFLOAT:
		case ID:
			NodeExpr left = parseTr();
			NodeExpr exp = parseExpP(left);
			return exp;
		default:
			throw new SyntacticException(Integer.toString(token.getRiga()));
		}
	}

	private NodeExpr parseExpP(NodeExpr left) throws SyntacticException, LexicalException, IOException {
		Token token = scanner.peekToken();
		NodeExpr Tr_ex1, Tr_ex2;// sono sintetizzazi

		switch (token.getTipo()) {
		case PLUS:
			match(TokenType.PLUS);
			Tr_ex1 = parseTr();
			Tr_ex2 = parseExpP(new NodeBinOp(left, LangOper.PIU, Tr_ex1));
			return Tr_ex2;
		case MINUS:
			match(TokenType.MINUS);
			Tr_ex1 = parseTr();
			Tr_ex2 = parseExpP(new NodeBinOp(left, LangOper.MENO, Tr_ex1));
			return Tr_ex2;

		case SEMI:
			return left;

		default:
			throw new SyntacticException(Integer.toString(token.getRiga()));
		}
	}

	private NodeExpr parseTr() throws SyntacticException, LexicalException, IOException {
		Token token = scanner.peekToken();
		switch (token.getTipo()) {
		case TYINT:
		case TYFLOAT:
		case ID:
			NodeExpr left = parseVal();
			NodeExpr exp = parseTrP(left);
			return exp;
		default:
			throw new SyntacticException(Integer.toString(token.getRiga()));
		}
	}

	private NodeExpr parseTrP(NodeExpr left) throws SyntacticException, LexicalException, IOException {
		Token token = scanner.peekToken();
		NodeExpr ex1, ex2;
		switch (token.getTipo()) {
		case TIMES:
			match(TokenType.TIMES);
			ex1 = parseVal();
			ex2 = parseTrP(new NodeBinOp(left, LangOper.PER, ex1));
			return ex2;
		case DIV:
			match(TokenType.DIV);
			ex1 = parseVal();
			ex2 = parseTrP(new NodeBinOp(left, LangOper.DIV, ex1));
			return ex2;

		case PLUS:
		case MINUS:
		case SEMI:
		case INT:
		case PRINT:
		case ID:
		case EOF:
			return left;

		default:
			throw new SyntacticException(Integer.toString(token.getRiga()));
		}
	}

	private NodeExpr parseVal() throws SyntacticException, LexicalException, IOException {
		Token token = scanner.peekToken();
		switch (token.getTipo()) {
		case TYINT:
			match(TokenType.TYINT);
			return new NodeDeref(new NodeId(token.getVal()));
		case TYFLOAT:
			match(TokenType.TYFLOAT);
			return new NodeDeref(new NodeId(token.getVal()));
		case ID:
			match(TokenType.ID);
			return new NodeDeref(new NodeId(token.getVal()));

		default:
			throw new SyntacticException(Integer.toString(token.getRiga()));
		}
	}

	/**/
}