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

@RestController
@RequiredArgsConstructor
public class ReposController {
    private final ReposService reposService;

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
