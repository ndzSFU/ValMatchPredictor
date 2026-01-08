FROM ubuntu:latest
LABEL authors="dukeo"

ENTRYPOINT ["top", "-b"]