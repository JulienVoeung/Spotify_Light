package dto;

public class UserDTO {

    private String firstName;
    private String lastName;
    private String pseudo;
    private String email;
    private String dateBirth;

    public UserDTO(String firstName, String lastName, String pseudo, String email, String dateBirth){
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
