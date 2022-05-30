CMAKE_MINIMUM_REQUIRED(VERSION 3.13)

project(example)


add_executable(prova src/skip_list_main.c
        src/skip_list.c
        )

add_executable(test src/skip_list_test.c
        src/skip_list.c
        ../C_resource/unity.c
        )