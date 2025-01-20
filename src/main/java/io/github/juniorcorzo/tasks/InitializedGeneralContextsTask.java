package io.github.juniorcorzo.tasks;

import io.github.juniorcorzo.context.implementations.InMemoryMunicipalityContext;
import io.github.juniorcorzo.context.implementations.InMemoryNumberingRangeContext;
import io.github.juniorcorzo.context.implementations.InMemoryTributesContext;

public class InitializedGeneralContextsTask implements Runnable {
    @Override
    public void run() {
        InMemoryNumberingRangeContext.getInstance().refreshContext();
        InMemoryTributesContext.getInstance().refreshContext();
        InMemoryMunicipalityContext.getInstance().refreshContext();
    }
}
