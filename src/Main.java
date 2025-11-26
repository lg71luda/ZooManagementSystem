package zoo;

import zoo.db.AnimalDAO;
import zoo.db.EnclosureDAO;
import zoo.db.StaffDAO;
import zoo.model.Animal;
import zoo.db.FeedingDAO;
import zoo.db.TicketDAO;
import zoo.db.DatabaseConnection;
import zoo.service.AuthService;

import java.sql.*;
import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    private static final Scanner sc = new Scanner(System.in);
    private static final AnimalDAO animalDAO = new AnimalDAO();
    private static final EnclosureDAO enclosureDAO = new EnclosureDAO();
    private static final StaffDAO staffDAO = new StaffDAO();

    public static void main(String[] args) {
        System.out.println("СИСТЕМА УПРАВЛЕНИЯ ЗООПАРКОМ v2.0");

        while (true) {
            System.out.println("\n=== АВТОРИЗАЦИЯ ===");
            System.out.print("Логин: ");
            String login = sc.nextLine();
            System.out.print("Пароль: ");
            String pass = sc.nextLine();

            if (AuthService.login(login, pass)) {
                mainMenu();        // ← после успешного входа переходим в главное меню
                return;            // выходим из main, чтобы не зациклиться
            } else {
                System.out.println("Попробовать снова? (да/нет)");
                if (!sc.nextLine().equalsIgnoreCase("да")) {
                    return;
                }

            }

        }
    }


    private static void mainMenu() {
        while (true) {
            System.out.println("\n=== ГЛАВНОЕ МЕНЮ ===");
            System.out.println("1. Добавить животное");
            System.out.println("2. Добавить вольер");
            System.out.println("3. Добавить сотрудника");
            System.out.println("4. Показать всех животных");
            System.out.println("5. Записать кормление");
            System.out.println("6. Продать билет");
            System.out.println("7. Назначить животное в вольер");
            System.out.println("8. ОТЧЁТЫ");
            System.out.println("9. Зарегистрировать сотрудника" +
                    (AuthService.hasRole("ADMIN") ? "" : " (только админ)"));
            System.out.println("0. Выход");
            System.out.print("Выберите пункт: ");

            int choice = sc.nextInt();
            sc.nextLine(); // чистим буфер

            switch (choice) {
                case 1 -> addAnimalMenu();
                case 2 -> addEnclosureMenu();
                case 3 -> addStaffMenu();
                case 4 -> showAllAnimals();
                case 5 -> addFeedingMenu();
                case 6 -> sellTicket();
                case 7 -> assignAnimalToEnclosure();
                case 8 -> showReports();
                case 9 -> {
                    if (AuthService.hasRole("ADMIN")) {
                        registerNewStaff();
                    } else {
                        System.out.println("Доступ запрещён!");
                    }
                }
                case 0 -> {
                    AuthService.logout();
                    System.out.println("До свидания!");
                    return;
                }
                default -> System.out.println("Неверный пункт!");
            }
        }
    }
    private static void addAnimalMenu() {
        System.out.println("=== Добавление животного ===");
        // TODO: реализация
        System.out.println("Пока не реализовано");
    }

    private static void addEnclosureMenu() {
        System.out.println("=== Добавление вольера ===");
        // TODO: реализация
    }

    private static void addStaffMenu() {
        System.out.println("=== Добавление сотрудника ===");
        // TODO: реализация
    }

    private static void showAllAnimals() {
        System.out.println("=== Все животные ===");
        // TODO: реализация
    }

    private static void addFeedingMenu() {
        System.out.println("=== Добавление кормления ===");
        // TODO: реализация
    }

    private static void sellTicket() {
        System.out.println("=== Продажа билета ===");
        // TODO: реализация
    }

    private static void assignAnimalToEnclosure() {
        System.out.println("=== Закрепление животного за вольером ===");
        // TODO: реализация
    }

    private static void showReports() {
        System.out.println("=== Отчёты ===");
        // TODO: реализация
    }

    private static void registerNewStaff() {
        System.out.println("=== РЕГИСТРАЦИЯ НОВОГО СОТРУДНИКА ===");

        System.out.print("Логин: ");
        String login = sc.nextLine();

        System.out.print("Пароль: ");
        String password = sc.nextLine();

        System.out.print("Имя: ");
        String firstName = sc.nextLine();

        System.out.print("Фамилия: ");
        String lastName = sc.nextLine();

        System.out.print("Роль (ADMIN / VET / KEEPER / CASHIER): ");
        String role = sc.nextLine().toUpperCase();

        // Простое хэширование (пока без BCrypt)
        String passwordHash = password;  // временно, потом заменишь на настоящий хэш

        boolean success = staffDAO.create(login, password, firstName, lastName, role);

        if (success) {
            System.out.println("Сотрудник " + firstName + " " + lastName + " успешно зарегистрирован!");
        } else {
            System.out.println("Ошибка при регистрации. Возможно, логин уже занят.");
        }

        System.out.println("Нажмите Enter для продолжения...");
        sc.nextLine();
    }
}

