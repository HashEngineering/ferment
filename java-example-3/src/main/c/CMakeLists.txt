cmake_minimum_required(VERSION 3.10)
SET(CMAKE_CXX_STANDARD 17)
SET(CMAKE_CXX_STANDARD_REQUIRED ON)
SET(BUILD_MODE Debug)
SET(CMAKE_CXX_FLAGS "-Wall")
SET(CMAKE_CXX_FLAGS_DEBUG "-g")
SET(CMAKE_CXX_FLAGS_RELEASE "-O3")
project(sdk)

# Define the C source file
set(C_SOURCE_FILE sdk.cpp)

# Define the pre-built Rust library
set(RUST_LIBRARY ${CMAKE_SOURCE_DIR}/../../../../target/debug/libferment_example_nested.a)
find_package(JNI REQUIRED)
include_directories(${JNI_INCLUDE_DIRS})
# Create an executable that links C and Rust code
add_library(sdklib SHARED ${C_SOURCE_FILE})

# Link the pre-built Rust library
target_link_libraries(sdklib PRIVATE ${RUST_LIBRARY})