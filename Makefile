
build_image:
	docker build . --no-cache -t encoded-data-differ:v1

run_docker: build_image
	docker run -p 8080:8080 encoded-data-differ:v1

run_int_tests:
	./gradlew integrationTest

run_unit_tests:
	./gradlew unitTest
