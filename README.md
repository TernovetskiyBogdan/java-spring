
# Java Spring Парсер вакансій

## 📌 Опис
Це навчальний проєкт на Spring Boot, який виконує парсинг вакансій із сайту, конвертацію зарплат в обрану валюту та збереження даних у Excel-файл. Додатково, дані виводяться у вигляді таблиці через HTML-шаблон.

## 🔧 Технології
- Java
- Spring Boot
- Maven
- REST API
- Jsoup (HTML-парсинг)
- Apache POI (генерація Excel)
- Thymeleaf (HTML-шаблони)

## 📁 Структура проєкту

- `controller/`
  - `ListController.java` — керує виводом списку вакансій у веб-інтерфейсі
  - `VacancyController.java` — REST-контролер для парсингу вакансій

- `model/`
  - `Vacancy.java` — модель вакансії
  - `ExperienceLevel.java` — enum рівня досвіду

- `service/`
  - `CurrencyApiClient.java` — API-клієнт для отримання валютних курсів
  - `ExcelManager.java` — створення Excel-файлу з вакансіями
  - `SalaryConverterService.java` — сервіс для конвертації зарплат
  - `SalaryParserService.java` — сервіс парсингу зарплат
  - `VacancyParserService.java` — головний сервіс для парсингу вакансій

- `templates/`
  - `vacancy-list.html` — HTML-шаблон для відображення списку вакансій

## ⚙️ Запуск проєкту

1. Встановіть JDK 17+ та Maven.
2. Клонуйте репозиторій:
   ```
   git clone https://github.com/TernovetskiyBogdan/java-spring.git
   ```
3. Перейдіть в директорію:
   ```
   cd java-spring
   ```
4. Запустіть додаток:
   ```
   ./mvnw spring-boot:run
   ```

5. Відкрийте в браузері:
   ```
   http://localhost:8080
   ```

## 🧪 Тести

Проєкт містить базовий unit-тест у `ParserApplicationTests.java`.

## 👨‍🎓 Автор

**Ternovetskiy Bohdan**  
Навчальний проєкт з практики (Java + Spring Boot)
