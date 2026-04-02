package cs.sbs.web.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class DataStore {
    public static final List<MenuItem> menu = Arrays.asList(
            new MenuItem("Fried Rice", 8),
            new MenuItem("Fried Noodles", 9),
            new MenuItem("Burger", 10)
    );

    public static final List<Order> orders = new ArrayList<>();

    public static final AtomicInteger orderIdCounter = new AtomicInteger(1000);
}//