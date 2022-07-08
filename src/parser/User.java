package parser;

public class User {
    private Boolean ableToWork;
    private String name;
    private Adress adress;
    private Integer age;
    private String[] languages;

    public void setLanguages(String[] languages) {
        this.languages = languages;
    }

    public String[] getLanguages() {
        return languages;
    }

    public void setAdress(Adress adress) {
        this.adress = adress;
    }

    public Adress getAdress() {
        return adress;
    }

    public Integer getAge() {
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

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }
}
