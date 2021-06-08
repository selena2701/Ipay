<<<<<<< HEAD:src/MODELS/Region.java
package MODELS;
=======
package models;
>>>>>>> b46c0cc0da32f7bf4e24fa2aa3679d67f7533556:src/models/Region.java

public class Region {
    public enum REGION_ENUM {
        NorthSide,
        MidSide,
        SouthSide,
        WestSide,
    }

    public static String regionToString(REGION_ENUM region) {
        switch (region) {
            case NorthSide:
                return "Miền Bắc";
            case MidSide:
                return "Miền Trung";
            case SouthSide:
                return "Miền Nam";
            case WestSide:
                return "Miền Tây";
        }
        return "";
    }

    public static REGION_ENUM stringToRegion(String string) {
        switch (string) {
            case "Miền Bắc":
                return REGION_ENUM.NorthSide;
            case "Miền Trung":
                return REGION_ENUM.MidSide;
            case "Miền Nam":
                return REGION_ENUM.SouthSide;
            default:
                return REGION_ENUM.WestSide;
        }
    }
}
