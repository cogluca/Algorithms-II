all: bin/main_ex2 bin/test_ex2

LIBS = src/skip_list.h ../C_resource/unity.h

INCLUDES = src/*.h

COMMON_DEPS = $(INCLUDES) Makefile

build/%.o:	src/%.c	$(COMMON_DEPS)
	gcc -c $< -o $@

build/%.o:	../C_resource/%.c	$(COMMON_DEPS)
	gcc -c $< -o $@

bin/test_ex2: src/skip_list_test.c build/skip_list.o build/unity.o	$(LIBS)
	gcc -g -o bin/test_ex2 src/skip_list_test.c src/skip_list.c ../C_resource/unity.c -I ../C_resource/ -I src/

bin/main_ex2: src/skip_list_main.c build/skip_list.o $(LIBS)
	gcc -g -o bin/main_ex2 src/skip_list_main.c src/skip_list.c -I src/