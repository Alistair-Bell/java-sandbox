# Programs

JC = javac
JV = java

SOURCES = program.java
CLASSES = ${SOURCES:.java=.class}
OUT     = program

%.class: %.java
	@echo "javac     $<"
	@${JC} $<

all: run

run: ${CLASSES}
	@echo "java      ${OUT}"
	@echo "--------------"
	@${JV} ${OUT}

clean:
	rm *.class

.PHONY: clean run
