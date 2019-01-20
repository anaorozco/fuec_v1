package com.gmail.anamara.backend;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Stream;

class StaticData {

    private static final String MINERAL_WATER = "Mineral Water";
    private static final String SOFT_DRINK = "Soft Drink";
    private static final String COFFEE = "Coffee";
    private static final String TEA = "Tea";
    private static final String DAIRY = "Dairy";
    private static final String CIDER = "Cider";
    private static final String BEER = "Beer";
    private static final String WINE = "Wine";
    private static final String OTHER = "Other";
    
    // Transport labels //
    
    private static final String T_ESPECIAL = "Transporte Especial";
    private static final String T_TAXI = "Taxi Compartido";

    public static final String UNDEFINED = "Sin definir";
    
    static final Map<String, String> BEVERAGES = new LinkedHashMap<>();

    static {

        
        Stream.of("Mauricio Londoño",
                "Van 8 PAX - T.E. Aloha",
                "T.Especial")
                .forEach(name -> BEVERAGES.put(name, T_ESPECIAL));
        
        Stream.of("Somos Tu Chofer",
                "T. Especial - Micro",
                "T. Especial")
                .forEach(name -> BEVERAGES.put(name, T_ESPECIAL));
        
        
        Stream.of("Contratante",
                "Placa",
                "Vehiculo - Tipo")
                .forEach(name -> BEVERAGES.put("", UNDEFINED));

    }

    /** This class is not meant to be instantiated. */
    private StaticData() {
    }
}
