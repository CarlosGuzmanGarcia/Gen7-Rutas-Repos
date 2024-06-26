package com.guzman.app.rutas.models.enums;

public enum Marcas {
    /*VOLVO,
    ALLIANCE,
    FORD,
    MERCEDES_BENZ,
    DINA*/
    VOLVO ("Volvo"){
        @Override
        public String toString() {
            return "Volvo";
        }
    }, ALLIANCE ("Alliance"){
        @Override
        public String toString() {
            return "Alliance";
        }
    },
    FORD ("Ford"){
        @Override
        public String toString() {
            return "Ford";
        }
    },
    MERCEDES_BENZ ("Mercedes benz"){
        @Override
        public String toString() {
            return "Mercedes benz";
        }
    },
    DINA ("Dina"){
        @Override
        public String toString() {
            return "Dina";
        }
    };

    private final String type;

    private Marcas(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public static Marcas deValor(String value) {
        for (Marcas marca : Marcas.values()) {
            if (marca.getType().equalsIgnoreCase(value)) {
                return marca;
            }
        }
        throw new IllegalArgumentException("Marca no válida: " + value);
    }
}
