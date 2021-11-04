# Programs
JC = javac
JA = java

TARGETS = employee shapes compression guessing sequence_reverse dice_roll blackjack treasure_game check_digit hangman pizza

all: info
info:
	@echo "${TARGETS}"

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
blackjack:
	${JC} blackjack.java && ${JA} blackjack_program
treasure_game:
	${JC} treasure_game.java && ${JA} treasure_game_program
check_digit:
	${JC} check_digit.java && ${JA} check_digit_program
hangman:
	${JC} hangman.java && ${JA} hangman_program
pizza:
	${JC} pizza.java && ${JA} pizaa_program

clean:
	rm *.class

.PHONY: clean run
