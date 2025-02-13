# В проекте используется:

Java 21
Система сборки Maven
Библиотеки:
Lombok 1.18.36
https://mvnrepository.com/artifact/org.projectlombok/lombok/1.18.36
Maven jar plugin 3.4.2
https://mvnrepository.com/artifact/org.apache.maven.plugins/maven-jar-plugin/3.4.2

# Инструкция по запуску проекта

Для запуска из директории проекта выполните команду mvn package

Затем выполните java -jar target\shift-test-task-1.0.jar -a -f -o C:\...\some\path -p sample- 1.txt 2.txt
Где аргументами для запуска являются следующие параметры перечисленные через пробел:
-a добавления в существующие файлы
-s краткая статистика содержит только количество элементов записанных в исходящие файлы
-f полная статистика для чисел дополнительно содержит минимальное и максимальное значения, сумма и среднее.
-o задает путь для результатов
-p задает префикс имен выходных файлов
1.txt 2.txt файлы для обработки
Пример команды для запуска
java -jar target\shift-test-task-1.0.jar -f -o C:\...\some\path -p sample- 1.txt 2.txt
