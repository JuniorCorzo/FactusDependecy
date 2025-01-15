package io.github.juniorcorzo.context.implementations;

import io.github.juniorcorzo.context.interfaces.DataContext;
import io.github.juniorcorzo.dto.measurement.MeasurementUnitsDTO;
import io.github.juniorcorzo.services.UnitMeasurementService;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryUnitMeasurementContext implements DataContext<Integer, MeasurementUnitsDTO> {
    private static volatile InMemoryUnitMeasurementContext instance;
    private final Map<Integer, MeasurementUnitsDTO> measurementContext;
    private final UnitMeasurementService measurementService;

    private InMemoryUnitMeasurementContext() {
        this.measurementService = new UnitMeasurementService();
        measurementContext = new ConcurrentHashMap<>();
    }

    public static InMemoryUnitMeasurementContext getInstance() {
        synchronized (InMemoryUnitMeasurementContext.class) {
            if (instance == null) instance = new InMemoryUnitMeasurementContext();
        }

        return instance;
    }

    @Override
    public boolean containsKey(Integer key) {
        return this.measurementContext.containsKey(key);
    }

    @Override
    public Map<Integer, MeasurementUnitsDTO> getContext() {
        return this.measurementContext;
    }

    @Override
    public MeasurementUnitsDTO getValue(Integer key) {
        return this.measurementContext.get(key);
    }

    @Override
    public void refreshContext() {
        this.measurementService
                .getMeasurementUnits()
                .forEach(measurement -> this.measurementContext.put(measurement.id(), measurement));
    }
}
