#include "SessionRepository.h"
#include "exceptions/exception.h"
#include "session/session_data.h"
#include <SQLiteCpp/Database.h>
#include <spdlog/spdlog.h>

using session_data = mdframework::session::session_data;

mdframework::session::infrastructure::SessionRepository::SessionRepository(std::string dbPath)
    : dbPath(std::move(dbPath))
{
}

std::shared_ptr<session_data> mdframework::session::infrastructure::SessionRepository::findAvailable()
{
    const SQLite::Database db(dbPath, SQLite::OPEN_READWRITE);
    SQLite::Statement query(
        db, "SELECT access_token, refresh_token, token_expires_at, user_id, email FROM session WHERE id = 1");

    int result = query.executeStep();

    if (!result) {
        spdlog::info("[SessionRepository] No session data available");
        return nullptr;
    }

    return std::make_shared<session_data>(query.getColumn(0).getString(), query.getColumn(1).getString(),
        query.getColumn(2).getInt(), query.getColumn(3).getString(), query.getColumn(4).getString());
}

std::shared_ptr<session_data> mdframework::session::infrastructure::SessionRepository::store(
    const mdframework::session::session_data& sessionData)
{
    const SQLite::Database db(dbPath, SQLite::OPEN_READWRITE);
    SQLite::Statement saveSession(db,
        "INSERT INTO session (access_token, refresh_token, token_expires_at, user_id, email) VALUES(?, ?, ?, ?, ?)");

    saveSession.bind(1, sessionData.accessToken);
    saveSession.bind(2, sessionData.refreshToken);
    saveSession.bind(3, sessionData.tokenExpiresAt);
    saveSession.bind(4, sessionData.userId);
    saveSession.bind(5, sessionData.email);

    try {
        saveSession.exec();
    } catch (const SQLite::Exception& exception) {
        spdlog::error(
            fmt::format("[SessionRepository] Exception during the execution fo storedata: [{}]", db.getErrorMsg()));
        throw exceptions::StorageException();
    }

    return std::make_shared<session_data>(sessionData.accessToken, sessionData.refreshToken, sessionData.tokenExpiresAt,
        sessionData.userId, sessionData.email);
}

std::shared_ptr<session_data> mdframework::session::infrastructure::SessionRepository::updateCurrent(
    const session_data& sessionData)
{
    const SQLite::Database db(dbPath, SQLite::OPEN_READWRITE);
    SQLite::Statement saveSession(db,
        "UPDATE session SET access_token = ?, refresh_token = ?, token_expires_at = ?, user_id = ?, email = ? "
        "WHERE id = 1");

    saveSession.bind(1, sessionData.accessToken);
    saveSession.bind(2, sessionData.refreshToken);
    saveSession.bind(3, sessionData.tokenExpiresAt);
    saveSession.bind(4, sessionData.userId);
    saveSession.bind(5, sessionData.email);

    try {
        saveSession.exec();
    } catch (const SQLite::Exception& exception) {
        spdlog::error(
            fmt::format("[SessionRepository] Exception during the execution for storedata: [{}]", db.getErrorMsg()));
        throw exceptions::StorageException();
    }

    return std::make_shared<session_data>(sessionData.accessToken, sessionData.refreshToken, sessionData.tokenExpiresAt,
        sessionData.userId, sessionData.email);
}
