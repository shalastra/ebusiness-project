FROM openjdk:8u151

ENV SCALA_VERSION 2.12.4
ENV SBT_VERSION 0.13.11
ENV SCALA_HOME /usr/local/share/scala

ENV SCALA_URL http://downloads.lightbend.com/scala
ENV SCALA_FILE scala-$SCALA_VERSION
ENV SCALA_TGZ $SCALA_FILE.tgz

ENV SBT_URL https://dl.bintray.com/sbt/native-packages/sbt
ENV SBT_FILE sbt-$SBT_VERSION
ENV SBT_TGZ $SBT_FILE.tgz

RUN wget $SCALA_URL/$SCALA_VERSION/$SCALA_TGZ && \
	tar xvzf $SCALA_TGZ && \
	mv $SCALA_FILE /usr/local/share/scala

RUN wget $SBT_URL/$SBT_VERSION/$SBT_TGZ && \
	tar xvzf $SBT_TGZ && \
	mv sbt $SCALA_HOME/sbt && \
	$SCALA_HOME/sbt/bin/sbt

ENV PATH $PATH:$SCALA_HOME/bin:$SCALA_HOME/sbt/bin

RUN mdkir ebusiness_project && cd ebusiness_project && \
  echo "libraryDependencies += \"com.typesafe.play\" %% \"play\" % \"2.6.12\"" >> build.sbt && \
  echo "libraryDependencies += \"com.typesafe.slick\" %% \"slick\" % \"3.2.1\"" >> build.sbt && \
  sbt update