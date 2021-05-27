package backskin.bankapi.services;

import org.springframework.stereotype.Service;

import java.util.TimeZone;
import java.util.function.Supplier;

@Service
public class TimeZoneService implements Supplier<TimeZone> {
    @Override
    public TimeZone get() {
        return TimeZone.getDefault();
    }
}
