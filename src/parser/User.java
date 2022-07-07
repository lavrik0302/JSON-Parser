package parser;

public class User {
    Boolean ableToWork;
    String name;
    Adress adress;
    int age;


    public Adress getAdress() {
        return adress;
    }

    public int getAge() {
        return age;
    }

    public Boolean getAbleToWork() {
        return ableToWork;
    }

    public String getName() {
        return name;
    }

    public void setAbleToWork(Boolean ableToWork) {
        this.ableToWork = ableToWork;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }
}
