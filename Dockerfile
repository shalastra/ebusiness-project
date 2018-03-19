FROM debian:stretch-slim

ENV SCALA_VERSION 2.12.4
ENV SBT_VERSION 1.1.1

RUN apt-get update

RUN mkdir /usr/share/man/man1
RUN apt-get install -y openjdk-8-jdk 
RUN apt-get install -y curl

RUN \
  curl -fsL https://downloads.typesafe.com/scala/$SCALA_VERSION/scala-$SCALA_VERSION.tgz | tar xfz - -C /root/ && \
  echo >> /root/.bashrc && \
  echo "export PATH=~/scala-$SCALA_VERSION/bin:$PATH" >> /root/.bashrc

RUN \
  curl -L -o sbt-$SBT_VERSION.deb https://dl.bintray.com/sbt/debian/sbt-$SBT_VERSION.deb && \
  dpkg -i sbt-$SBT_VERSION.deb && \
  rm sbt-$SBT_VERSION.deb && \
  apt-get update && \
  apt-get install sbt && \
  sbt sbtVersion

RUN DEBIAN_FRONTEND=noninteractive apt-get install -y mysql-server mysql-client

WORKDIR /home
RUN mkdir ebusiness && \
    cd ebusiness && \ 
    echo "libraryDependencies += \"com.typesafe.slick\" %% \"slick\" % \"3.2.1\"" >> build.sbt && \
    echo "libraryDependencies += \"com.typesafe.play\" %% \"play\" % \"2.6.12\"" >> build.sbt && \
    echo "libraryDependencies += \"mysql\" % \"mysql-connector-java\" % \"6.0.6\"" >> build.sbt && \
    sbt update

CMD sbt console