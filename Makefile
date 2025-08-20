JAVAC = javac
JAVA = java
JAR = jar

# Dirs
SRC_DIR = src
BIN_DIR = bin
OBJ_DIR = obj
DATA_DIR = data
DIST_DIR = dist
DOC_DIR = doc

# Arquivos fonte 
SOURCES = $(wildcard $(SRC_DIR)/*.java)
CLASSES = $(SOURCES:$(SRC_DIR)/%.java=$(BIN_DIR)/%.class)

# Nome do JAR
JAR_NAME = app.jar
MAIN_CLASS = Solver

# Flags
JFLAGS = -g -d $(BIN_DIR) -cp $(BIN_DIR)
JAR_FLAGS = cfme

.PHONY: all compile run jar clean help

all: compile jar

compile: $(CLASSES)

$(BIN_DIR)/%.class: $(SRC_DIR)/%.java
	@mkdir -p $(BIN_DIR)
	$(JAVAC) $(JFLAGS) $<

jar: compile
	@mkdir -p $(DIST_DIR)
	@echo "Main-Class: $(MAIN_CLASS)" > Manifest.txt
	@echo "Class-Path: ." >> Manifest.txt
	$(JAR) $(JAR_FLAGS) $(DIST_DIR)/$(JAR_NAME) Manifest.txt -C $(BIN_DIR) .
	@rm -f Manifest.txt

run: compile
	$(JAVA) -cp $(BIN_DIR) $(MAIN_CLASS) $(DATA_DIR)

runjar: jar
	$(JAVA) -jar $(DIST_DIR)/$(JAR_NAME) $(DATA_DIR)

run-with-data: compile
	$(JAVA) -cp $(BIN_DIR) $(MAIN_CLASS) $(DATA_DIR)/client.txt $(DATA_DIR)/imoveis.txt

clean:
	rm -rf $(BIN_DIR) $(OBJ_DIR) Manifest.txt

distclean: clean
	rm -rf $(DIST_DIR)

help:
	@echo "Targets disponíveis:"
	@echo "  all       - Compila e cria JAR (padrão)"
	@echo "  compile   - Apenas compila"
	@echo "  jar       - Cria JAR"
	@echo "  run       - Executa programa compilado"
	@echo "  runjar    - Executa JAR"
	@echo "  clean     - Remove arquivos compilados"
	@echo "  distclean - Limpeza completa"
	@echo "  help      - Esta mensagem"
