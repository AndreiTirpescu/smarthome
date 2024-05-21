cmake_minimum_required(VERSION 3.22)
project(mdframework VERSION 1.0.0 DESCRIPTION "Master Device Framework")

set(CMAKE_CXX_STANDARD 20)
set(CMAKE_POSITION_INDEPENDENT_CODE ON)

find_package(yaml-cpp CONFIG REQUIRED)
find_package(fmt CONFIG REQUIRED)
find_package(simpleble CONFIG REQUIRED)
find_package(SQLiteCpp CONFIG REQUIRED)
find_package(nlohmann_json CONFIG REQUIRED)
find_package(jwt-cpp CONFIG REQUIRED)

add_library(${PROJECT_NAME} SHARED)

target_sources(${PROJECT_NAME}
        PRIVATE
        src/config/api/config_api.cpp
        src/config/locator/ServiceLocator.h
        src/config/model/AppConfig.cpp
        src/config/model/AppConfig.h
        src/config/service/AppConfigLoader.cpp
        src/config/service/AppConfigLoader.h
        src/config/service/ChangelogRunner.cpp
        src/config/service/ChangelogRunner.h
        src/device/model/Device.cpp
        src/device/model/Device.h
        src/device/api/device_api.cpp
        src/device/service/DeviceSetupService.cpp
        src/device/service/DeviceSetupService.h
        src/device/infrastructure/MasterDeviceRepository.cpp
        src/device/infrastructure/MasterDeviceRepository.h
        src/device/infrastructure/HomeSystemNetworking.cpp
        src/device/infrastructure/HomeSystemNetworking.h
        src/device/infrastructure/HomeSystemResponse.h
        src/device/infrastructure/DevicesResponse.h
        src/exceptions/exception.cpp
        src/session/api/session_api.cpp
        src/session/infrastructure/LoginResponse.h
        src/session/infrastructure/SessionNetworking.cpp
        src/session/infrastructure/SessionNetworking.h
        src/session/infrastructure/SessionRepository.cpp
        src/session/infrastructure/SessionRepository.h
        src/session/model/User.cpp
        src/session/model/User.cpp
        src/session/model/User.h
        src/session/model/User.h
        src/session/service/SessionService.cpp
        src/session/service/SessionService.h
)

target_include_directories(
        ${PROJECT_NAME}
        PRIVATE
        src
        PUBLIC
        PUBLIC
        $<BUILD_INTERFACE:${CMAKE_CURRENT_SOURCE_DIR}/include>
        $<INSTALL_INTERFACE:${CMAKE_INSTALL_INCLUDEDIR}>
)

target_link_libraries(${PROJECT_NAME} PRIVATE yaml-cpp::yaml-cpp)
target_link_libraries(${PROJECT_NAME} PRIVATE fmt::fmt)
target_link_libraries(${PROJECT_NAME} PRIVATE SQLiteCpp)
target_link_libraries(${PROJECT_NAME} PRIVATE nlohmann_json::nlohmann_json)
target_link_libraries(${PROJECT_NAME} PRIVATE simpleble::simpleble)
target_link_libraries(${PROJECT_NAME} PRIVATE jwt-cpp::jwt-cpp)

set(public_headers
        include/config/config_api.h
        include/device/device_api.h
        include/device/master_device_data.h
        include/exceptions/exception.h
        include/session/session_api.h
        include/session/session_data.h
)


set_target_properties(${PROJECT_NAME} PROPERTIES PUBLIC_HEADER "${public_headers}")
set_target_properties(${PROJECT_NAME} PROPERTIES DEBUG_POSTFIX "d")


# install instructions
include(GNUInstallDirs)
include(CMakePackageConfigHelpers)
install(TARGETS ${PROJECT_NAME}
        EXPORT "${PROJECT_NAME}Targets"
        PUBLIC_HEADER DESTINATION ${CMAKE_INSTALL_INCLUDEDIR}/${PROJECT_NAME}
        INCLUDES DESTINATION ${CMAKE_INSTALL_INCLUDEDIR})

install(EXPORT "${PROJECT_NAME}Targets"
        FILE "${PROJECT_NAME}Targets.cmake"
        NAMESPACE ${PROJECT_NAME}::
        DESTINATION cmake)
write_basic_package_version_file(
        "${CMAKE_CURRENT_BINARY_DIR}/${PROJECT_NAME}ConfigVersion.cmake"
        VERSION "${version}"
        COMPATIBILITY AnyNewerVersion
)
configure_package_config_file(${CMAKE_CURRENT_SOURCE_DIR}/Config.cmake.in
        "${CMAKE_CURRENT_BINARY_DIR}/${PROJECT_NAME}Config.cmake"
        INSTALL_DESTINATION cmake
)
install(FILES
        "${CMAKE_CURRENT_BINARY_DIR}/${PROJECT_NAME}Config.cmake"
        "${CMAKE_CURRENT_BINARY_DIR}/${PROJECT_NAME}ConfigVersion.cmake"
        DESTINATION cmake
)