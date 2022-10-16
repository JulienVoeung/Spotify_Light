package repository;

import entities.User;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserRepository {

    private List<User> userRepository;

    public UserRepository(){
        this.userRepository = new ArrayList<User>();
    }

    //TO ADD A USER TO REPOSITORY
    public void addUser(User user){ this.userRepository.add(user);  }

    //TO DELETE A USER FROM REPOSITORY
    public void deleteUser(User user){
        for(User u : this.userRepository){
            if(u.getId().equals(user.getId())){
                this.userRepository.remove(u);
            }
        }
    }

    //TO GET THE REPOSITORY
    public List<User> listUser(){
        return this.userRepository;
    }

    //TO GET A USER BY ID USER
    public User getUserById(UUID id){
        for(User u : this.userRepository){
            if(u.getId().equals(id)){
                return u;
            }
        }
        return null;
    }

    //TO GET A USER BY EMAIL
    public User getUserByEmail(String email){
        for(User u : this.userRepository){
            if(u.getEmail().equals(email)){
                return u;
            }
        }
        return null;
    }

}
