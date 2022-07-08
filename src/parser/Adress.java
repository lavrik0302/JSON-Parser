package parser;

public class Adress {
    private Integer houseNumber;
    private String street;

   private City city;
    public enum City{
        Grodno,
       Minsk,
      Vitebsk,
       Gomel,
        Mogilev,
        Brest
           }

   public void setCity(City city) {
        this.city = city;
    }

    public City getCity() {
       return city;
    }

    public void setHouseNumber(Integer houseNumber) {
        this.houseNumber = houseNumber;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public String getStreet() {
        return street;
    }
}
