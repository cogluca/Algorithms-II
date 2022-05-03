CC=gcc

DEPS = src/*.h

all: bin/sort_algo_main build/sort_library.o bin/prova

%.o: %.c $(DEPS)
	$(CC) -c -o $@ $< $

clean:
	$(RM) -rf bin/*
	$(RM) -rf build/*

bin/prova: src/prova.c build/sort_library.o
	$(CC) -o bin/prova src/prova.c src/sort_library.c

build/sort_library.o: src/sort_library.c 
	$(CC) -c src/sort_library.c -o build/sort_library.o

bin/sort_algo_main: src/sort_algo_main.c build/sort_library.o 
	$(CC) -o bin/sort_algo_main src/sort_algo_main.c src/sort_library.c
