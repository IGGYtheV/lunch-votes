package com.iggy.lunchvotes.web.user;

import com.iggy.lunchvotes.model.user.User;
import com.iggy.lunchvotes.to.UserTo;
import com.iggy.lunchvotes.util.UserUtil;
import com.iggy.lunchvotes.web.JwtUser;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import static com.iggy.lunchvotes.util.validation.ValidationUtil.assureIdConsistent;

@RestController
@RequestMapping(value = ProfileController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class ProfileController extends AbstractUserController {
    static final String REST_URL = "/api/v1.0/profile";

    @GetMapping
    public User get(JwtUser jwtUser) {
        return findByJwtUser(jwtUser);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(JwtUser jwtUser) {
        super.delete(jwtUser.id());
    }


    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    public void update(@RequestBody @Valid UserTo userTo, JwtUser jwtUser) {
        assureIdConsistent(userTo, jwtUser.id());
        User user = findByJwtUser(jwtUser);
        prepareAndSave(UserUtil.updateFromTo(user, userTo));
    }
}