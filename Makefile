JAVA=java
JAVAC=javac
JFLEX=$(JAVA) -jar jflex-full-1.8.2.jar
CUPJAR=./java-cup-11b.jar
CUP=$(JAVA) -jar $(CUPJAR)
CP=.:$(CUPJAR)

default: all

.SUFFIXES: $(SUFFIXES) .class .java

.java.class:
	$(JAVAC) -cp $(CP) $*.java

FILE=	Lexer.java parser.java sym.java \
	LexerRules.java ParserTest.java \
	Program.java Fielddecl.java Expr.java Stmt.java \
	Name.java BinaryOp.java BaseToken.java

all: parserTests

parserTests: build
	@rm -f parserTestOutputs.txt;
	@for f in ./testfiles/*.as; do \
		echo "Processing file $$f"; \
		echo "\\*_____ $$f _____*\\" >> parserTestOutputs.txt; \
		$(JAVA) -cp $(CP) ParserTest $$f >> parserTestOutputs.txt; \
	done;
	@cat -n parserTestOutputs.txt

lexerTests: build
	@rm -f lexerTestOutputs.txt;
	@for f in ./testfiles/*; do \
		echo "Processing file $$f"; \
		echo "\\*_____ $$f _____*\\" >> lexerTestOutputs.txt; \
		$(JAVA) -cp $(CP) LexerRules $$f >> lexerTestOutputs.txt; \
	done;
	@cat -n lexerTestOutputs.txt

build: Lexer.java parser.java $(FILE:java=class)

dump: Lexer.java parserD.java $(FILE:java=class)

clean:
	rm -f *.class *.bak Lexer.java parser.java sym.java *.txt

Lexer.java: grammar.jflex
	$(JFLEX) grammar.jflex

parser.java: tokens.cup
	$(CUP) -interface < tokens.cup

parserD.java: tokens.cup
	$(CUP) -interface -dump < tokens.cup


