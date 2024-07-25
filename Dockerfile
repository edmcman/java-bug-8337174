FROM ubuntu:latest

RUN apt-get update
RUN apt-get -y install \
	notion wget xserver-xephyr
RUN apt-get clean

RUN wget 'https://github.com/adoptium/temurin21-binaries/releases/download/jdk-21.0.4%2B7/OpenJDK21U-jdk_x64_linux_hotspot_21.0.4_7.tar.gz' -O - | tar -xvzf - -C /opt

ENV JAVA_HOME=/opt/jdk-21.0.4+7
ENV PATH="$PATH:$JAVA_HOME/bin"

RUN java -version

ADD notion.bash .
ADD DragAndDropExample.java .

CMD bash notion.bash
