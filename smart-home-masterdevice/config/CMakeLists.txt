target_sources(${PROJECT_NAME} PRIVATE
        DbConnection.h
        DbConnection.cpp
        ChangelogRunner.h
        ChangelogRunner.cpp
        HomeSystemApplicationConfig.h
        HomeSystemApplicationConfig.cpp
        )

target_include_directories(${PROJECT_NAME} PRIVATE .)