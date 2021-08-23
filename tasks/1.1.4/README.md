## Попытка 2
* Обновил Hibernate до 5.5.6 Final
* Теперь session работают в try с ресурсами
* Добавил транзакции
* Убрал аннотации @Column из модели юзера
## Попытка 1
* HibernateSessionFactoryUtil
  * Нагуглил, что в проекте создается один объект **SessionFactory**, который раздает сессии. Реализовал его в Util.
* User model
    1. Обозначил класс аннотацией @Entity
    2. Дописал название таблицы к аннотации @Table
    3. Допилил @GeneratedValue(strategy = GenerationType.IDENTITY) к ID для автоинкремента.
* UserDaoHibernateImpl
    1. **createUsersTable dropUsersTable** реализовал с помощью обычного SQL запроса
    2. **removeUserById getAllUsers** с помощью HQL
    3. **saveUser** c помощью API хибернейта