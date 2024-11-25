JAR_NAME = target/chess-game-1.0-SNAPSHOT.jar

MVN = mvn

JAVA = java

all: build

build:
	$(MVN) clean package

run: build
	$(JAVA) -jar $(JAR_NAME)

clean:
	$(MVN) clean

.PHONY: all build run clean
