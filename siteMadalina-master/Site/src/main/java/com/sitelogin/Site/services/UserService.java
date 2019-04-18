package com.sitelogin.Site.services;

import com.sitelogin.Site.domain.User;
import com.sitelogin.Site.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final Logger log = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers(){
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    public void addUser(User user) throws Exception {
        try {
            userRepository.save(user);
        //eu am prins o exceptie foarte generica
        // in mod normal se prinde cea mai putin generica exceptie;
        // o poti vedea in log-uri in cazul in care incerci sa pui un user cu email deja existent
        // te intreb cum se numeste exceptia
        } catch (Exception exception) {
            // in cazul in care avem un duplicat, primim contraint violation exception
            // pentru a nu ajunge in pagina de eroare, ar prinde aici eroarea
            log.error("could not save the user", user.getEmail(), exception);
            //dupa ce am prins-o, o trimitem mai departe catre controller, care ar trb sa o trimita catre frontend
            throw new Exception("could not save the user ");
        }
    }

    public void updateUser(int ID, User user){
//        userRepository.saveAndFlush(user);
        // nu inteleg de ce nu functioneaza save sau saveAndFlush, in mod normal asta folosim
        //am scris un query de update
        //si ar trb trimsi toti params pt update
        int result = userRepository.updateUser("updated first name", ID);
    }

    public void deleteUser(String username){
        userRepository.deleteById(username);
    }

    public User findUserById(int id) {
//        List<User> users = getAllUsers();
//        for(int i = 0; i < users.size(); i++)
//            if(users.get(i).getID() == id)
//                return users.get(i);
//         return null;
        User user = null;
        Optional<User> optionalUser = userRepository.findUserById(id);
        if (optionalUser.isPresent())
            user = optionalUser.get();

        return user;
    }

    public User getUpdatedUser(User oldUser, User newUser){
        User updatedUser = new User();
        if(newUser.getUserName() == null)
            updatedUser.setUserName(oldUser.getUserName());
        else
            updatedUser.setUserName(newUser.getUserName());

        if(newUser.getLastName() == null)
            updatedUser.setUserName(oldUser.getLastName());
        else
            updatedUser.setUserName(newUser.getLastName());

        if(newUser.getFirstName() == null)
            updatedUser.setUserName(oldUser.getFirstName());
        else
            updatedUser.setUserName(newUser.getFirstName());

        if(newUser.getPassword() == null)
            updatedUser.setUserName(oldUser.getPassword());
        else
            updatedUser.setUserName(newUser.getPassword());

        if(newUser.getEmail() == null)
            updatedUser.setUserName(oldUser.getEmail());
        else
            updatedUser.setUserName(newUser.getEmail());

        return updatedUser;

    }


}
