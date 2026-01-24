############################################
# container
############################################
build:
	docker compose build

up:
	docker compose up -d

down:
	docker compose down -v --remove-orphans

clean:
	docker compose down --rmi all --volumes --remove-orphans

ps:
	docker compose ps -a

logs:
	docker compose logs

logs/%:
	docker compose logs $(@F)

restart: down up

rebuild: clean up
