package test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;

import scanner.LexicalException;
import scanner.Scanner;
import token.Token;
public class TestScanner {
	
	
	@Test
	public void testScanEOF() throws IOException, LexicalException {
		
		//String path ="src/test/data/testOperators.txt";
		Scanner scanner = new Scanner("src/test/data/testEOF.txt");
		assertEquals("<EOF,r:3>", scanner.nextToken().toString());
		/*
		Scanner scanner1 = new Scanner("./src/test/data/testNumbers.txt");
		assertEquals("<EOF, r:1>", scanner1.nextToken().toString());
		*/
		
	}
	
	@Test
	public void testScanOp() throws IOException, LexicalException {
		Scanner scanner = new Scanner("src/test/data/testOperators.txt");
		assertEquals("<PLUS,r:1>", scanner.nextToken().toString());
		
		assertEquals("<TIMES,r:2>", scanner.nextToken().toString());
		
		assertEquals("<DIV,r:3>", scanner.nextToken().toString());
		assertEquals("<ASSIGN,r:8>", scanner.nextToken().toString());
		assertEquals("<SEMI,r:10>", scanner.nextToken().toString());
	}
	
	@Test
	public void testScanID() throws IOException, LexicalException{
		
		Scanner scanner = new Scanner("src/test/data/testIdKw.txt");
		
		assertEquals("<TYINT,r:1>", scanner.nextToken().toString());
		
		assertEquals("<TYFLOAT,r:2>",scanner.nextToken().toString());
		assertEquals("<ID,r:2,floata>",scanner.nextToken().toString());
	}
	

	@Test
    public void testScanNumber() throws IOException, LexicalException{
		
		Scanner scanner = new Scanner("src/test/data/testNumbers.txt");
		
		assertEquals("<INT,r:1,3000>", scanner.nextToken().toString());
		
		//assertEquals("<FLOAT,r:4,098.895>", scanner.nextToken().toString());
		
		
		
		 assertEquals("<INT,r:3,698>", scanner.nextToken().toString());
	     assertEquals("<FLOAT,r:4,3.4>", scanner.nextToken().toString());
		
		assertEquals("<INT,r:5,45668>", scanner.nextToken().toString());
		
		LexicalException e1 = assertThrows(LexicalException.class, () -> scanner.nextToken(), "LexicalException");
		assertEquals("no float", e1.getMessage());
		
		assertEquals("<FLOAT,r:8,89.99>", scanner.nextToken().toString());
		
		LexicalException e2 = assertThrows(LexicalException.class, () -> scanner.nextToken(), "LexicalException");
		assertEquals("non'è zero", e2.getMessage());
		
		assertEquals("<INT,r:9,98>", scanner.nextToken().toString());
	    
		//assertEquals("<EOF,r:9>", scanner.nextToken().toString());
        
		assertEquals("<INT,r:10,0>", scanner.nextToken().toString());
		assertEquals("<INT,r:11,11>", scanner.nextToken().toString());
        
		assertEquals("<EOF,r:11>", scanner.nextToken().toString());
	}
	
	@Test
	public void testScanGenerale() throws IOException, LexicalException{
		
		Scanner scanner = new Scanner("src/test/data/testGenerale.txt");
		
	    assertEquals("<TYINT,r:2>", scanner.nextToken().toString());
	                   
	    assertEquals("<ID,r:2,tempa>", scanner.nextToken().toString());
	    
	    assertEquals("<SEMI,r:2>", scanner.nextToken().toString());
	    
	    assertEquals("<ID,r:3,tempa>", scanner.nextToken().toString());
	    
	    assertEquals("<ASSIGN,r:3>", scanner.nextToken().toString());
	    
	    assertEquals("<FLOAT,r:3,5.1>", scanner.nextToken().toString());
	    
	    assertEquals("<SEMI,r:3>", scanner.nextToken().toString());
	    
	    LexicalException e1 = assertThrows(LexicalException.class, () -> scanner.nextToken(), "LexicalException");
		assertEquals("token non comincia per .", e1.getMessage());
	    
		assertEquals("<INT,r:4,12>", scanner.nextToken().toString());
	    //assertEquals("<,r:4>", scanner.nextToken().toString());
	    
	   // assertEquals("<TYFLOAT,r:3,5.1>", scanner.nextToken().toString());
		
	    //assertEquals("<SEMI,r:3>", scanner.nextToken().toString());
	    
	    
	}
	
	
	
	
	

	@Test
	public void testEcc() throws LexicalException, IOException {
		
		Scanner scanner = new Scanner("src/test/data/erroriIdNumbers.txt");
		
		// ciao.
		//123.
		LexicalException e1 = assertThrows(LexicalException.class, () -> scanner.nextToken(), "LexicalException");
		assertEquals("no float", e1.getMessage());
		
		
		//.123
		LexicalException e2 = assertThrows(LexicalException.class, () -> scanner.nextToken(), "LexicalException");
		assertEquals("token non comincia per .", e2.getMessage());
		//123
		assertEquals("<INT,r:2,123>", scanner.nextToken().toString());
		//123.
		LexicalException e3 = assertThrows(LexicalException.class, () -> scanner.nextToken(), "LexicalException");
		assertEquals("no float", e3.getMessage());
		//asd
		assertEquals("<ID,r:3,asd>", scanner.nextToken().toString());
		//@
		LexicalException e4 = assertThrows(LexicalException.class, () -> scanner.nextToken(), "LexicalException");
		assertEquals("token non comincia per char non del l'alfabetto", e4.getMessage());
        
		//abcd6
		LexicalException e5 = assertThrows(LexicalException.class, () -> scanner.nextToken(), "LexicalException");
		assertEquals("token non comincia per char non del l'alfabetto", e5.getMessage());
		
		//@
		//assertThrows(LexicalException.class, () -> scanner.nextToken(), "LexicalException");
		
		//t = scanner.nextToken();	// 123.
		//assertThrows(LexicalException.class, () -> scanner.nextToken(), "LexicalException");
		
		// .123
		//assertThrows(LexicalException.class, () -> scanner.nextToken(), "LexicalException");
		
		//System.out.print("dd");
		// 123a
		//assertThrows(LexicalException.class, () -> scanner.nextToken(), "LexicalException");
		
		// 123.a
		//assertThrows(LexicalException.class, () -> scanner.nextToken(), "LexicalException");
	    
	}
	
} 