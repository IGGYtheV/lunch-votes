package com.iggy.lunchvotes.web.user;

import com.iggy.lunchvotes.error.IllegalRequestDataException;
import com.iggy.lunchvotes.model.user.User;
import com.iggy.lunchvotes.repository.UserRepository;
import com.iggy.lunchvotes.util.UserUtil;
import com.iggy.lunchvotes.web.JwtUser;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import static org.slf4j.LoggerFactory.getLogger;

public abstract class AbstractUserController {

    protected final Logger log = getLogger(getClass());

    @Autowired
    protected UserRepository repository;

    @Autowired
    private UniqueMailValidator emailValidator;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(emailValidator);
    }

    public ResponseEntity<User> get(int id) {
        log.info("get {}", id);
        return ResponseEntity.of(repository.findById(id));
    }

    public void delete(int id) {
        log.info("delete {}", id);
        repository.deleteExisted(id);
    }

    protected User prepareAndSave(User user) {
        return repository.save(UserUtil.prepareToSave(user));
    }

    protected User findByJwtUser(JwtUser jwtUser) {
        return repository.findById(jwtUser.id()).orElseThrow(
                () -> new IllegalRequestDataException("User id='" + jwtUser.getId() + "' was not found"));
    }
}