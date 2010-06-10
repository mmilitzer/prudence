#!/bin/bash

here=$(readlink -f "$(dirname "$0")")
cd $here/..

java=/usr/bin/java
#java=/usr/lib/jvm/java-1.5.0-sun/bin/java

main=com.threecrickets.scripturian.Scripturian

jars=\
libraries/com.hazelcast.jar:\
libraries/com.mysql.jdbc.jar:\
libraries/com.sun.grizzly.jar:\
libraries/com.threecrickets.jygments.jar:\
libraries/com.threecrickets.prudence.jar:\
libraries/com.threecrickets.scripturian.jar:\
libraries/com.threecrickets.succinct.jar:\
libraries/javax.script.jar:\
libraries/javax.servlet.jar:\
libraries/jep.jar:\
libraries/org.apache.commons.fileupload.jar:\
libraries/org.apache.commons.io.jar:\
libraries/org.apache.log4j.jar:\
libraries/org.apache.velocity.jar:\
libraries/org.codehaus.jackson.jar:\
libraries/org.codehaus.jackson.mapper.jar:\
libraries/org.h2.jar:\
libraries/org.json.jar:\
libraries/org.python.jar:\
libraries/org.restlet.ext.fileupload.jar:\
libraries/org.restlet.ext.grizzly.jar:\
libraries/org.restlet.ext.jackson.jar:\
libraries/org.restlet.ext.json.jar:\
libraries/org.restlet.ext.slf4j.jar:\
libraries/org.restlet.jar:\
libraries/org.slf4j.bridge.jar:\
libraries/org.slf4j.impl.jar:\
libraries/org.slf4j.jar

# We are setting java.library.path for Jepp

"$java" \
-cp "$jars" \
-Dscripturian.cache=cache \
-Dpython.home=libraries/python \
-Dpython.verbose=warning \
-Djava.library.path=/usr/local/lib ^
$main instance
