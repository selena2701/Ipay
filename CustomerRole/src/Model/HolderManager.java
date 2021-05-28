package Model;

public final class HolderManager {
    private final static HolderManager INSTANCE = new HolderManager();
    public HolderManager(){}
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public static HolderManager getInstance(){return INSTANCE;}
}
