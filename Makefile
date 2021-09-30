# Programs
JC = javac
JA = java

TARGETS = employee shapes compression guessing

all: ${TARGETS}

employee:
	${JC} employee.java ; ${JA} employee_program	
guessing:
	${JC} guessing.java ; ${JA} guessing_program	
shapes:
	${JC} shapes.java ; ${JA} shapes_program	
compression:
	${JC} compression.java ; ${JA} compression_program	

clean:
	rm *.class

.PHONY: clean run
