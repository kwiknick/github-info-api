package io.willard.github_info_api.controllers;

import io.willard.github_info_api.models.GithubCombinedUserInfo;
import io.willard.github_info_api.services.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user-info")
@Validated
public class UserInfoController {

    private static final Logger log = LogManager.getLogger();

    private final UserService userService;

    @Autowired
    public UserInfoController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping(value = "/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GithubCombinedUserInfo> getUserInfo(@Valid @PathVariable
                                                                @NotBlank
                                                                @Pattern(regexp = "^(?!.*--)[a-zA-Z0-9]+(?:-[a-zA-Z0-9]+)*$",
                                                                         message = "The supplied username is not valid. Only Alphnumerical and Single Hyphens are allowed.")
                                                                    String username) {
        log.debug("Gathering Github User Info and Repository Info");
        return new ResponseEntity<>(new GithubCombinedUserInfo(userService.getUserInfo(username), userService.getUserRepoInfo(username)), HttpStatus.OK);
    }
}
