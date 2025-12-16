# Notes CLI

Простое CLI-приложение для управления заметками, написанное на Java.
Приложение поддерживает запуск локально и через Docker.

---

## Возможности

### v1.0.0
- добавление заметок (add)
- вывод списка заметок (list)

### v1.1.0
- подсчёт количества заметок (count)
- удаление заметки по id (rm)
- добавлен CI (GitHub Actions)

---

## Хранение данных

Заметки сохраняются в файл:

data/notes.csv

Формат строки:

id;текст заметки

Пример:

1;Купить хлеб
2;Позвонить маме

---

## Запуск локально

Компиляция:

javac src/com/example/*.java

Добавить заметку:

java -cp src com.example.App --cmd=add --text="Купить хлеб"

Показать список заметок:

java -cp src com.example.App --cmd=list

Посчитать количество заметок:

java -cp src com.example.App --cmd=count

Удалить заметку по id:

java -cp src com.example.App --cmd=rm --id=1

---

## Запуск через Docker

Сборка Docker-образа:

docker build -t notes-cli:dev .

Создание папки для данных:

mkdir -p data

Команды в Docker

Показать список:

docker run --rm -v "$PWD/data:/app/data" notes-cli:dev --cmd=list

Добавить заметку:

docker run --rm -v "$PWD/data:/app/data" notes-cli:dev --cmd=add --text="Купить хлеб"

Посчитать количество:

docker run --rm -v "$PWD/data:/app/data" notes-cli:dev --cmd=count

Удалить заметку:

docker run --rm -v "$PWD/data:/app/data" notes-cli:dev --cmd=rm --id=1

---

## CI

В проекте используется GitHub Actions.

CI автоматически выполняет компиляцию проекта (javac) при:
- push в любую ветку
- pull request в ветку main

---

## Релизы

v1.0.0 — базовый функционал (add, list)  
v1.1.0 — добавлены команды rm и count, CI