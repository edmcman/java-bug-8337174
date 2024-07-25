FROM ubuntu:latest

RUN apt-get update
RUN apt-get -y install \
	wget xserver-xephyr git build-essential lua5.4 liblua5.4-dev \
        libx11-dev libxext-dev libsm-dev gettext libxinerama-dev     \
        libxrandr-dev libxft-dev libxtst6 xterm libxi6 x11-utils x11-apps
RUN apt-get clean

RUN git clone https://github.com/raboof/notion
RUN cd notion && make all install

RUN wget 'https://github.com/adoptium/temurin21-binaries/releases/download/jdk-21.0.4%2B7/OpenJDK21U-jdk_x64_linux_hotspot_21.0.4_7.tar.gz' -O - | tar -xvzf - -C /opt

ENV JAVA_HOME=/opt/jdk-21.0.4+7
ENV PATH="$PATH:$JAVA_HOME/bin"

RUN java -version

ADD notion.bash .
ADD DragAndDropExample.java .

CMD bash notion.bash
