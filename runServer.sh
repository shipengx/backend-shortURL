#!/bin/bash
#
#

mvn clean install

java -cp ./target/lib/*:./target/backend-shortURL-0.0.1-SNAPSHOT.jar org.shipeng.backend_shortURL.server.start
