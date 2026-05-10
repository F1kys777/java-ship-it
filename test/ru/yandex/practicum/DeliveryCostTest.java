package ru.yandex.practicum;

import ru.yandex.practicum.delivery.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DeliveryCostTest {

    //Coast

    @Test
    void testStandardParcelCost1() {
        StandardParcel parcel = new StandardParcel("Книга", 5, "Moscow", 1);
        assertEquals(10, parcel.calculateDeliveryCost());
    }

    @Test
    void testStandardParcelCost2() {
        StandardParcel parcel = new StandardParcel("Ноутбук", 10, "Tokyo", 2);
        assertEquals(20, parcel.calculateDeliveryCost());
    }

    @Test
    void testStandardParcelCostBorder() {
        StandardParcel parcel = new StandardParcel("Пусто", 0, "Cincinnati", 3);
        assertEquals(0, parcel.calculateDeliveryCost());
    }


    @Test
    void testFragileParcelCost1() {
        FragileParcel parcel = new FragileParcel("Ваза", 2, "Moscow", 5);
        assertEquals(8, parcel.calculateDeliveryCost());
    }

    @Test
    void testFragileParcelCost2() {
        FragileParcel parcel = new FragileParcel("Зеркало", 7, "Tokyo", 6);
        assertEquals(28, parcel.calculateDeliveryCost());
    }

    @Test
    void testFragileParcelCostBorder() {
        FragileParcel parcel = new FragileParcel("Стекло", 0, "Cincinnati", 7);
        assertEquals(0, parcel.calculateDeliveryCost());
    }


    @Test
    void testPerishableParcelCost1() {
        PerishableParcel parcel = new PerishableParcel("Торт", 3, "Moscow", 10, 2);
        assertEquals(9, parcel.calculateDeliveryCost());
    }

    @Test
    void testPerishableParcelCos2() {
        PerishableParcel parcel = new PerishableParcel("Рыба", 6, "Tokyo", 12, 1);
        assertEquals(18, parcel.calculateDeliveryCost());
    }

    @Test
    void testPerishableParcelCostBorder() {
        PerishableParcel parcel = new PerishableParcel("Ягоды", 0, "Cincinnati", 15, 3);
        assertEquals(0, parcel.calculateDeliveryCost());
    }

    //isExpired

    @Test
    void isExpiredFalse() {
        PerishableParcel parcel = new PerishableParcel("Молоко", 1, "Cincinnati", 7, 3);
        assertFalse(parcel.isExpired(9));
    }

    @Test
    void isExpiredTrue() {
        PerishableParcel parcel = new PerishableParcel("Молоко", 1, "Moscow", 7, 3);
        assertTrue(parcel.isExpired(11));
    }

    @Test
    void isExpiredBorder() {
        PerishableParcel parcel = new PerishableParcel("Молоко", 1, "Cincinnati", 7, 3);
        assertFalse(parcel.isExpired(10));
    }

    //"Yeah buddy, lightweight baby!"

    @Test
    void addParcelSuccess() {
        ParcelBox<StandardParcel> box = new ParcelBox<>(50);
        StandardParcel p1 = new StandardParcel("Ronnie", 20, "Moscow", 1);
        StandardParcel p2 = new StandardParcel("Coleman", 29, "Cincinnati", 1);
        box.addParcel(p1);
        box.addParcel(p2);
        assertEquals(2, box.getAllParcels().size());
    }

    @Test
    void addParcelFails() {
        ParcelBox<FragileParcel> box = new ParcelBox<>(40);
        FragileParcel p1 = new FragileParcel("Ronnie", 21, "Moscow", 3);
        FragileParcel p2 = new FragileParcel("Coleman", 20, "Tokyo", 4);
        box.addParcel(p1);
        box.addParcel(p2);
        assertEquals(1, box.getAllParcels().size());
    }

    @Test
    void addParcelBorder() {
        ParcelBox<PerishableParcel> box = new ParcelBox<>(100);
        PerishableParcel p1 = new PerishableParcel("Ronnie", 40, "Tokyo", 5, 2);
        PerishableParcel p2 = new PerishableParcel("Coleman", 60, "Cincinnati", 6, 1);
        box.addParcel(p1);
        box.addParcel(p2);
        assertEquals(2, box.getAllParcels().size());
    }
}
