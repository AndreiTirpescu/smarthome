target_sources(${PROJECT_NAME} PRIVATE
        DbConnection.h
        DbConnection.cpp
        ChangelogRunner.h
        ChangelogRunner.cpp
        HomeSystemApplicationConfig.h
        HomeSystemApplicationConfig.cpp
        NetworkRequestBuilder.h
        NetworkRequestBuilder.cpp
        dto/ErrorResponse.h
        )

target_include_directories(${PROJECT_NAME} PRIVATE . dto)