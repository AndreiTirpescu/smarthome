cmake_minimum_required(VERSION 3.25)
project(masterdevice)

set(CMAKE_CXX_STANDARD 20)
set(QT_VERSION 6)

set(CMAKE_CXX_STANDARD_REQUIRED ON)
set(CMAKE_PREFIX_PATH ~/Qt/6.6.2/gcc_64)

set(CMAKE_AUTOMOC ON)
set(CMAKE_AUTORCC ON)
set(CMAKE_AUTOUIC ON)

set(QT_REQUIRED_COMPONENTS Core Quick Sql VirtualKeyboard Positioning)
set(QT_LINK_LIBS Qt${QT_VERSION}::Core Qt${QT_VERSION}::Quick Qt${QT_VERSION}::Positioning Qt${QT_VERSION}::VirtualKeyboard)

set(RESOURCES_PATH ${CMAKE_SOURCE_DIR}/resources)
set(RESOURCES_TARGET_DIR resources)
set(QRC_FILES main.qrc view/components/components.qrc view/pages/pages.qrc view/assets/assets.qrc)
find_package(Qt${QT_VERSION} COMPONENTS ${QT_REQUIRED_COMPONENTS} REQUIRED)


if (NOT TARGET mdframework)
    add_subdirectory(mdframework)
endif ()
include_directories(mdframework)

add_executable(${PROJECT_NAME} main.cpp ${QRC_FILES})

add_subdirectory(features)

target_link_libraries(${PROJECT_NAME} PRIVATE mdframework ${QT_LINK_LIBS})
