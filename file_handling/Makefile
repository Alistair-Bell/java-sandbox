include ../config.mk

TARGETS = adaptation bookstore login

TARGET ?= bookstore

all:
	$(JC) ${TARGET}.java && $(JA) ${TARGET}_program

info:
	@echo ${TARGETS}

clean:
	rm *.class
	rm *.txt

.PHONY: clean
