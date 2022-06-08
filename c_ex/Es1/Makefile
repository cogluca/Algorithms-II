all: bin/sort_algo_main bin/sort_algo_test

LIBS = src/sort_library.h ../C_resource/unity.h

bin/sort_algo_main: src/sort_algo_main.c src/sort_library.c $(LIBS)
	clang -o bin/sort_algo_main src/sort_algo_main.c src/sort_library.c -I src/

bin/sort_algo_test: src/sort_algo_test.c src/sort_library.c ../C_resource/unity.c $(LIBS)
	gcc -g -o bin/sort_algo_test src/sort_algo_test.c src/sort_library.c ../C_resource/unity.c -I ../C_resource/ -I src/



