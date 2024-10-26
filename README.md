## 🎓 University CRUD REST
University CRUD REST — простое приложение с реализацией CRUD (создание, чтение, обновление, удаление) для управления данными студентов и курсов с использованием JDBC и Servlets без использования Spring или Hibernate.

## 🚀 Возможности
**CRUD-операции:** Поддержка базовых операций с объектами, представляющими университетские данные.
**Серверная часть:** Реализация на сервлетах с использованием чистого JDBC.
**Контейнеризация:** Поддержка Docker для удобного развёртывания.
## 🛠 Технологический стек
**Java (Servlet API, JDBC)**
**PostgreSQL**
**Docker**
## 📂 Структура проекта
src/ — исходный код Java.
docker-compose.yml — конфигурация Docker для быстрого развёртывания PostgreSQL.
## 🔧 Установка и настройка
1.  **Предварительные требования**
Docker и Docker Compose
Java JDK 11+

2. **Установка**
Клонируйте репозиторий:
- git clone https://github.com/luckyhunterzzz/university_crud_rest.git
- cd university_crud_rest

3. **Запустите базу данных PostgreSQL:**
docker-compose up -d

4. **Соберите и запустите проект:**
mvn clean package
java -jar target/university_crud_rest.jar

## 📬 API Эндпоинты
Поддерживаемые эндпоинты для работы с данными:

- GET /students — Получить список всех студентов.
- POST /students — Создать нового студента.
- PUT /students/{id} — Обновить данные студента.
- DELETE /students/{id} — Удалить студента.
