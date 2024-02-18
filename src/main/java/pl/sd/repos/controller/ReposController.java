package pl.sd.repos.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import pl.sd.repos.model.RepoModel;
import pl.sd.repos.service.ReposService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Controller class for handling requests related to repositories.
 */
@RestController
@RequiredArgsConstructor
public class ReposController {
    /**
     * Service for retrieving information about GitHub repositories.
     */
    private final ReposService reposService;

    /**
     * Endpoint to retrieve information about repositories owned by a user.
     * @param name The username of the owner of the repositories.
     * @return ResponseEntity containing a list of RepoModel objects or a status code with an error message if the user is not found.
     */
    @GetMapping("/repos")
    public ResponseEntity<?> getReposInfo(@RequestParam String name) {
        try {
            List<RepoModel> repos = reposService.getUserRepositoriesInfo(name);
            return ResponseEntity.ok(repos);
        } catch (HttpClientErrorException exception) {
            Map<String, Object> response = new HashMap<>();
            response.put("status", HttpStatus.NOT_FOUND.value());
            response.put("message", "User not found.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
}
