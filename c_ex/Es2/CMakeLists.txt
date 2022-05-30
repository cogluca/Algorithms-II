CMAKE_MINIMUM_REQUIRED(VERSION 3.13)

project(Algoritmi21-22)


add_executable(dataset src/skip_list_main.c
        src/skip_list.c
        )

add_executable(basic_test src/skip_list_test.c
        src/skip_list.c
        ../C_resource/unity.c
        )