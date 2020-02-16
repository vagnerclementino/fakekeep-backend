include MakefileDocumentation

run: ##@application Starts API Locally
	./mvnw clean package -DskipTests && docker-compose up --build

stop: ##@application Stop all containers.
	docker-compose down

build: ##@application Creates docker containers.
	docker-compose build

container: ##@helpers Do a docker exec bash inside container.
	docker exec -it viridis_api /bin/sh

test: ##@tests Run all tests
	./mvnw clean test -Dspring.profiles.active=test

psql: ##@helpers Start a postgres client
	docker exec -it viridis_db /bin/bash -c 'psql exercise viridis'

pmd: ##@helpers Run a code analysis
	./mvnw pmd:pmd pmd:cpd pmd:check pmd:cpd-check
