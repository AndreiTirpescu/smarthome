cmake_minimum_required(VERSION 3.25)

target_sources(${PROJECT_NAME}
        PRIVATE
        deviceaccess/UserLoginComponent.h
        deviceaccess/UserLoginComponent.cpp
        navigation/NavigationComponent.h
        navigation/NavigationComponent.cpp
        devicesetup/DeviceSetupComponent.h
        devicesetup/DeviceSetupComponent.cpp
)

target_include_directories(${PROJECT_NAME} PRIVATE deviceaccess navigation)