package io.github.juniorcorzo.context.implementations;

import io.github.juniorcorzo.context.interfaces.DataContext;
import io.github.juniorcorzo.dto.municipality.MunicipalityDTO;
import io.github.juniorcorzo.services.MunicipalityService;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryMunicipalityContext implements DataContext<Integer, MunicipalityDTO> {
    private static volatile InMemoryMunicipalityContext instance;
    private final Map<Integer, MunicipalityDTO> municipalityContext;
    private final MunicipalityService municipalityService;

    private InMemoryMunicipalityContext() {
        this.municipalityContext = new ConcurrentHashMap<>();
        this.municipalityService = new MunicipalityService();
    }

    public static InMemoryMunicipalityContext getInstance() {
        synchronized (InMemoryMunicipalityContext.class) {
            if (instance == null) {
                instance = new InMemoryMunicipalityContext();
            }
        }
        return instance;
    }

    @Override
    public boolean containsKey(Integer key) {
        return this.municipalityContext.containsKey(key);
    }

    @Override
    public Map<Integer, MunicipalityDTO> getContext() {
        return this.municipalityContext;
    }

    @Override
    public MunicipalityDTO getValue(Integer key) {
        return this.municipalityContext.get(key);
    }

    @Override
    public void refreshContext() {
        this.municipalityService
                .getMunicipalities()
                .forEach((municipality) -> municipalityContext.put(municipality.id(), municipality));
    }
}
