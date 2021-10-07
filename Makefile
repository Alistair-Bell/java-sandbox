# Programs
JC = javac
JA = java

TARGETS = employee shapes compression guessing sequence_reverse

all: info
info:
	@echo "targets: ${TARGETS}"

employee:
	${JC} employee.java && ${JA} employee_program	
guessing:
	${JC} guessing.java && ${JA} guessing_program	
shapes:
	${JC} shapes.java && ${JA} shapes_program	
compression:
	${JC} compression.java && ${JA} compression_program	
sequence_reverse:
	${JC} sequence_reverse.java && ${JA} sequence_reverse_program


clean:
	rm *.class

.PHONY: clean run
