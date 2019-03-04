FROM ubuntu:16.04

# Install all the updates for UBUNTU
RUN apt-get update && apt-get install -y python-software-properties software-properties-common
RUN apt-get install -y iputils-ping

# Adds the repository where JDK 8 can be obtained for UBUNTU
RUN add-apt-repository ppa:webupd8team/java

#INSTALL ORACLE JDK 8
RUN echo "oracle-java8-installer shared/accepted-oracle-license-v1-1 boolean true" | debconf-set-selections

#updates MAVEN JDK 8
RUN apt-get update && apt-get install -y oracle-java8-installer maven

# ADD a directory called docker-git-hello-world inside the UBUNTU IMAGE where you will be moving all of these files under this
# DIRECTORY to
ADD . /mnt/route-puzzle-game

# AFTER YOU HAVE MOVED ALL THE FILES GO AHEAD CD into the directory and run mvn assembly.
# Maven assembly will package the project into a JAR FILE which can be executed
RUN cd /mnt/route-puzzle-game && mvn clean compile assembly:single

CMD ["java", "-cp", "/mnt/route-puzzle-game/target/route-puzzle-game-0.0.1-SNAPSHOT-jar-with-dependencies.jar", "it.michelepierri.rpg.launcher.StartGame"]