# Variáveis globais
JAVAC := javac
JAVA := java
JAVA_FLAGS := --release 8 # Adapta a versão java que compila o programa (pode não ser adequada para todos)
SRC_DIR := src
UTILS_SRC := $(SRC_DIR)/utils/*.java
CLIENT_SRC := $(SRC_DIR)/client/*.java
SERVER_SRC := $(SRC_DIR)/server/*.java

ifeq ($(OS), Windows_NT)
	CLASSPATH := -cp $(SRC_DIR);lib/*
else
	CLASSPATH := -cp $(SRC_DIR):lib/*
endif

# Compilação

all: client server

utils:
	$(JAVAC) $(JAVA_FLAGS) $(CLASSPATH) $(UTILS_SRC)

client: utils
	$(JAVAC) $(JAVA_FLAGS) $(CLASSPATH) $(CLIENT_SRC)

server: utils
	$(JAVAC) $(JAVA_FLAGS) $(CLASSPATH) $(SERVER_SRC)

run_client:
	$(JAVA) $(CLASSPATH) client.Client

run_server:
	$(JAVA) $(CLASSPATH) server.Server

clean:
ifeq ($(OS), Windows_NT)
	del /Q $(SRC_DIR)\utils\*.class $(SRC_DIR)\client\*.class $(SRC_DIR)\server\*.class
else
	rm -f $(SRC_DIR)/utils/*.class $(SRC_DIR)/client/*.class $(SRC_DIR)/server/*.class
endif