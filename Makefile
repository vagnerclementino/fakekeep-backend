include MakefileDocumentation
SERVICE_NAME = api-fakekeep
IMAGE_TAG ?= $(shell git rev-parse --short HEAD)
IMAGE_ID = $(SERVICE_NAME):$(IMAGE_TAG)

build-image:
	docker build -t $(IMAGE_ID) -f Dockerfile .

build-artifact:
	./gradlew bootJar

run-only:
	docker-compose up

run: build-image build-artifact run-only

stop: ##@application Stop all containers.
	docker-compose down

container: ##@helpers Do a docker exec bash inside container.
	docker exec -it fakekeep_api /bin/sh

test: ##@tests Run all tests
	./gradlew test -Dspring.profiles.active=test

psql: ##@helpers Start a postgres client
	docker exec -it fakekeep_db /bin/bash -c 'psql fakekeep fakekeep'

lint: ##@helpers Run a code analysis
	./gradlew lint