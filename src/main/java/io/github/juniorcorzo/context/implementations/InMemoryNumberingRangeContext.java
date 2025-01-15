package io.github.juniorcorzo.context.implementations;

import io.github.juniorcorzo.context.interfaces.DataContext;
import io.github.juniorcorzo.dto.numberingRange.NumberingRangeDTO;
import io.github.juniorcorzo.services.NumberingRangeService;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryNumberingRangeContext implements DataContext<Integer, NumberingRangeDTO> {
    private static volatile InMemoryNumberingRangeContext instance;
    private final Map<Integer, NumberingRangeDTO> numberingContext;
    private final NumberingRangeService numberingRangeService;

    private InMemoryNumberingRangeContext() {
        numberingContext = new ConcurrentHashMap<>();
        numberingRangeService = new NumberingRangeService();

        this.refreshContext();
    }

    public static InMemoryNumberingRangeContext getInstance() {
        synchronized (InMemoryNumberingRangeContext.class) {
            if (instance == null) instance = new InMemoryNumberingRangeContext();
        }
        return instance;
    }

    @Override
    public boolean containsKey(Integer key) {
        return this.numberingContext.containsKey(key);
    }

    @Override
    public Map<Integer, NumberingRangeDTO> getContext() {
        return this.numberingContext;
    }

    @Override
    public NumberingRangeDTO getValue(Integer key) {
        return this.numberingContext.get(key);
    }

    @Override
    public void refreshContext() {
        this.numberingRangeService
                .getNumberingRanges()
                .forEach(numberings -> this.numberingContext.put(numberings.id(), numberings));
    }
}
