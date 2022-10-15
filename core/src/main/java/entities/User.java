package entities;

public class User extends Entity{

    private final String firstName;
    private final String lastName;
    private final String pseudo;
    private final String email;
    private final String dateBirth;

    public User(String firstName, String lastName, String pseudo, String email, String dateBirth){
        this.firstName = firstName;
        this.lastName = lastName;
        this.pseudo = pseudo;
        this.email = email;
        this.dateBirth = dateBirth;
    }

    public String getFirstName(){
        return this.firstName;
    }

    public String getLastName(){
        return this.lastName;
    }

    public String getPseudo(){
        return this.pseudo;
    }

    public String getEmail(){
        return this.email;
    }

    public String getDateBirth(){ return this.dateBirth; }

}
