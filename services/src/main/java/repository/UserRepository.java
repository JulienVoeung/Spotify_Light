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

    public void addUser(User user){ this.userRepository.add(user);  }

    public void deleteUser(User user){
        for(User u : this.userRepository){
            if(u.getId().equals(user.getId())){
                this.userRepository.remove(u);
            }
        }
    }

    public List<User> listUser(){
        return this.userRepository;
    }

    public User getUserById(UUID id){
        for(User u : this.userRepository){
            if(u.getId().equals(id)){
                return u;
            }
        }
        return null;
    }
}
