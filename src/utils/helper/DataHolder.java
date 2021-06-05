package utils.helper;

public final class DataHolder {
    private String userName;
    private final static DataHolder INSTANCE = new DataHolder();

    private DataHolder() {
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public static DataHolder getINSTANCE() {
        return INSTANCE;
    }
}
