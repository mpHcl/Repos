package pl.sd.repos.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import pl.sd.repos.client.GitHubClient;
import pl.sd.repos.model.RepoModel;

import java.util.List;

/**
 * Service class for retrieving information about GitHub repositories.
 */
@Service
@RequiredArgsConstructor
public class ReposService {
    /**
     * Client for interacting with the GitHub API.
     */
    private final GitHubClient gitHubClient;

    /**
     * Retrieves information about repositories owned by a user from the GitHub API.
     * @param name The username of the owner of the repositories.
     * @return A list of RepoModel objects representing the repositories.
     * @throws HttpClientErrorException If an HTTP error occurs during the request.
     */
    public List<RepoModel> getUserRepositoriesInfo(String name) throws HttpClientErrorException {
        return gitHubClient.getUserRepositoriesInfo(name);
    }
}
