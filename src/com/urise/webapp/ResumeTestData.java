package com.urise.webapp;

import com.urise.webapp.model.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import static com.urise.webapp.ResumeTestData.DataBase.*;
import static com.urise.webapp.ResumeTestData.DemoUtils.*;
import static com.urise.webapp.model.ContactType.*;
import static com.urise.webapp.model.SectionType.*;


public class ResumeTestData {
    public static void main(String[] args) {
        System.out.println();
        Resume resume = new Resume(FULL_NAME);

        resume.setContact(HOME_PHONE, telContact);
        resume.setContact(SKYPE, skypeContact);
        resume.setContact(MAIL, mailContact);
        resume.setContact(LINKEDIN, linkedInContact);
        resume.setContact(GITHUB, gitHubContact);
        resume.setContact(STACKOVERFLOW, stackOverflowContact);
        resume.setContact(HOME_PAGE, homePageContact);
        System.out.println();

        resume.setSection(OBJECTIVE, new TextSection(textObjective1));
        resume.setSection(PERSONAL, new TextSection(textPersonal2));
        resume.setSection(ACHIEVEMENT, new ListSection(arrAchievement3));
        resume.setSection(QUALIFICATIONS, new ListSection(arrQualification4));
        resume.setSection(EXPERIENCE, new OrganizationSection(experienceOrganizations));
        resume.setSection(EDUCATION, new OrganizationSection(experienceOrganizations));

//start printing
        System.out.println(resume.getFullName().toUpperCase());
        System.out.println();
//printing contacts
        for (ContactType type : ContactType.values()) {
            String contact = resume.getContact(type);
            if (contact != null) {
                System.out.println(contact);
            }
        }

        System.out.println();
        System.out.println();
//printing sections
        for (SectionType type : SectionType.values()) {
            Section section = resume.getSection(type);

            if ((type == PERSONAL || type == OBJECTIVE) && section != null) {
                System.out.println(type.getTitle().toUpperCase());
                System.out.println(section);
            }
            System.out.println();

            if ((type == QUALIFICATIONS || type == ACHIEVEMENT) && section != null) {
                System.out.println(type.getTitle().toUpperCase());

                ListSection listSection = (ListSection) section;

                for (int i = 0; i < listSection.getItems().size(); i++) {
                    String item = listSection.getItems().get(i);
                    if (item != null) {
                        System.out.println("*  " + item + "\n");
                    }
                }
                System.out.println();
            }

            if ((type == EXPERIENCE || type == EDUCATION) && section != null) {
                System.out.println(type.getTitle().toUpperCase());

                OrganizationSection organizations = (OrganizationSection) section;

                for (int i = 0; i < organizations.getOrganizations().size(); i++) {
                    Organization item = organizations.getOrganizations().get(i);
                    if (item != null) {

                        System.out.println("*  " + item + "\n");
                    }
                }
                System.out.println();
            }

        }

        System.out.println("_____________________________________________________________________________");


        Map<SectionType, Object> structure = new HashMap<>();
        String textObjective = textObjective1;
        structure.put(OBJECTIVE, textObjective);
        System.out.println(structure.get(OBJECTIVE));


    }


    static class DemoUtils {

        static String formatText(int width, String str) {
            String[] stringArr = str.split(" ");
            String[] formattedArr = new String[stringArr.length];
            int currentLength = 0;
            int start = 0;
            int caretNum = 0;

            for (int i = 0; i < stringArr.length; i++) {
                currentLength = currentLength + stringArr[i].length();

                if (i == stringArr.length - 1 || currentLength + stringArr[i + 1].length() >= width) {
                    String[] tempArr = new String[i + 1 - start];
                    System.arraycopy(stringArr, start, tempArr, 0, i + 1 - start);

                    formattedArr[caretNum++] = String.join(" ", tempArr);
                    start = ++i;
                    currentLength = 0;
                }
            }

            String[] outputArr = new String[caretNum];
            System.arraycopy(formattedArr, 0, outputArr, 0, caretNum);

            return String.join("\n", outputArr);
        }

        static String formatText(String str) {
            int defaultWidth = 70;
            return formatText(defaultWidth, str);
        }

        static LocalDate toLocDate(boolean isStart, String stringDate) {
            String[] strings = stringDate.split(" - ");
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            LocalDate startDate = LocalDate.parse("01/" + strings[0], dtf);
            LocalDate endDate = LocalDate.parse("01/" + strings[1], dtf);
            return isStart ? startDate : endDate;
        }

        static LocalDate toLocDate(String stringDate) {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            return LocalDate.parse("01/" + stringDate, dtf);
        }


    }


    static class DataBase {
        static final String FULL_NAME = "Григорий Кислин";

        //      contacts:
        static String telContact = "Тел.: +7(962)5179133";
        static String skypeContact = "skype:grigory.kislin";
        static String mailContact = "yevhenboiev@gmail.com";
        static String linkedInContact = "Профиль LinkedIn";
        static String gitHubContact = "Профиль GitHub";
        static String stackOverflowContact = "Профиль Stackoverflow";
        static String homePageContact = "Домашняя страница";

        //      all the sections:

        //      text sections
        static String textObjective1 = formatText("Ведущий стажировок и корпоративного обучения по Java Web и " +
                "Enterprise технологиям");

        static String textPersonal2 = formatText("Аналитический склад ума, сильная логика, креативность, " +
                "инициативность. Пурист кода и архитектуры.");

        //      list sections
        static String[] arrAchievement3 = {
                formatText("Организация команды и успешная реализация Java проектов для сторонних " +
                        "заказчиков: приложения автопарк на стеке Spring Cloud/микросервисы, система мониторинга " +
                        "показателей " +
                        "спортсменов на Spring Boot, участие в проекте МЭШ на Play-2, многомодульный Spring Boot + " +
                        "Vaadin " +
                        "проект для комплексных DIY смет"),

                formatText("ОС 2013 года: разработка проектов \"Разработка Web приложения\",\"Java" +
                        " Enterprise\", \"Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы " +
                        "(JAX-RS/SOAP). " +
                        "Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн стажировок и ведение проектов. " +
                        "Более 3500 " +
                        "выпускников."),

                formatText("Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. " +
                        "Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk."),

                formatText("Налаживание процесса разработки и непрерывной интеграции ERP системы River BPM. " +
                        "Интеграция с 1С, Bonita BPM, CMIS, LDAP. Разработка приложения управления окружением на " +
                        "стеке: Scala/Play/Anorm/JQuery. Разработка SSO аутентификации и авторизации различных ERP " +
                        "модулей, интеграция CIFS/SMB java сервера."),

                formatText("Реализация c нуля Rich Internet Application приложения на стеке технологий JPA, Spring, " +
                        "Spring-MVC, GWT, ExtGWT (GXT), Commet, HTML5, Highstock для алгоритмического трейдинга."),

                formatText("Создание JavaEE фреймворка для отказоустойчивого взаимодействия слабо-связанных сервисов " +
                        "(SOA-base архитектура, JAX-WS, JMS, AS Glassfish). Сбор статистики сервисов и информации о " +
                        "состоянии через систему мониторинга Nagios. Реализация онлайн клиента для администрирования " +
                        "и мониторинга системы по JMX (Jython/ Django)."),

                formatText("Реализация протоколов по приему платежей всех основных платежных системы России " +
                        "(Cyberplat, Eport, Chronopay, Сбербанк), Белоруcсии(Erip, Osmp) и Никарагуа."),

        };

        static String[] arrQualification4 = {
                formatText("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2"),

                formatText("Version control: Subversion, Git, Mercury, ClearCase, Perforce"),

                formatText("DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle, MySQL, " +
                        "SQLite, MS SQL, HSQLDB"),

                formatText("Languages: Java, Scala, Python/Jython/PL-Python, JavaScript, Groovy"),

                formatText("XML/XSD/XSLT, SQL, C/C++, Unix shell scripts"),

                formatText("Java Frameworks: Java 8 (Time API, Streams), Guava, Java Executor, MyBatis, Spring (MVC, " +
                        "Security, Data, Clouds, Boot), JPA (Hibernate, EclipseLink), Guice, GWT(SmartGWT, " +
                        "ExtGWT/GXT), Vaadin, Jasperreports, Apache Commons, Eclipse SWT, JUnit, Selenium " +
                        "(htmlelements)."),

                formatText("Python: Django."),

                formatText("JavaScript: jQuery, ExtJS, Bootstrap.js, underscore.js"),

                formatText("Scala: SBT, Play2, Specs2, Anorm, Spray, Akka"),

                formatText("Технологии: Servlet, JSP/JSTL, JAX-WS, REST, EJB, RMI, JMS, JavaMail, JAXB, StAX, SAX, " +
                        "DOM, XSLT, MDB, JMX, JDBC, JPA, JNDI, JAAS, SOAP, AJAX, Commet, HTML5, ESB, CMIS, BPMN2, " +
                        "LDAP, OAuth1, OAuth2, JWT."),

                formatText("Инструменты: Maven + plugin development, Gradle, настройка Ngnix"),

                formatText("администрирование Hudson/Jenkins, Ant + custom task, SoapUI, JPublisher, Flyway, Nagios, " +
                        "iReport, OpenCmis, Bonita, pgBouncer"),

                formatText("Отличное знание и опыт применения концепций ООП, SOA, шаблонов проектрирования, " +
                        "архитектурных шаблонов, UML, функционального программирования"),

                formatText("Родной русский, английский \"upper intermediate\""),

        };

        //        organisations section
        static Organization[] experienceOrganizations = {

                new Organization("Java Online Projects", "http://javaops.ru/",
                        toLocDate("10/2013"), LocalDate.now(),
                        "Автор проекта.", "Создание, организация и проведение Java онлайн проектов и стажировок."),

                new Organization("Wrike", "https://www.wrike.com/",
                        toLocDate("10/2014"),
                        toLocDate("01/2016"),
                        "Старший разработчик (backend)", "Проектирование и разработка онлайн платформы управления " +
                        "проектами Wrike (Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). " +
                        "Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO."),

                new Organization("RIT Center", "",
                        toLocDate("04/2012"),
                        toLocDate("10/2014"),
                        "Java архитектор", "Организация процесса разработки системы ERP для разных окружений: " +
                        "релизная политика, версионирование, ведение CI (Jenkins), миграция базы (кастомизация " +
                        "Flyway), конфигурирование системы (pgBoucer, Nginx), AAA via SSO. Архитектура БД и серверной" +
                        " части системы. Разработка интергационных сервисов: CMIS, BPMN2, 1C (WebServices), сервисов " +
                        "общего назначения (почта, экспорт в pdf, doc, html). Интеграция Alfresco JLAN для online " +
                        "редактирование из браузера документов MS Office. Maven + plugin development, Ant, Apache " +
                        "Commons, Spring security, Spring MVC, Tomcat,WSO2, xcmis, OpenCmis, Bonita, Python " +
                        "scripting, Unix shell remote scripting via ssh tunnels, PL/Python"),

                new Organization("Luxoft (Deutsche Bank)", "http://www.luxoft.ru/",
                        toLocDate("12/2010"),
                        toLocDate("04/2012"),
                        "Ведущий программист", "Участие в проекте Deutsche Bank CRM (WebLogic, Hibernate, Spring, " +
                        "Spring MVC, SmartGWT, GWT, Jasper, Oracle). Реализация клиентской и серверной части CRM. " +
                        "Реализация RIA-приложения для администрирования, мониторинга и анализа результатов в области" +
                        " алгоритмического трейдинга. JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Highstock, Commet, " +
                        "HTML5."),

                new Organization("Yota", "https://www.yota.ru/",
                        toLocDate("06/2008"),
                        toLocDate("12/2010"),
                        "Ведущий специалист", "Дизайн и имплементация Java EE фреймворка для отдела \"Платежные " +
                        "Системы\" (GlassFish v2.1, v3, OC4J, EJB3, JAX-WS RI 2.1, Servlet 2.4, JSP, JMX, JMS, " +
                        "Maven2). Реализация администрирования, статистики и мониторинга фреймворка. Разработка " +
                        "online JMX клиента (Python/ Jython, Django, ExtJS)"),

                new Organization("Enkata", "http://enkata.com/",
                        toLocDate("03/2007"),
                        toLocDate("06/2008"),
                        "Разработчик ПО", "Реализация клиентской (Eclipse RCP) и серверной (JBoss 4.2, Hibernate " +
                        "3.0, Tomcat, JMS) частей кластерного J2EE приложения (OLAP, Data mining)."),

                new Organization("Siemens AG", "https://www.siemens.com/ru/ru/home.html",
                        toLocDate("01/2005"),
                        toLocDate("02/2007"),
                        "Разработчик ПО", "Разработка информационной модели, проектирование интерфейсов, реализация и" +
                        " отладка ПО на мобильной IN платформе Siemens @vantage (Java, Unix)."),

                new Organization("Alcatel", "http://www.alcatel.ru/",
                        toLocDate("09/1997"),
                        toLocDate("01/2005"),
                        "Разработчик ПО", "Тестирование, отладка, внедрение ПО цифровой телефонной станции Alcatel " +
                        "1000 S12 (CHILL, ASM).")
        };

        static Organization[] educationOrganizations = {

                new Organization("Coursera", "https://www.coursera.org/course/progfun",
                        toLocDate("03/2013"),
                        toLocDate("05/2013"),
                        "'Functional Programming Principles in Scala' by Martin Odersky", ""),

                new Organization("Luxoft", "http://www.luxoft-training.ru/training/catalog/course.html?ID=22366",
                        toLocDate("03/2011"),
                        toLocDate("04/2011"),
                        "Курс 'Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.'", ""),

                new Organization("Siemens AG", "http://www.siemens.ru/",
                        toLocDate("01/2005"),
                        toLocDate("04/2005"),
                        "3 месяца обучения мобильным IN сетям (Берлин)", ""),

                new Organization("Alcatel", "http://www.alcatel.ru/",
                        toLocDate("09/1997"),
                        toLocDate("03/1998"),
                        "6 месяцев обучения цифровым телефонным сетям (Москва)", ""),

                new Organization("Alcatel", "http://www.alcatel.ru/",
                        toLocDate("09/1997"),
                        toLocDate("03/1998"),
                        "6 месяцев обучения цифровым телефонным сетям (Москва)", ""),

                new Organization("Санкт-Петербургский национальный исследовательский университет информационных " +
                        "технологий, механики и оптики", "http://www.ifmo.ru/",
                        toLocDate("09/1993"),
                        toLocDate("07/1996"),
                        "Аспирантура (программист С, С++)", ""),

                new Organization("Санкт-Петербургский национальный исследовательский университет информационных " +
                        "технологий, механики и оптики", "http://www.ifmo.ru/",
                        toLocDate(true, "09/1987 - 07/1993"),
                        toLocDate(false, "09/1987 - 07/1993"),
                        "Инженер (программист Fortran, C)", ""),

                new Organization("Заочная физико-техническая школа при МФТИ", "http://www.school.mipt.ru/",
                        toLocDate(true, "09/1984 - 06/1987"),
                        toLocDate(false, "09/1984 - 06/1987"),
                        "Закончил с отличием", ""),

        };

    }
}
