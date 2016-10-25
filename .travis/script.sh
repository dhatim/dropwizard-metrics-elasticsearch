#!/usr/bin/env bash

if [ "${TRAVIS_EVENT_TYPE}" == push ] &&
       echo "${TRAVIS_TAG}" | egrep '^[0-9]+\.[0-9]+\.[0-9]+$'
then
    # the build is triggered by a tag push, and the tag looks like
    # a version number: proceed with release
    set +x
    echo ${GPG_SECRET_KEY} | base64 --decode | gpg --import
    echo ${GPG_OWNERTRUST} | base64 --decode | gpg --import-ownertrust
    set -x
    mvn versions:set -DnewVersion=${TRAVIS_TAG}
    mvn -s .travis/settings.xml -P release deploy
else
    # this is a regular build
    mvn -o install
fi
