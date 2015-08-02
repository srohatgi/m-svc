# m-svc #

login web-application and a micro-service api running on top of mongodb

## dev environment setup - mac ##

1. Install [virtual box](https://www.virtualbox.org/)
2. Install [vagrant](https://www.vagrantup.com/)
3. Install [jdk 8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
4. Install [gradle](https://gradle.org/gradle-download/)

## running on mac laptop ##

In your terminal (preferably using [iTerm 2](https://www.iterm2.com/))

    $ git clone https://github.com/srohatgi/m-svc; cd m-svc
    $ cd env; vagrant up; cd -
    $ gradle bRun
    
Navigate to [http://localhost:8080/api/people](http://localhost:8080/api/people) in your browser
