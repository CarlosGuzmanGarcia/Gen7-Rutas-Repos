package com.guzman.app.rutas.models.enums;

public enum Tipos {


    /*TRAILER,
    TORTON,
    DOBLE_REMOLQUE,
    VOLTEO,
    SEMI_REMOLQUE*/

    TRAILER ("Trailer"){
        @Override
        public String toString() {
            return "Trailer";
        }
    }, TORTON ("Torton"){
        @Override
        public String toString() {
            return "Torton";
        }
    },
    VOLTEO ("Volteo"){
        @Override
        public String toString() {
            return "Volteo";
        }
    },
    SEMI_REMOLQUE ("Semi Remolque"){
        @Override
        public String toString() {
            return "Semi Remolque";
        }
    };

    private final String type;

    private Tipos(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public static Tipos deValor(String value) {
        for (Tipos tipo : Tipos.values()) {
            if (tipo.getType().equalsIgnoreCase(value)) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("Tipo de camión no válido: " + value);
    }
}
