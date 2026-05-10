package ru.yandex.practicum.delivery;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DeliveryApp {

    private static final Scanner scanner = new Scanner(System.in);
    private static List<Parcel> allParcels = new ArrayList<>();
    private static List<Trackable> trackableParcels = new ArrayList<>();
    private static ParcelBox standardBox = new ParcelBox(52);
    private static ParcelBox fragileBox = new ParcelBox(67);
    private static ParcelBox perishableBox = new ParcelBox(152);

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            showMenu();
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    addParcel();
                    break;
                case 2:
                    sendParcels();
                    break;
                case 3:
                    calculateCosts();
                    break;
                case 4:
                    reportStatusAll();
                    break;
                case 5:
                    showBox();
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("Неверный выбор.");
            }
        }
    }

    private static void showMenu() {
        System.out.println("Выберите действие:");
        System.out.println("1 — Добавить посылку");
        System.out.println("2 — Отправить все посылки");
        System.out.println("3 — Посчитать стоимость доставки");
        System.out.println("4 — Статус трекинга");
        System.out.println("5 — Показать содержимое коробки");
        System.out.println("0 — Завершить");
    }

    private static void addParcel() {

        System.out.println("Введите тип посылки (1 - Стандарт, 2 - Хрупкая, 3 - Скоропортящаяся):");
        int choice = Integer.parseInt(scanner.nextLine());
        System.out.println("Введите описание:");
        String description = scanner.nextLine();
        System.out.println("Введите вес:");
        int weight = Integer.parseInt(scanner.nextLine());
        System.out.println("Введите адрес:");
        String address = scanner.nextLine();
        System.out.println("Введите день отправки:");
        int sendDay = Integer.parseInt(scanner.nextLine());

        Parcel parcel;
        boolean addedToBox = false;
        switch (choice) {
            case 1:
                parcel = new StandardParcel(description, weight, address, sendDay);
                addedToBox = standardBox.addParcel((StandardParcel) parcel);
                break;
            case 2:
                parcel = new FragileParcel(description, weight, address, sendDay);
                addedToBox = fragileBox.addParcel((FragileParcel)parcel);
                if (addedToBox) {
                    trackableParcels.add((Trackable) parcel);
                }
                break;
            case 3:
                System.out.println("Введите срок годности:");
                int timeToLive = Integer.parseInt(scanner.nextLine());
                parcel = new PerishableParcel(description, weight, address, sendDay, timeToLive);
                addedToBox = perishableBox.addParcel((PerishableParcel) parcel);
                break;
            default:
                System.out.println("Неверный тип");
                return;
        }
        if (addedToBox) {
            allParcels.add(parcel);
            System.out.println("Посылка добавлена!");
        } else {
            System.out.println("Посылка не добавлена: не помещается в коробку.");
        }

    }

    private static void reportStatusAll() {
        System.out.println("Введите новое местоположение: ");
        String newLocation = scanner.nextLine();
        for (Trackable parcels : trackableParcels) {
            parcels.reportStatus(newLocation);
        }
    }

    private static void showBox() {
        System.out.println("Выберите тип коробки:");
        System.out.println("1 — Стандартные");
        System.out.println("2 — Хрупкие");
        System.out.println("3 — Скоропортящиеся");

        int choice = Integer.parseInt(scanner.nextLine());

        List<Parcel> parcels = null;
        switch (choice) {
            case 1:
                parcels = standardBox.getAllParcels();
                break;
            case 2:
                parcels = fragileBox.getAllParcels();
                break;
            case 3:
                parcels = perishableBox.getAllParcels();
                break;
            default:
                System.out.println("Неверный тип");
                return;
        }

        if (parcels.isEmpty()) {
            System.out.println("Коробка пуста.");
        } else {
            System.out.println("Содержимое коробки:");
            for (Parcel p : parcels) {
                System.out.println(p.getDescription());
            }
        }
    }
    private static void sendParcels() {
        for (Parcel p : allParcels) {
            p.packageItem();
            p.deliver();
        }
    }

    private static void calculateCosts() {
        int total = 0;
        for (Parcel p : allParcels) {
            total += p.calculateDeliveryCost();
        }
        System.out.println("Общая стоимость доставки: " + total);
    }
    }
