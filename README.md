Essock Aurelien(readMe FLT2)

# Compilatore da AC a DC  
**Corso**: Fondamenti, Linguaggi e Traduttori  
**Autore**: [Essock Aurelien]  

---

## 📋 Descrizione del Progetto  
Questo progetto implementa un compilatore che traduce programmi scritti nel linguaggio **AC** (un linguaggio semplice con tipi `int` e `float`) in codice eseguibile per il linguaggio **DC** (un calcolatore stack-based in notazione polacca inversa).  

### Caratteristiche principali:  
- Analisi lessicale e sintattica del codice AC.  
- Type checking per garantire la correttezza semantica.  
- Generazione di codice DC ottimizzato.  

---

## 🛠️ Struttura del Progetto  
Il progetto è sviluppato in **Java** con Eclipse e organizza le classi come segue:  

### Classi Principali:  
- **`Scanner`**: Analisi lessicale (tokenizzazione del codice AC).  
- **`Parser`**: Analisi sintattica e costruzione dell'AST (Abstract Syntax Tree).  
- **`TypeCheckingVisitor`**: Verifica dei tipi (controllo semantico).  
- **`CodeGeneratorVisitor`**: Generazione del codice DC dall'AST.  

### Supporto:  
- **`Token`**: Rappresentazione dei token rilevati dallo scanner.  
- **`AST`**: Struttura dati per l'albero sintattico astratto.  
- **`SymbolTable`**: Tabella dei simboli per gestire dichiarazioni e scope.  

---

## 📚 Linguaggio AC (Input)  
### Sintassi:  
- **Dichiarazioni**: `int nomeVariabile;` o `float nomeVariabile;`  
- **Assegnamento**: `variabile = espressione;`  
- **Stampa**: `print variabile;`  

### Esempio di codice AC:  
```ac  
int tempa;  
tempa = 5;  
float tempb;  
tempb = tempa + 32;  
print tempb;


## 📚 Linguaggio DC (Output)
caratteristiche:
** Notazione inversa
** Istruzioni stack-based(es. 5 sa 0 k salva il valore 5 nella variabile a).

esempio codice dc:
  5 sa 0 k  
  la 5 k 32 + sb0k  
  lb p P

Configurazione e Esecuzione.
Requisiti:
  ** java JDK 11+
  ** Eclipse IDE(opzionale)

Compilazione ed Esecuzione:
 - Importa il progetto in Eclipse:
   File → Import → Existing Projects into Workspace.
 - Esegui il compilatore:
   Il main si trova in Main.java.
Fornisci un file di input AC (es. input.ac).
Output:
Il codice DC generato verrà salvato in output.dc.

Esecuzione da Terminale(bash):
 javac -d bin src/*.java  
 java -cp bin Main input.ac output.dc

Test.

il progetto include test per verificare:
  - Correttezza lessicale/Sintatica.
  - Type checking (esempio: int-> float)
  -Generazione di codice DC coerente.

Esegui Test.

  -Usa i file di test nella cartella test/

📝 Note
La tabella dei simboli gestisce lo scope delle variabili.
Le conversioni di tipo sono gestite automaticamente solo da int a float.
Il progetto Cup(compilatore DC) lo trtovate in un altro repository di questo stesso account FLT2_compilatore_DC

👥 Crediti
Progetto sviluppato per il corso Fondamenti, Linguaggi e Traduttori, Università degli Studi di Piemonte Orientale.
Docente: Prof.ssa Paola Giannini.
