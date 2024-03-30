cmake_minimum_required(VERSION 3.25)

target_sources(${PROJECT_NAME} PRIVATE
        deviceaccess/domain/AccessToken.h
        deviceaccess/domain/AccessToken.cpp
        deviceaccess/domain/IAccessTokenProvider.h
        deviceaccess/application/UserLoginComponent.h
        deviceaccess/application/UserLoginComponent.cpp
        devicesetup/domain/DeviceSetupConfig.h
        devicesetup/domain/DeviceSetupConfig.cpp
        devicesetup/application/DeviceSetupComponent.h
        devicesetup/application/DeviceSetupComponent.cpp
        devicesetup/domain/IDeviceSetupProvider.h
        navigation/NavigationComponent.h
        navigation/NavigationComponent.cpp
        )

target_include_directories(${PROJECT_NAME} PRIVATE deviceaccess/application deviceaccess/domain devicesetup/application devicesetup/domain)