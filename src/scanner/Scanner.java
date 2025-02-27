
package scanner;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PushbackReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import token.*;


public class Scanner {
  
	Token Tokencorrente  = null;
	
	//private static  char[] StringAp = null;
	final char EOF = (char) -1; // int 65535
	private int riga;
	private PushbackReader buffer;  //serve per fare le letture readChar e peekChar
	//private String log; // appendiamo eventuali segnalazioni di errore

	private List<Character> skipChars; // ' ', '\n', '\t', '\r', EOF	
	private List<Character> letters; // 'a',...'z'
	private List<Character> numbers; // '0',...'9'
	
	private HashMap<Character, TokenType> operatorsMap;  //'+', '-', '*', '/', '=', ';'
	private HashMap<String, TokenType> keyWordsMap;  //"print", "float", "int"
	
	//String stringAppoggio = "" ;
	//String stringAppoggioN = "" ;
	//boolean contains;
	//String punto = " ";
	//String confronto = ".";
	//int i=0;
	
	public Scanner(String fileName) throws FileNotFoundException{
		this.buffer = new PushbackReader(new FileReader(fileName));
		riga = 1;
		//chiama metodo per inizializzare liste e HashMaps
		letters = Arrays.asList('a', 'b', 'c', 'd', 'e','f', 'g', 'h', 'i', 'j', 'k',
								'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
								'w', 'x', 'y', 'z');
		skipChars = Arrays.asList(' ','\n','\t','\r',EOF);
		numbers = Arrays.asList('0', '1', '2', '3', '4', '5', '6', '7', '8', '9');
		
		
		operatorsMap = new HashMap<Character, TokenType>();
		keyWordsMap = new HashMap<String, TokenType>();
		
		operatorsMap.put('+', TokenType.PLUS);
		operatorsMap.put('-', TokenType.MINUS);
		operatorsMap.put('*', TokenType.TIMES);
		operatorsMap.put('/', TokenType.DIV);
		operatorsMap.put('=', TokenType.ASSIGN);
		operatorsMap.put(';', TokenType.SEMI);
		
		keyWordsMap.put("print", TokenType.PRINT);
		keyWordsMap.put("float", TokenType.TYFLOAT);
		keyWordsMap.put("int", TokenType.TYINT);
		
	}

	public Token peekToken() throws LexicalException, IOException{
		 Tokencorrente=nextToken(); 
	     return Tokencorrente ;
		
	}
	
	public Token nextToken() throws IOException, LexicalException {

		if(Tokencorrente  != null){
			Token tmpToken = Tokencorrente ;
			Tokencorrente  = null;
			return tmpToken;
		}		

		// nextChar contiene il prossimo carattere dell'input.
		char nextChar = peekChar();
		
		// ciclo unico per rientrare a sfruttare il file dato dopo aver restituito un numero n di token
	
		// Avanza nel buffer leggendo i carattere in skipChars
			
		while(skipChars.contains(nextChar)) {
			readChar();
		// incrementando riga se leggi '\n'
			
		 if(nextChar== '\n') {		 
           riga++;			 
		 }
       // Se raggiungi la fine del file ritorna il Token EOF ; ok vero totologia perche il primo while lo fa già 
		 
		 if(nextChar == EOF) {
			 return new Token(TokenType.EOF, riga);
		 }
		 // aggiorno il next char (o andiamo avanti )
		   nextChar = peekChar();
		}
		
		
		// Se nextChar e' in operators
		// ritorna il Token associato con l'operatore o il delimitatore
				
		if(operatorsMap.get(nextChar) != null) {
			readChar();
			return new Token(operatorsMap.get(nextChar), riga);
			
		
			
			// Se nextChar è in letters 
		    // return scanId()
		    // che legge tutte le lettere minuscole e ritorna un Token ID o
		   // il Token associato Parola Chiave (per generare i Token per le
		   // parole chiave usate l'HaskMap di corrispondenza
			
		}else if(letters.contains(nextChar)){
			      return scanId(nextChar);
			
		// Se nextChar e' in numbers
		// return scanNumber()
		// che legge sia un intero che un float e ritorna il Token INUM o FNUM
		// i caratteri che leggete devono essere accumulati in una stringa
		// che verra' assegnata al campo valore del Token
			
			}else if(numbers.contains(nextChar)) {
			    return scanNumbers(nextChar);
			} else if(nextChar == '.') {
				readChar();
				nextChar = peekChar();
				
				throw new LexicalException("token non comincia per .");
			}else {
				throw new LexicalException("token non comincia per char non del l'alfabetto");
			}
			
			// Altrimenti il carattere NON E' UN CARATTERE LEGALE   : ecc
			//return null;
		//throw new LexicalException("non buona");		
	}
	
	// private Token scanId()
	private Token scanId(char nextChar) throws IOException {
		String stringAppoggio = "";
		  
		while(letters.contains(nextChar)){
		stringAppoggio += nextChar;
		readChar();
		nextChar = peekChar();
		}

		if(keyWordsMap.get(stringAppoggio) != null) {  
			return new Token(keyWordsMap.get(stringAppoggio), riga);
		}
		//throw new LexicalException("non'è zero");
		return new Token(TokenType.ID, riga, stringAppoggio);
	}

	// private Token scanNumber()
	private Token scanNumbers(char nextChar) throws IOException, LexicalException {
		String stringAppoggioN = "";
		
		if(nextChar == '0') {
			stringAppoggioN += nextChar;
			readChar();
			nextChar = peekChar();
		
		   if(numbers.contains(nextChar)) {
			   throw new LexicalException("non'è zero");
		   }else {
			   return new Token(TokenType.INT, riga, stringAppoggioN);
		    }
	    }   
		while(numbers.contains(nextChar)){
		    
			
			stringAppoggioN += nextChar;
			 readChar();
			 nextChar = peekChar();
		}

		if( nextChar != '.') {
			 return new Token(TokenType.INT, riga, stringAppoggioN); 
		}else if(nextChar == '.'){
			stringAppoggioN += readChar();
			nextChar = peekChar();
			int i = 0;
			
			if(!numbers.contains(nextChar)) {
				//throw new LexicalException();
				throw new LexicalException("no float");
			}
			while(numbers.contains(nextChar)){
				stringAppoggioN += nextChar;
				readChar();
				nextChar = peekChar();
				i++;	
			}if(i<5) {
				return new Token(TokenType.FLOAT, riga, stringAppoggioN);
			}else {
				//throw new LexicalException(); 
				throw new LexicalException("float no legale");
			}
			
			
		}
		throw new LexicalException("no token .");
		// eccezione	
		//throw new LexicalException( "stringa non valida");
	}     

		private char readChar() throws IOException {
			return ((char) this.buffer.read());
		}
	
		private char peekChar() throws IOException {
			char c = (char) buffer.read();
			buffer.unread(c);
			return c;
		}
		
		
	
}