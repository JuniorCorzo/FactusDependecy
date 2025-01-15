package io.github.juniorcorzo.context.implementations;

import io.github.juniorcorzo.context.interfaces.DataContext;
import io.github.juniorcorzo.dto.tributes.TributesDTO;
import io.github.juniorcorzo.services.TributesService;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryTributesContext implements DataContext<Integer, TributesDTO> {
    private static volatile InMemoryTributesContext instance;
    private final Map<Integer, TributesDTO> tributesContext;
    private final TributesService tributesService;

    private InMemoryTributesContext() {
        tributesContext = new ConcurrentHashMap<>();
        tributesService = new TributesService();

        this.refreshContext();
    }

    public static InMemoryTributesContext getInstance() {
        synchronized (InMemoryTributesContext.class) {
            if (instance == null) instance = new InMemoryTributesContext();
        }

        return instance;
    }

    @Override
    public boolean containsKey(Integer key) {
        return this.tributesContext.containsKey(key);
    }

    @Override
    public Map<Integer, TributesDTO> getContext() {
        return this.tributesContext;
    }

    @Override
    public TributesDTO getValue(Integer key) {
        return tributesContext.get(key);
    }

    @Override
    public void refreshContext() {
        this.tributesService
                .getTributes()
                .forEach(tributes -> this.tributesContext.put(tributes.id(), tributes));
    }
}
