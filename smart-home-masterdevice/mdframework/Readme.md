## Build custom path

rm -rf build && mkdir build && cd build && cmake -G Ninja -DCMAKE_FIND_ROOT_PATH=/home/andrei/thirdparty
-DCMAKE_INSTALL_PREFIX:PATH=/home/andrei/thirdparty -DCMAKE_INSTALL_LIBDIR=lib -DCMAKE_INSTALL_INCLUDEDIR=include .. &&
cd ..

