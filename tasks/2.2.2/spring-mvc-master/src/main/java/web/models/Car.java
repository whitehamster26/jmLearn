package web.models;

public class Car {
    private long id;
    private String name;
    private short manufactureYear;

    public Car() {}

    public Car (long id, String name, short manufactureYear) {
        this.id = id;
        this.name = name;
        this.manufactureYear = manufactureYear;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public short getManufactureYear() {
        return manufactureYear;
    }

    public void setManufactureYear(short manufactureYear) {
        this.manufactureYear = manufactureYear;
    }
}
