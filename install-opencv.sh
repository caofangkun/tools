#!/bin/bash

function platform {
  PLATFORM=darwin
  case "$OSTYPE" in
    darwin*)
      PLATFORM=darwin
      ;;
    linux*)
      PLATFORM=ubuntu
      if [ -f /etc/redhat-release ] ; then
        PLATFORM=centos
      fi
      ;;
    *)
      echo "WARNING: Your platform is not currently supported!" >&2
      echo "Currently supported platforms are:" >&2
      echo "  darwin, ubuntu, and centos"
      exit 1
      ;;
  esac
  echo ${PLATFORM}
}

CURRENT_PLATFORM=`platform`

echo "Installing Required Packages on " ${CURRENT_PLATFORM}
if [ ${CURRENT_PLATFORM} == "ubuntu" ] ; then 
  echo ""
  sudo apt-get install -y build-essential
  sudo apt-get install -y cmake git libgtk2.0-dev pkg-config libavcodec-dev libavformat-dev libswscale-dev \
        python-dev python-numpy libtbb2 libtbb-dev libjpeg-dev libpng-dev libtiff-dev libjasper-dev libdc1394-22-dev

elif [ ${CURRENT_PLATFORM} == "centos" ] ; then 
  sudo yum install -y cmake gcc gcc-c++ gtk+-devel gimp-devel gimp-devel-tools gimp-help-browser \ 
             zlib-devel libtiff-devel libjpeg-devel libpng-devel gstreamer-devel libavc1394-devel \
             libraw1394-devel libdc1394-devel jasper-devel jasper-utils swig python libtool nasm  \
             libjpeg-turbo-devel  openexr-devel libwebp-devel gtk2-devel libv4l-devel ffmpeg-devel \  
             gstreamer-plugins-base-devel python-devel numpy
else 
  echo "Do not support " ${CURRENT_PLATFORM}
  exit 1
fi


tar -zxvf opencv-3.1.0.tar.gz
cd opencv-3.1.0
mkdir build && cd build
cmake -D CMAKE_BUILD_TYPE=RELEASE -D CMAKE_INSTALL_PREFIX=/usr/local ..
make -j 16
sudo make install
