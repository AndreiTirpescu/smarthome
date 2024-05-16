#ifndef MDFRAMEWORKWITHTEST_CONFIG_H
#define MDFRAMEWORKWITHTEST_CONFIG_H

#include <string>

namespace mdframework::config {
void initialize_library(const std::string& configPath);
void clear_library();
}
#endif // MDFRAMEWORKWITHTEST_CONFIG_H
