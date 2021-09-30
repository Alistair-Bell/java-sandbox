# Programs

JC = javac
JV = java

SOURCES = compression.java guessing.java employee.java shapes.java
CLASSES = ${SOURCES:.java=.class}
OUT     = shapes_program

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
