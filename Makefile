# Variáveis globais
JAVAC := javac
JAVA := java
SRC_DIR := src
UTILS_SRC := $(SRC_DIR)/utils/*.java
CLIENT_SRC := $(SRC_DIR)/client/*.java
SERVER_SRC := $(SRC_DIR)/server/*.java
CLASSPATH := -cp $(SRC_DIR):lib/*

# Compilação

all: client server

utils:
	$(JAVAC) $(CLASSPATH) $(UTILS_SRC)

client: utils
	$(JAVAC) $(CLASSPATH) $(CLIENT_SRC)

server: utils
	$(JAVAC) $(CLASSPATH) $(SERVER_SRC)

run_client:
	$(JAVA) $(CLASSPATH) client.Client

run_server:
	$(JAVA) $(CLASSPATH) server.Server

clean:
	rm -f $(UTILS_SRC)/*.class $(CLIENT_SRC)/*.class $(SERVER_SRC)/*.class