all: bin/main_ex1 bin/test_ex1

LIBS = src/sort_library.h ../C_resource/unity.h

INCLUDES = src/*.h

COMMON_DEPS = $(INCLUDES) Makefile

build/%.o:	src/%.c	$(COMMON_DEPS)
	gcc -c $< -o $@

build/%.o:	../C_resource/%.c	$(COMMON_DEPS)
	gcc -c $< -o $@

bin/main_ex1: src/sort_algo_main.c src/sort_library.c $(LIBS)
	gcc -o bin/main_ex1 src/sort_algo_main.c src/sort_library.c -I src/

bin/test_ex1: src/sort_algo_test.c src/sort_library.c ../C_resource/unity.c $(LIBS)
	gcc -g -o bin/test_ex1 src/sort_algo_test.c src/sort_library.c ../C_resource/unity.c -I ../C_resource/ -I src/



