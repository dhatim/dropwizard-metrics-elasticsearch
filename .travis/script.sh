#!/usr/bin/env bash

if [ "${TRAVIS_PULL_REQUEST}" == false -a -n "${TRAVIS_TAG}" ]; then
    # not a pr & this is a tag? -> deploy
    set +x
    echo ${GPG_SECRET_KEY} | base64 --decode | gpg --import
    echo ${GPG_OWNERTRUST} | base64 --decode | gpg --import-ownertrust
    set -x
    mvn versions:set -DnewVersion=${TRAVIS_TAG}
    mvn --settings .travis/settings.xml -P release deploy
else
    # install
    mvn install
fi
