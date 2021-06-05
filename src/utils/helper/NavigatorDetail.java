package utils.helper;

public class NavigatorDetail {

    /*
     * Destination is path to the fxml from views folder
     * e.g: admin/admin-home-screen
     */
    private String destination = "";
    private String title = "";
    private double width = 1080;
    private double height = 720;


    public NavigatorDetail(String destination) {
        this.destination = destination;
    }

    public NavigatorDetail(String destination, String title, double width, double height) {
        this.destination = destination;
        this.title = title;
        this.width = width;
        this.height = height;
    }

    public NavigatorDetail(String destination, String title) {
        this.destination = destination;
        this.title = title;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public String getDestination() {
        return destination;
    }

    public String getTitle() {
        return title;
    }
}
