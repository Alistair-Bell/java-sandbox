# Programs
JC = javac
JA = java

TARGETS = employee shapes compression guessing sequence_reverse dice_roll

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
dice_roll:
	${JC} dice_roll.java && ${JA} dice_roll_program


clean:
	rm *.class

.PHONY: clean run
