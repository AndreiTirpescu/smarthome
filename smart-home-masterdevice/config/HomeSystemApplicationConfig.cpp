#include "HomeSystemApplicationConfig.h"

void config::HomeSystemApplicationConfig::runChangelogsOnStartup(config::DbConnectionPtr dbConnection)
{
    auto changelogRunner = config::ChangelogRunner(dbConnection, path);
    changelogRunner.runChangelogs();
}

void config::HomeSystemApplicationConfig::applyFont()
{
    QFont workSansFont { "WorkSans" };
    workSansFont.setHintingPreference(QFont::HintingPreference::PreferNoHinting);
    workSansFont.setStyleStrategy(QFont::StyleStrategy::PreferQuality);
    QGuiApplication::setFont(workSansFont);
}

std::string config::HomeSystemApplicationConfig::getEnv(const std::string& envVar)
{
    return QProcessEnvironment::systemEnvironment().value(envVar.c_str()).toStdString();
}
