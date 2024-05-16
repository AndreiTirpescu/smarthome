#include "config/config_api.h"
#include "config/locator/ServiceLocator.h"
#include "config/model/AppConfig.h"
#include "config/service/AppConfigLoader.h"
#include "config/service/ChangelogRunner.h"
#include "session/infrastructure/SessionNetworking.h"
#include "session/infrastructure/SessionRepository.h"
#include "session/service/SessionService.h"
#include "spdlog/sinks/stdout_color_sinks.h"
#include <memory>
#include <spdlog/spdlog.h>

namespace mdframework::config {
void initialize_library(const std::string& configPath)
{
    using SessionRepository = mdframework::session::infrastructure::SessionRepository;
    using SessionNetworking = mdframework::session::infrastructure::SessionNetworking;
    using SessionService = mdframework::session::service::SessionService;

    auto console = spdlog::stdout_color_mt("console");
    console->set_level(spdlog::level::level_enum::trace);
    spdlog::set_default_logger(console);

    std::unique_ptr<AppConfigLoader> configLoader = std::unique_ptr<AppConfigLoader>();
    const auto appConfig = configLoader->initializeConfig(configPath);

    ServiceLocator& locator = ServiceLocator::instance();

    locator.registerInstance<AppConfig>(new AppConfig(appConfig));
    locator.registerInstance<SessionRepository>(new SessionRepository(appConfig.dbPath()));
    locator.registerInstance<SessionNetworking>(new SessionNetworking(appConfig.baseUrl(), appConfig.getPort()));
    locator.registerInstance<SessionService>(
        new SessionService(locator.resolve<SessionRepository>(), locator.resolve<SessionNetworking>()));

    configLoader->initializeConfig(configPath);

    const auto changelogRunner = std::unique_ptr<ChangelogRunner>();
    locator.resolve<ChangelogRunner>()->runChangelogs();
}

void clear_library()
{
    spdlog::info("Clearing framework data");
    auto& locator = ServiceLocator::instance();
    locator.clear();
}
}
