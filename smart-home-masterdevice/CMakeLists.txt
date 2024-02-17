cmake_minimum_required(VERSION 3.25)
project(masterdevice)

set(CMAKE_CXX_STANDARD 20)
set(QT_VERSION 6)

set(CMAKE_CXX_STANDARD_REQUIRED ON)
set(CMAKE_PREFIX_PATH ~/Qt/6.6.2/gcc_64)

set(CMAKE_AUTOMOC ON)
set(CMAKE_AUTORCC ON)
set(CMAKE_AUTOUIC ON)

set(QT_REQUIRED_COMPONENTS Core Quick Sql VirtualKeyboard)
set(QT_LINK_LIBS Qt${QT_VERSION}::Core Qt${QT_VERSION}::Quick Qt${QT_VERSION}::Sql Qt${QT_VERSION}::VirtualKeyboard)

set(RESOURCES_PATH ${CMAKE_SOURCE_DIR}/resources)
set(RESOURCES_TARGET_DIR resources)
set(QRC_FILES main.qrc view/components/components.qrc view/pages/pages.qrc)
find_package(Qt${QT_VERSION} COMPONENTS ${QT_REQUIRED_COMPONENTS} REQUIRED)

add_executable(masterdevice main.cpp ${QRC_FILES})

add_subdirectory(features)
add_subdirectory(framework)
add_subdirectory(config)
add_subdirectory(mapper)
target_link_libraries(masterdevice ${QT_LINK_LIBS})

add_custom_command(
        TARGET ${PROJECT_NAME}
        POST_BUILD
        COMMAND ${CMAKE_COMMAND} -E copy ${RESOURCES_PATH}/changelogs ${RESOURCES_TARGET_DIR}/changelogs
        COMMENT "Copying..."
        DEPENDS ${RESOURCES_PATH}
)



