package pl.sd.repos.client;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import pl.sd.repos.dto.BranchDto;
import pl.sd.repos.dto.RepoDto;
import pl.sd.repos.model.BranchModel;
import pl.sd.repos.model.RepoModel;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * Client class for interacting with the GitHub API to fetch repository and branch information.
 */
@Component
public class GitHubClient {
    /**
     * The base URL for retrieving user repositories from the GitHub API.
     */
    private static final String REPOS_URL = "https://api.github.com/users";

    /**
     * The base URL for retrieving branches from repositories in the GitHub API.
     */
    private static final String BRANCHES_URL = "https://api.github.com/repos";

    /**
     * The RestTemplate used for making HTTP requests to the GitHub API.
     */
    private final RestTemplate restTemplate = new RestTemplate();

    /**
     * Retrieves information about repositories owned by a user from the GitHub API.
     * @param username The username of the GitHub user.
     * @return A list of RepoModel objects representing the user's repositories.
     */
    public List<RepoModel> getUserRepositoriesInfo(String username) {
        List<RepoModel> result = new ArrayList<>();
        var repos = getUserRepositories(username);
        repos.stream().filter(r -> !r.isFork()).forEach(r -> {
            List<BranchModel> branches = new ArrayList<>();
            getBranchesForRepository(r.getOwner().getLogin(), r.getName())
                    .forEach(b ->
                        branches.add(
                                BranchModel.builder()
                                        .name(b.getName())
                                        .sha(b.getCommit().getSha())
                                        .build()
                        )
                    );
            result.add(
                    RepoModel.builder()
                            .ownerLogin(r.getOwner().getLogin())
                            .name(r.getName())
                            .branches(branches)
                            .build());
        });

        return result;
    }

    /**
     * Retrieves information about repositories owned by a user from the GitHub API.
     * @param username The username of the GitHub user.
     * @return A list of RepoDto objects representing the user's repositories.
     * @throws HttpClientErrorException If an HTTP error occurs during the request.
     */
    public List<RepoDto> getUserRepositories(String username) throws HttpClientErrorException {
        String url = REPOS_URL + "/" + username + "/repos";
        ResponseEntity<List<RepoDto>> responseEntity = restTemplate.exchange(
                URI.create(url),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<RepoDto>>() {
                }
        );
        List<RepoDto> repos = responseEntity.getBody();
        if (repos == null || repos.size() == 0) {
            return new ArrayList<>();
        }

        return repos;
    }

    /**
     * Retrieves information about branches in a repository from the GitHub API.
     * @param username The username of the owner of the repository.
     * @param repository The name of the repository.
     * @return A list of BranchDto objects representing the branches in the repository.
     */
    public List<BranchDto> getBranchesForRepository(String username, String repository) {
        String url = BRANCHES_URL + "/" + username + "/" + repository + "/branches";
        ResponseEntity<List<BranchDto>> responseEntity = restTemplate.exchange(
                URI.create(url),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<BranchDto>>() {
                }
        );
        List<BranchDto> branches = responseEntity.getBody();
        if (branches == null || branches.size() == 0) {
            return new ArrayList<>();
        }

        return branches;
    }
}
