# ZooManagementSystem

Полнофункциональная информационно-справочная система управления зоопарком на Java + MySQL.

## Описание
Система автоматизирует:
- Учёт животных (вид, возраст, здоровье, размещение)
- Управление персоналом (роли, авторизация)
- Продажа билетов и лояльность
- Планирование кормлений и осмотров
- Отчёты и аналитика

## Технологии
- **Язык**: Java 17
- **База данных**: MySQL (JDBC)
- **Структура**: MVC (Model-View-Controller), DAO-паттерн
- **Авторизация**: Роли (ADMIN, VET, KEEPER, CASHIER), простой хэш паролей

## Установка
1. Установи MySQL, создай БД `zoo_db` (SQL-скрипт в /sql/schema.sql)
2. Скачай JAR: mysql-connector-j-8.0.33.jar в /lib
3. Запусти: `java -cp "out/production/ZooManagementSystem;lib/*" zoo.Main`
4. Логин: admin / 123456

## Демо
- Админ: Добавь сотрудника (пункт 9) → testvet / 1111 (роль VET)
- Зайди под testvet → увидишь ограниченное меню

## Структура
- `zoo.db/`: DAO для БД (AnimalDAO, StaffDAO)
- `zoo.model/`: Сущности (Animal, Staff)
- `zoo.service/`: Бизнес-логика (AuthService)
- `Main.java`: Консольное меню

## Будущие улучшения
- Графический интерфейс (JavaFX)
- Spring Boot + веб-версия


Автор: [lg71luda](https://github.com/lg71luda)