#!/usr/bin/env bash

if [ "${TRAVIS_PULL_REQUEST}" == false -a -n "${TRAVIS_TAG}" ]; then
    # not a pr & this is a tag? -> deploy
    echo ${GPG_SECRET_KEY} | base64 --decode | gpg --import
    echo ${GPG_OWNERTRUST} | base64 --decode | gpg --import-ownertrust
    mvn --settings .travis/settings.xml -P release \
        -DnewVersion=${TRAVIS_TAG} \
        org.codehaus.mojo:versions-maven-plugin:2.2:set \
        deploy
else
    # install
    mvn install
fi
