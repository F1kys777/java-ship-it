package ru.yandex.practicum.delivery;

public abstract class Parcel {
    //добавьте реализацию и другие необходимые классы

    private String description;
    private int weight;
    private String deliveryAddress;
    private int sendDay;

    public Parcel(String description,int weight, String deliveryAddress, int sendDay) {
        this.description = description;
        this.weight = weight;
        this.deliveryAddress = deliveryAddress;
        this.sendDay = sendDay;
    }

    public String getDescription() {
        return description;
    }

    public int getWeight() {
        return weight;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public int getSendDay() {
        return sendDay;
    }

    public abstract int getCost();

    public void packageItem() {
        System.out.println("Посылка " + getDescription() + " упакована");
    }

    public void deliver() {
        System.out.println("Посылка " + getDescription() + " доставлена по адресу " + getDeliveryAddress() + ".");
    }

    public int calculateDeliveryCost() {
        return weight * getCost();
    }

}
