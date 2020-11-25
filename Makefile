default: help

install: ## Install dependencies
	./mvnw install

build: ## Packages the app
	./mvnw clean package

run: ## Runs the Spring Boot app in dev mode
	./mvnw spring-boot:run

clean: ## Cleans out all targets
	./mvnw clean

test: clean ## Runs test
	./mvnw test

help:
	@grep -E '^[a-zA-Z_-]+:.*?## .*$$' $(MAKEFILE_LIST) | sort | awk 'BEGIN {FS = ":.*?## "}; {printf "\033[36m%-30s\033[0m %s\n", $$1, $$2}'

.PHONY: install build run clean test help
