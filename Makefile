# Include the config.mk file, this tells the paths where our java stuff is.
include config.mk

TARGETS = employee shapes compression guessing sequence_reverse dice_roll blackjack \
	treasure_game check_digit hangman pizza methods match4

TARGET ?= match4

all: 
	${JC} ${TARGET}.java && ${JA} ${TARGET}_program

info:
	@echo ${TARGETS}
	@echo "Run make TARGET='program'"

clean:
	rm **.class

.PHONY: clean run
