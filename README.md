# Jd-02_Biazverkhi
 # Курсовой проект
## Тема: "Прокат автомобилей". 
### Исполнитель: Безверхий Алексей.
#### Условия:
- Клиент выбирает Автомобиль из списка доступных.
- Заполняет форму Заказа, указывая паспортные данные, срок аренды. 
- Администратор может отклонить Заявку, указав причины отказа. 
- При подтверждении Заявки, Клиент оплачивает Заказ. 
- Система выписывает сумму. 
- В случае повреждения Автомобиля Клиентом, Администратор вносит соответствующие пометки.
### Требования 1 этапа разработки с применением JDBC:
1. Должен быть многомодульный проект, логика базы данных должна быть в dao модуле, логика работы с сетью в модуле веб
2. Данные должны храниться в реляционной базе данных MySQL
3. Вся логика дао и сервис модуля должна быть доступна через интерфейсы
4. 30% кода дао и сервисов должны быть покрыто тестами
5. Модуль сервисов должен быть протестирован с помощью моков
6. Для авторизации и аутентификации использовать Filter.
7. Классы и методы должны иметь отражающую их функциональность названия и должны быть грамотно структурированы по пакетам.
8. Приложение должно соответствовать шаблону Model-View-Controller или dto jsp servlet/service
9. Для получения объектов dao и service использовать шаблон Singletone.
10. Конфигурационную информацию хранить в properties-файле, например, такую как: параметры соединения с БД, граничные значения предметной области, локализацию
11. Приложение должно запускаться на сервере приложений томкат
12.	При разработке использовать журналирование событий (slf4j).
13.	Используя сервлеты и JSP, реализовать функциональности, предложенные в постановке конкретной задачи.
14.	В JSP-страницах использовать возможности библиотеки JSTL.
15. Приложение должно поддерживать работу с кириллицей, в том числе и при хранении информации в БД.
16. Проект должен иметь функциональнось логина пользователя, добавления сущностей удаления и обновления и просмотр
### Требования 2 этапа разработки с применением Hibernate:
1. Использовать контрольную работу №1 изменить модуль dao. Переписать модуль dao с использованием фреймворка Hibernate.
2. Использовать отношения между сущностями 1-1, 1-n, n-n.
3. Ограничить количество элементов выборки с помощью педжинации.
4. Для запросов использовать Hibernate Query и Criteria.
5. Использовать механизм пользовательских транзакций.
6. Подключить кэш второго уровня Hibernate.
7. 60% кода модулей dao и services покрыть unit-тестами.
8. должно быть минимум 5 таблиц и 5 сущности и 5 Dao и 5 сервисов для работы с этими сущностями и связывающая их функциональность
9. должен соблюдаться https://www.oracle.com/technetwork/java/codeconventions-150003.pdf
### Требования 3 этапа разработки с применением Spring:
1. Использовать spring mvc для web, сервлетов не должно быть
2. В качестве представления данных использовать шаблоны фрейморка Tiles или аналог
3. Использовать модуль безопасности Spring для ограничения доступа
4. Реализовать интернационализацию сообщений через Spring.
5. Для проекта произвести интеграцию Hibernate и Spring.
6. Проект должен иметь законченную функциональность (все должно работать)
7. Должна быть полная функциональность (не просто 2 Dao и 2 service)
8. Выполнение всех пунктов не гарантирует получение высшего балла
9. Будет оцениваться: чистота кода, размеры классов и методов, имена переменных и соблюдение общих стандартов написание кода
10. Будут задаваться теоретические вопросы при сдаче

## Как подключиться:
http://localhost:8080/index

Возможности:
- реализованы все требования преподавателя;
- просмотр, создание, изменение, удаление всех сущностей;
- оформление заявки на аренду авто; 
- подтверждение заявки;
- сортировка и пагинация;
- фильтрация элементов отображения;
- регистрация пользователя;

