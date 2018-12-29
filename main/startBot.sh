#!/bin/bash
echo "starting script!" && cd .. && mvn clean install -DskipTests && cd main && mvn exec:java && echo "script done!"
