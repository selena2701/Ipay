package MODELS;

public class Bank {
    public enum BANK_ENUM {
        VCB,
        ACB,
        BIDV,
        ARB,
    }

    public static String bankToString(BANK_ENUM bank) {
        switch (bank) {
            case VCB:
                return "Vietcombank";
            case ACB:
                return "ACB";
            case BIDV:
                return "BIDV";
            case ARB:
                return "Agribank";
        }
        return "";
    }

    public static BANK_ENUM stringToBank(String string) {
        switch (string) {
            case "Vietcombank":
                return BANK_ENUM.VCB;
            case "ACB":
                return BANK_ENUM.ACB;
            case "BIDV":
                return BANK_ENUM.BIDV;
            default:
                return BANK_ENUM.ARB;
        }
    }
}
