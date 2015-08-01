# m-svc #

microservice with mongodb in the backend

## dev environment setup - mac ##

1. Install [VirtualBox](https://www.virtualbox.org/)
2. Install [Vagrant](https://www.vagrantup.com/)
3. Install [JDK 8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
4. Install [gradle](https://gradle.org/gradle-download/)


Run following commands on your terminal

    $ git clone https://github.com/srohatgi/m-svc
    $ cd m-svc/env; vagrant up; cd -
    $ cd m-svc; gradle run
    
Navigate to `http://localhost:8080/api/people` in your browser
