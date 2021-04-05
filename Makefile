
build_image:
	docker build . --no-cache -t encoded-data-differ:v1

run: build_image
	docker run -p 8080:8080 encoded-data-differ:v1