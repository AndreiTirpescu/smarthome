cmake_minimum_required(VERSION 3.25)

target_sources(${PROJECT_NAME} PRIVATE
        deviceaccess/domain/AccessToken.h
        deviceaccess/domain/AccessToken.cpp
        deviceaccess/domain/IAccessTokenProvider.h
        deviceaccess/application/UserLoginComponent.h
        deviceaccess/application/UserLoginComponent.cpp
        )

target_include_directories(${PROJECT_NAME} PRIVATE deviceaccess/usecases deviceaccess/domain)