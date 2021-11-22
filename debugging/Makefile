include ../config.mk

TARGETS = broken_code debugging
TARGET  ?= broken_code

all:
	${JC} ${TARGET}.java && ${JA} ${TARGET}_program

info:
	@echo "${TARGETS}"


clean:
	rm *.class
