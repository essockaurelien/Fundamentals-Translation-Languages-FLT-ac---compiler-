package test;

import static junit.framework.TestCase.fail;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;

import Parser.Parser;
import Parser.SyntacticException;
import scanner.LexicalException;
import scanner.Scanner;

public class TestParser {
	
	String fileParser = System.getProperty("user.dir") + "/fileParser.txt";
	String fileParser2 = System.getProperty("user.dir") + "/fileParser2.txt";
	
	String fileParserWrong1 = System.getProperty("user.dir") + "/testVisitor/testParser/fileParserWrong1.txt";
	String fileParseCorrect2 = System.getProperty("user.dir") + "/testVisitor/testParser/fileParserCorrect2.txt";
	String fileParseCorrect3 = System.getProperty("user.dir") + "/testVisitor/testParser/fileParserCorrect3.txt";
	String fileParserWrong2 = System.getProperty("user.dir") + "/testVisitor/testParser/fileParserWrong2.txt";
	String fileScannerCorrect1 = System.getProperty("user.dir") + "/testVisitor/testParser/fileScannerCorrect1.txt";
	
	
	/*
	 * int float;
	 * float = 6.7;
	 * */
	@Test
	public void testParseWrong1() throws SyntacticException, LexicalException, IOException {
		
		try {
			Scanner scanner;
			Parser parser;
		scanner = new Scanner(fileParserWrong1);
		parser = new Parser(scanner);
		assertThrows(SyntacticException.class, () -> parser.parsePrg(), "Errore Sintatico");
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
	}
	
	
	//print float;
	 
	@Test
	public void testParseWrong2() throws SyntacticException, LexicalException, IOException {
		try {
			Scanner scanner;
			Parser parser;
			scanner = new Scanner(fileParserWrong2);
			parser = new Parser(scanner);
			try {
				parser.parsePrg();
			} catch (Exception e) {
				assert(e instanceof SyntacticException);
			}
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
	}
	
	/*
	 * print stampa;
	 * float numberFloat;
	 * int floatI;
	 * a = 5 + 3;
	 * b = a + 5;
	 * */
	@Test
	public void testParseCorrect2() throws SyntacticException, LexicalException, IOException {
		try {
			Scanner scanner;
			Parser parser;
			scanner = new Scanner(fileParseCorrect2);
			parser = new Parser(scanner);
			try {
				parser.parsePrg();
			} catch (Exception e) {
				assert(e instanceof SyntacticException);
			}
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
	}
	
	/*int num;
	* num = 5;
	* num = id;
	* num = id + 5.0;
	* num = id * 5;
	* num = id * id;
	* num = id + 5 - 8 * 6.0 / 2;
	* num = id * 5 - 8.0 * 6 + 2;
	* print ok;
	**/
	@Test
	public void testParseCorrect3() throws SyntacticException, LexicalException, IOException {
		try {
			Scanner scanner;
			Parser parser;
			scanner = new Scanner(fileParseCorrect3);
			parser = new Parser(scanner);
			try {
				parser.parsePrg();
			} catch (Exception e) {
				assert(e instanceof SyntacticException);
			}
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}      
	}
	
	/*
	 * int num = 5;
	 * 5 + 13.0;
	 **/
	
	@Test
	public void testScannerCorrect1() throws SyntacticException, LexicalException, IOException {
		try {
			Scanner scanner;
			Parser parser;
			scanner = new Scanner(fileScannerCorrect1);
			parser = new Parser(scanner);
			try {
				parser.parsePrg();
			} catch (Exception e) {
				assert(e instanceof SyntacticException);
			}
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
	}
	
	@Test
	public void testParse() throws SyntacticException, LexicalException {
		try {
			Scanner scanner;
			Parser parser;
			scanner = new Scanner(fileParser);
			parser = new Parser(scanner);
			try {
				parser.parsePrg();
			} catch (Exception e) {
				assert(e instanceof SyntacticException);
			}
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
	}
	
	@Test
	public void testParse2() throws SyntacticException, LexicalException {
		
		try {
			Scanner scanner;
			Parser parser;
			scanner = new Scanner(fileParser2);
			parser = new Parser(scanner);
			try {
				parser.parsePrg();
			} catch (Exception e) {
				assert(e instanceof SyntacticException);
			}
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
	}
	
}

