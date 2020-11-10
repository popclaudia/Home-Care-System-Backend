package ro.tuc.ds2020.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.tuc.ds2020.controllers.handlers.exceptions.model.ResourceNotFoundException;
import ro.tuc.ds2020.dtos.UserDTO;
import ro.tuc.ds2020.dtos.UserPrivateDTO;
import ro.tuc.ds2020.dtos.builders.UserBuilder;
import ro.tuc.ds2020.entities.Patient;
import ro.tuc.ds2020.entities.User;
import ro.tuc.ds2020.entities.UserAccount;
import ro.tuc.ds2020.repositories.UserRepository;
import ro.tuc.ds2020.repositories.AccountRepository;

import java.util.Optional;

@Service
public class UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
    private final AccountRepository accountRepository;
    private final UserRepository userRepository;

    @Autowired
    public UserService(AccountRepository accountRepository, UserRepository userRepository) {
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
    }

    public UserPrivateDTO findUserById(Integer id) {
        Optional<UserAccount> prosumerOptional = accountRepository.findById(id);
        if (!prosumerOptional.isPresent()) {
            LOGGER.error("User with id {} was not found in db", id);
            throw new ResourceNotFoundException(User.class.getSimpleName() + " with id: " + id);
        }
        return UserBuilder.toUserPrivateDTO(prosumerOptional.get());
    }
    public UserPrivateDTO findUserByUsername(String username, String pass) {
        Optional<UserAccount> prosumerOptional = accountRepository.findByUsername(username);
        if (!prosumerOptional.isPresent()) {
            LOGGER.error("User with username {} was not found in db", username);
            throw new ResourceNotFoundException(User.class.getSimpleName() + " with username: " + username);
        }
        if (!prosumerOptional.get().getPassword().equals(pass)) {
            LOGGER.error("Wrong password for username {}", username);
            throw new ResourceNotFoundException(User.class.getSimpleName() + " with password: " + pass);
        }
        User user = prosumerOptional.get().getUser();
        String role=user.getUserType();

        return UserBuilder.toUserPrivateDTO(prosumerOptional.get());
    }


    public Integer insert(UserDTO userDTO) {
        Optional<User> prosumerOptional = userRepository.findById(userDTO.getUser());
        if (!prosumerOptional.isPresent()) {
            LOGGER.error("User with id {} was not found in db", userDTO.getUser());
            throw new ResourceNotFoundException(User.class.getSimpleName() + " with id: " + userDTO.getUser());
        }
        User user = prosumerOptional.get();
        System.out.println(user.getName());
        UserAccount cont = UserBuilder.toEntity(userDTO, user);
        user.setUser_account(cont);
        cont = accountRepository.save(cont);
        user = userRepository.save(user);

        LOGGER.debug("User with id {} was inserted in db", user.getID());
        return cont.getId();

    }

}
