target_sources(${PROJECT_NAME} PRIVATE
        domain/AccessToken.h
        domain/AccessToken.cpp
        domain/IAccessTokenProvider.h
        framework/SqliteAccessTokenProvider.h
        framework/SqliteAccessTokenProvider.cpp
        )

target_include_directories(${PROJECT_NAME} PRIVATE domain application framework)