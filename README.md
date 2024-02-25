## Задание
Реализовать приложение, которое выполняет CRUD операции над Объектом.

## О проекте
Был реализован онлайн магазин мобильных телефонов. Реализована проверка данных на содержание пустых полей у brand и model, проверка на положительное число у полей quantity и price. Сделана настройка подключения к базе дынных PostgreSQL, а также написаны скрипты на Flyway для создания таблицы. Создана документация REST API с использованием Springdoc. Проект покрыт тестами при помощи библиотек JUnit и Mockito. Настроен docker-compose.yml файл.
- id - идентификатор
- brand - бренд телефона
- model - модель телефона
- quantity - количество определённого товара
- price - цена телефона
- createdDate - дата создания товара
- updatedDate - дата обновления товара

## Stack
- Spring Boot
- Spring Data JPA
- PostgreSQL
- FlyWay
- Swagger
- Junit + Mockito
- Docker

## Включает 6 каталогов Java
- controller – контроллер модели Device;
- dto – объект для передачи данных;
- model - модель объекта Device;
- repository - репозиторий модели;
- service - бизнес-логика;
- util — классы для обработки исключений;
- test - модульное и интеграционное тестирование.

## Запуск приложения
Выполните эту команду из вашей IDE.
```bash
  docker-compose up
```
База данных автоматически заполнится 5 товарами.

### Получение информации товара по id.
Выполните GET запрос в Postman
```
http://localhost:8080/devices/1
```
Вернётся результат
```
{
    "brand": "iPhone",
    "model": "12",
    "quantity": 100,
    "price": 45000
}
```

### Получение информации всех товаров.
Выполните GET запрос в Postman
```
http://localhost:8080/devices
```
Вернётся результат
```
[
    {
        "brand": "iPhone",
        "model": "12",
        "quantity": 100,
        "price": 45000
    },
    {
        "brand": "Samsung",
        "model": "A43",
        "quantity": 340,
        "price": 27000
    },
    {
        "brand": "Xiaomi",
        "model": "Redmi 12",
        "quantity": 510,
        "price": 19000
    },
    {
        "brand": "iPhone",
        "model": "15 pro",
        "quantity": 50,
        "price": 90000
    },
    {
        "brand": "iPhone",
        "model": "15",
        "quantity": 150,
        "price": 60000
    }
]
```

### Редактирование товара по id.
Выполните PUT запрос в Postman
```
http://localhost:8080/devices/1
```
```
{
    "brand": "iPhone",
    "model": "12",
    "quantity": 600,
    "price": 40000
}
```

Вернётся результат
```
{
    "brand": "iPhone",
    "model": "12",
    "quantity": 600,
    "price": 40000
}
```

### Создание товара.
Выполните POST запрос в Postman
```
http://localhost:8080/devices
```
Введите данные товара
```
{
    "brand": "iPhone",
    "model": "13",
    "quantity": 30,
    "price": 39000
}
```

Вернётся результат
```
{
    "brand": "iPhone",
    "model": "13",
    "quantity": 30,
    "price": 39000
}
```

### Удаление товара по id.
Выполните DELETE запрос в Postman
```
http://localhost:8080/devices/1
```
Вернётся результат
```
Device with id:1 is delete
```

### Получение несуществующего товара
Выполните GET запрос в Postman
```
localhost:8080/devices/100
```
Вернётся результат 
```
{
    "details": "Entity not found",
    "error": "EntityNotFoundException",
    "message": "Device with id:100 was not found",
    "path": "/devices/100",
    "status": "NOT_FOUND"
}
```

### Валидацию данных
Выполните POST запрос в Postman
```
{
        "brand": "",
        "model": "",
        "quantity": -10,
        "price": -10
}
```
Вернётся результат
```
{
    "details": "Change the data in the request body",
    "error": "IllegalArgumentException",
    "message": "price - Quantity of an device must not be negative!; brand - Brand of an device must not be empty!; model - Model of an device must not be empty!; quantity - Quantity of an device must not be negative!; ",
    "path": "/devices",
    "status": "BAD_REQUEST"
}
```


### Тестирование приложения
Запустить тесты можно с помощью Maven:
```
./mvnw clean test
```

### Swagger API docs
Запустите сервер и перейдите по ссылке: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
