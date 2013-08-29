package pl.mjedynak;

import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;

import java.util.concurrent.atomic.AtomicInteger;

@ManagedResource
public class DiscountService {

    private AtomicInteger globalDiscount = new AtomicInteger(12);

    public int calculateDiscount() {
        return globalDiscount.get() * 2;
    }

    @ManagedOperation
    public int checkGlobalDiscount() {
        return globalDiscount.get();
    }

    @ManagedOperation
    public void modifyGlobalDiscount(int newDiscount) {
        globalDiscount.set(newDiscount);
    }
}
