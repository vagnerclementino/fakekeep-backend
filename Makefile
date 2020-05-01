include MakefileDocumentation
SERVICE_NAME = api-fakekeep
IMAGE_TAG ?= $(shell git rev-parse --short HEAD)
IMAGE_ID = vagnerclementino/$(SERVICE_NAME):$(IMAGE_TAG)
DOCKER_IMAGE_ID = vagnerclementino/dev:api-fakekeep-lastest

build-image: build-artifact
	docker build -t $(IMAGE_ID) -f Dockerfile .

push-image: build-image
	docker tag $(IMAGE_ID) $(DOCKER_IMAGE_ID)
	docker push $(DOCKER_IMAGE_ID)

build-artifact:
	./gradlew clean bootJar

run-only:
	docker-compose up

run: build-image run-only

stop: ##@application Stop all containers.
	docker-compose down

container: ##@helpers Do a docker exec bash inside container.
	docker exec -it fakekeep_api /bin/sh

test: ##@tests Run all tests
	./gradlew cleanTest test
psql: ##@helpers Start a postgres client
	docker exec -it fakekeep_db /bin/bash -c 'psql fakekeep fakekeep'

lint: ##@helpers Run a code analysis
	./gradlew lint
