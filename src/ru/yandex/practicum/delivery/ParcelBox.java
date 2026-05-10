package ru.yandex.practicum.delivery;

import java.util.ArrayList;
import java.util.List;

public class ParcelBox {
    private final int maxWeight;
    private List<Parcel> parcels = new ArrayList<>();

    public ParcelBox(int maxWeight) {
        this.maxWeight = maxWeight;
    }

    public boolean addParcel(Parcel parcel) {
        int currentWeight = 0;
        for (Parcel p : parcels) {
            currentWeight += p.getWeight();
        }
        if (currentWeight + parcel.getWeight() <= maxWeight) {
            parcels.add(parcel);
            return true;
        } else {
            System.out.println("Не удалось добавить посылку - превышен максимальный вес!");
            return false;
        }
    }

    public List<Parcel> getAllParcels() {
        return new ArrayList<>(parcels);
    }
}
