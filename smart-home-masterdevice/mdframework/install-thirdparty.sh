#!/bin/bash
mkdir /tmp/deps
BASE_DEPS=/tmp/deps

LIBFMT_VERSION=10.2.1
SPDLOG_VERSION=1.13.0
SQLITE_CPP_VERSION=3.3.1
CPP_HTTPLIB=0.15.3
YAML_CPP=0.8.0
NLOHMANN_JSON=3.11.3
SIMPLE_BLE=0.7.3
JWT_CPP=0.7.0

cd ${BASE_DEPS} || exit

# LIBFMT
wget "https://github.com/fmtlib/fmt/archive/refs/tags/${LIBFMT_VERSION}.tar.gz"
tar -xvzf ${LIBFMT_VERSION}.tar.gz
rm ${LIBFMT_VERSION}.tar.gz
echo "Installing lib fmt \n"
cd fmt-${LIBFMT_VERSION} &&
  mkdir build &&
  cd build &&
  cmake -DBUILD_SHARED_LIBS=TRUE .. &&
  cmake --build . -j 8 &&
  cmake --install .
echo "Cleaning up lib fmt"
cd ${BASE_DEPS} && rm -rf fmt-${LIBFMT_VERSION}

# SPDLOG
wget "https://github.com/gabime/spdlog/archive/refs/tags/v${SPDLOG_VERSION}.tar.gz"
tar -xvzf v${SPDLOG_VERSION}.tar.gz
rm v${SPDLOG_VERSION}.tar.gz
echo "Installing spd log to\n"
cd spdlog-${SPDLOG_VERSION} &&
  mkdir build &&
  cd build &&
  cmake -DBUILD_SHARED_LIBS=TRUE .. &&
  cmake --build . -j 8 &&
  cmake --install .
echo "Cleaning up spd log"
cd ${BASE_DEPS} && rm -rf spdlog-${SPDLOG_VERSION}

# SQLiteCpp
wget "https://github.com/SRombauts/SQLiteCpp/archive/refs/tags/${SQLITE_CPP_VERSION}.tar.gz"
tar -xvzf ${SQLITE_CPP_VERSION}.tar.gz
rm ${SQLITE_CPP_VERSION}.tar.gz
echo "Installing SQLITE_CPP_VERSION to\n"
cd SQLiteCpp-${SQLITE_CPP_VERSION} &&
  mkdir build &&
  cd build &&
  cmake -DBUILD_SHARED_LIBS=TRUE .. &&
  cmake --build . -j &&
  cmake --install .
echo "Cleaning up SQLITE_CPP_VERSION"
cd ${BASE_DEPS} && rm -rf SQLiteCpp-${SQLITE_CPP_VERSION}

# CPP_HTTPLIB
wget "https://github.com/yhirose/cpp-httplib/archive/refs/tags/v${CPP_HTTPLIB}.tar.gz"
tar -xvzf v${CPP_HTTPLIB}.tar.gz
rm v${CPP_HTTPLIB}.tar.gz
echo "Installing spd log\n"
cd cpp-httplib-${CPP_HTTPLIB} &&
  mkdir build &&
  cd build &&
  cmake -DBUILD_SHARED_LIBS=TRUE .. &&
  cmake --build . -j &&
  cmake --install .
echo "Cleaning up CPP_HTTPLIB"
cd ${BASE_DEPS} && rm -rf cpp-httplib-${CPP_HTTPLIB}

# YAML_CPP
wget "https://github.com/jbeder/yaml-cpp/archive/refs/tags/${YAML_CPP}.tar.gz"
tar -xvzf ${YAML_CPP}.tar.gz
rm ${YAML_CPP}.tar.gz
echo "Installing spd log \n"
cd yaml-cpp-${YAML_CPP} &&
  mkdir build &&
  cd build &&
  cmake -DBUILD_SHARED_LIBS=TRUE .. &&
  cmake --build . -j &&
  cmake --install .
echo "Cleaning up YAML_CPP"
cd ${BASE_DEPS} && rm -rf yaml-cpp-${YAML_CPP}

# NLOHMANN_JSON
wget "https://github.com/nlohmann/json/archive/refs/tags/v${NLOHMANN_JSON}.tar.gz"
tar -xvzf v${NLOHMANN_JSON}.tar.gz
rm v${NLOHMANN_JSON}.tar.gz
echo "Installing NLOHMANN_JSON \n"
cd json-${NLOHMANN_JSON} &&
  mkdir build &&
  cd build &&
  cmake -DBUILD_SHARED_LIBS=TRUE .. &&
  cmake --build . -j &&
  cmake --install .
echo "Cleaning up NLOHMANN_JSON"
cd ${BASE_DEPS} && rm -rf json-${NLOHMANN_JSON}

# SIMPLE_BLE
wget "https://github.com/OpenBluetoothToolbox/SimpleBLE/archive/refs/tags/v${SIMPLE_BLE}.tar.gz"
tar -xvzf v${SIMPLE_BLE}.tar.gz
rm v${SIMPLE_BLE}.tar.gz
echo "Installing SimpleBLE \n"
cd SimpleBLE-${SIMPLE_BLE}/simpleble &&
  mkdir build &&
  cd build &&
  cmake -DBUILD_SHARED_LIBS=TRUE .. &&
  cmake --build . -j &&
  cmake --install .
echo "Cleaning up SIMPLE_BLE"
cd ${BASE_DEPS} && rm -rf SimpleBLE-${SIMPLE_BLE}

# JWT-CPP
wget "https://github.com/Thalhammer/jwt-cpp/archive/refs/tags/v${JWT_CPP}.tar.gz"
tar -xvzf v${JWT_CPP}.tar.gz
rm v${JWT_CPP}.tar.gz
echo "Installing JWT-CPP \n"
cd jwt-cpp-${JWT_CPP} &&
  mkdir build &&
  cd build &&
  cmake -DBUILD_SHARED_LIBS=TRUE .. &&
  cmake --build . -j &&
  cmake --install .
echo "Cleaning up JWT-CPP "
cd ${BASE_DEPS} && rm -rf jwt-cpp-${JWT-CPP}

#rm -rf ${BASE_DEPS}
