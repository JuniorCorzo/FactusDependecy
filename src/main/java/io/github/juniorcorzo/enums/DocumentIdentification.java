package io.github.juniorcorzo.enums;

public enum DocumentIdentification {
    BIRTH_CERTIFICATE(1),
    IDENTITY_CARD(2),
    CITIZENSHIP_ID(3),
    FOREIGNER_ID_CARD(4),
    FOREIGNER_ID(5),
    NIT(6),
    PASSPORT(7),
    FOREIGN_ID_DOCUMENT(8),
    PEP(9),
    FOREIGN_NIT(10),
    NUIP(11);

    private final int id;

    DocumentIdentification(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

}
