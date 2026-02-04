############################################
# container
############################################
ifeq ($(OS),Windows_NT)
    DETECTED_OS := Windows
    SET_BUILDKIT := set "DOCKER_BUILDKIT=1" &
    RM := rmdir /s /q
    MKDIR := mkdir
    MOVE := move
    CACHE_BASE := C:\tmp
else
    DETECTED_OS := $(shell uname -s)
    # Linux/macOS用の環境変数設定
    SET_BUILDKIT := DOCKER_BUILDKIT=1
    RM := rm -rf
    MKDIR := mkdir -p
    MOVE := mv
    CACHE_BASE := /tmp
endif

# キャッシュディレクトリのリスト
CACHE_DIRS := user order product caller

build:
	$(SET_BUILDKIT) docker compose build --parallel

build-no-cache:
	$(SET_BUILDKIT) docker compose build --no-cache --parallel

# ビルド後に自動でキャッシュローテーション
build-with-cache-rotate:
	@echo "Building with auto cache rotation..."
	@$(MAKE) build
	@$(MAKE) cache-cleanup
	@echo "Build and cache rotation completed!"

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

rebuild: clean build-with-cache-rotate up

# よく使うフロー（ビルド→ローテーション→起動）
deploy: build-with-cache-rotate up
	@echo "Services deployed successfully!"

# 個別キャッシュのクリーンアップ
cache-cleanup:
ifeq ($(OS),Windows_NT)
	@echo "Cleaning up build cache on Windows..."
	@for %%s in ($(CACHE_DIRS)) do ( \
		if exist "$(CACHE_BASE)\.buildx-cache-%%s" $(RM) "$(CACHE_BASE)\.buildx-cache-%%s" 2>nul & \
		if exist "$(CACHE_BASE)\.buildx-cache-%%s-new" $(MOVE) "$(CACHE_BASE)\.buildx-cache-%%s-new" "$(CACHE_BASE)\.buildx-cache-%%s" >nul 2>&1 \
	)
	@echo "Cache cleanup completed."
else
	@echo "Cleaning up build cache on $(DETECTED_OS)..."
	@for service in $(CACHE_DIRS); do \
		$(RM) $(CACHE_BASE)/.buildx-cache-$$service 2>/dev/null || true; \
		if [ -d $(CACHE_BASE)/.buildx-cache-$$service-new ]; then \
			$(MOVE) $(CACHE_BASE)/.buildx-cache-$$service-new $(CACHE_BASE)/.buildx-cache-$$service; \
		fi; \
	done
	@echo "Cache cleanup completed."
endif

# すべてのキャッシュを完全削除
cache-purge:
ifeq ($(OS),Windows_NT)
	@echo "Purging all build cache on Windows..."
	@for %%s in ($(CACHE_DIRS)) do ( \
		if exist "$(CACHE_BASE)\.buildx-cache-%%s" $(RM) "$(CACHE_BASE)\.buildx-cache-%%s" 2>nul & \
		if exist "$(CACHE_BASE)\.buildx-cache-%%s-new" $(RM) "$(CACHE_BASE)\.buildx-cache-%%s-new" 2>nul \
	)
	@echo "All cache purged."
else
	@echo "Purging all build cache on $(DETECTED_OS)..."
	@for service in $(CACHE_DIRS); do \
		$(RM) $(CACHE_BASE)/.buildx-cache-$$service 2>/dev/null || true; \
		$(RM) $(CACHE_BASE)/.buildx-cache-$$service-new 2>/dev/null || true; \
	done
	@echo "All cache purged."
endif

# 特定のサービスのキャッシュをクリーンアップ
cache-cleanup/%:
ifeq ($(OS),Windows_NT)
	@echo "Cleaning up cache for $(@F)..."
	@if exist "$(CACHE_BASE)\.buildx-cache-$(@F)" $(RM) "$(CACHE_BASE)\.buildx-cache-$(@F)" 2>nul
	@if exist "$(CACHE_BASE)\.buildx-cache-$(@F)-new" $(MOVE) "$(CACHE_BASE)\.buildx-cache-$(@F)-new" "$(CACHE_BASE)\.buildx-cache-$(@F)" >nul 2>&1
	@echo "Cache cleanup for $(@F) completed."
else
	@echo "Cleaning up cache for $(@F)..."
	@$(RM) $(CACHE_BASE)/.buildx-cache-$(@F) 2>/dev/null || true
	@if [ -d $(CACHE_BASE)/.buildx-cache-$(@F)-new ]; then \
		$(MOVE) $(CACHE_BASE)/.buildx-cache-$(@F)-new $(CACHE_BASE)/.buildx-cache-$(@F); \
	fi
	@echo "Cache cleanup for $(@F) completed."
endif

# 特定のサービスのみビルド
build/%:
	$(SET_BUILDKIT) docker compose build $(@F)

# ヘルプメッセージ
help:
	@echo "Available targets (OS: $(DETECTED_OS)):"
	@echo "  build                      - Build all services with parallel mode"
	@echo "  build-no-cache             - Build without cache"
	@echo "  build-with-cache-rotate    - Build and auto-rotate cache"
	@echo "  build/<service>            - Build specific service (e.g., make build/user-api)"
	@echo "  deploy                     - Build, rotate cache, and start services"
	@echo "  up                         - Start services"
	@echo "  down                       - Stop services"
	@echo "  clean                      - Stop and remove all containers, images, and volumes"
	@echo "  restart                    - Restart services"
	@echo "  rebuild                    - Clean, rebuild with cache rotation, and start"
	@echo "  ps                         - Show container status"
	@echo "  logs                       - Show all logs"
	@echo "  logs/<service>             - Show logs for specific service (e.g., make logs/user-api)"
	@echo "  cache-cleanup              - Rotate build cache (move -new to active)"
	@echo "  cache-cleanup/<s>          - Cleanup cache for specific service (e.g., make cache-cleanup/user)"
	@echo "  cache-purge                - Delete all build cache"
	@echo "  help                       - Show this help message"

.PHONY: build build-no-cache build-with-cache-rotate up down clean ps logs restart rebuild deploy cache-cleanup cache-purge help
