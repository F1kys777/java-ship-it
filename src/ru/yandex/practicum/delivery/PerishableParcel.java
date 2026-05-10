package ru.yandex.practicum.delivery;

public class PerishableParcel extends Parcel {
    private int timeToLive;
    private static final int PERISHABLE_COST = 3;

    public PerishableParcel (String description,int weight, String deliveryAddress, int sendDay, int timeToLive) {
        super(description, weight, deliveryAddress, sendDay);
        this.timeToLive = timeToLive;
    }

    @Override
    public int getCost() {
        return PERISHABLE_COST;
    }

    public int getTimeToLive() {
        return timeToLive;
    }

    public boolean isExpired(int currentDay) {
        return currentDay > (getSendDay() + getTimeToLive());
    }
}
