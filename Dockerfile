FROM debian:stretch-slim

RUN apt-get update
RUN apt-get install -y software-properties-common gnupg2 apt-transport-https apt-utils
    
RUN echo "deb https://dl.bintray.com/sbt/debian /" | tee -a /etc/apt/sources.list.d/sbt.list
RUN apt-key adv --keyserver hkp://keyserver.ubuntu.com:80 --recv 2EE0EA64E40A89B84B2DF73499E82A75642AC823
RUN echo oracle-java8-installer shared/accepted-oracle-license-v1-1 select true | debconf-set-selections
RUN add-apt-repository -y ppa:webupd8team/java

RUN apt-get update
RUN mkdir -p /usr/share/man/man1
RUN apt-get install -y --allow-unauthenticated oracle-java8-installer
RUN apt-get install oracle-java8-set-default
RUN apt-get install -y scala sbt

EXPOSE 9000

RUN mkdir /home/shared
VOLUME ["/home/shared/"]

WORKDIR /home/
RUN git clone https://github.com/playframework/play-scala-slick-example
WORKDIR /home/play-scala-slick-example/
CMD sbt run
