<h3>Попытка 2</h3>
<ol>
<li>
Поменял Statement на PreparedStatement, теперь в UserDaoJDBCImpl приходится напрямую работать с объектом состояния,
чтобы загружать туда параметры.
</li>
<li>
Query вынес в отдельный .java файл, видимость на уровне пакета
</li>
<li>
Поменял в Util константы: добавил static, разделил слова нижним подчеркиванием, ориентировался на <a href="https://google.github.io/styleguide/javaguide.html">Google Java Style Guide</a>
</li>
</ol>
<h3>Попытка 1</h3>
Не совсем понял, зачем нужен Util, реализовал в нем соединение с БД<br>
Поменял версию mysql-connector-java в pom.xml<br> 
