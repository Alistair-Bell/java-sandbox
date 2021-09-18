# Programs

JC = javac
JV = java

SOURCES = compression.java guessing.java
CLASSES = ${SOURCES:.java=.class}
OUT     = program

%.class: %.java
	@echo "javac     $<"
	@${JC} -Werror $<

all: run

run: ${CLASSES}
	@echo "java      ${OUT}"
	@echo "--------------"
	@${JV} ${OUT}

clean:
	rm *.class

.PHONY: clean run
