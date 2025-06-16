package FinalPrototype;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class IdGenerator {

	private static final AtomicLong customerCounter = new AtomicLong(0);
    private static final AtomicLong staffCounter = new AtomicLong(0);
    private static final AtomicLong orderCounter = new AtomicLong(0);

    public static String generateCustomerId() {
        return "CUST-" + String.format("%04d", customerCounter.incrementAndGet());
    }

    public static String generateStaffId() {
        return "STAFF-" + String.format("%04d", staffCounter.incrementAndGet());
    }

    public static String generateOrderId() {
        return "ORDER-" + String.format("%05d", orderCounter.incrementAndGet());
    }

    // For OrderItem, a UUID might be simpler if they don't need sequential human-readable IDs
    public static String generateOrderItemId() {
        return UUID.randomUUID().toString().substring(0, 8);
    }
}
