package models;

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
