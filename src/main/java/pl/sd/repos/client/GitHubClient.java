package pl.sd.repos.client;

import lombok.SneakyThrows;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;
import pl.sd.repos.dto.BranchDto;
import pl.sd.repos.dto.RepoDto;
import pl.sd.repos.model.BranchModel;
import pl.sd.repos.model.RepoModel;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 * Client class for interacting with the GitHub API to fetch repository and branch information.
 */
@Component
public class GitHubClient {
    /**
     * The base URL for retrieving user repositories from the GitHub API.
     */
    private static final String REPOS_URL = "https://api.github.com/users/{username}/repos";

    /**
     * The base URL for retrieving branches from repositories in the GitHub API.
     */
    private static final String BRANCHES_URL = "https://api.github.com/repos/{username}/{repository}/branches";

    /**
     * The RestTemplate used for making HTTP requests to the GitHub API.
     */
    private final RestClient restClient = RestClient.create();

    /**
     * Retrieves information about repositories owned by a user from the GitHub API.
     *
     * @param username The username of the GitHub user.
     * @return A list of RepoModel objects representing the user's repositories.
     */
    @SneakyThrows
    public List<RepoModel> getUserRepositoriesInfo(String username) {
        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
            var reposFuture = executor.submit(() -> {
                List<RepoDto> repos = getUserRepositories(username);
                return repos.stream()
                        .map(r -> getRepoModel(executor, r))
                        .collect(Collectors.toList());
            });

            return reposFuture.get();
        }
    }

    /**
     * Retrieves information about branches in provided repository from GitHub API.
     *
     * @param executor concurrent executor, used for tasking with virtual threads.
     * @param repoDto  provided repository.
     * @return RepoModel with required information.
     */
    @SneakyThrows
    private RepoModel getRepoModel(ExecutorService executor, RepoDto repoDto) {
        var branchFuture = executor.submit(() -> getBranchesForRepository(repoDto.owner().login(), repoDto.name()));

        List<BranchModel> branches = branchFuture.get()
                .stream()
                .map(b -> new BranchModel(b.name(), b.commit().sha()))
                .collect(Collectors.toList());
        return new RepoModel(repoDto.owner().login(), repoDto.name(), branches);
    }

    /**
     * Retrieves information about repositories owned by a user from the GitHub API.
     *
     * @param username The username of the GitHub user.
     * @return A list of RepoDto objects representing the user's repositories.
     * @throws HttpClientErrorException If an HTTP error occurs during the request.
     */
    public List<RepoDto> getUserRepositories(String username) throws HttpClientErrorException {
        return restClient.get()
                .uri(REPOS_URL, username)
                .retrieve()
                .body(new ParameterizedTypeReference<List<RepoDto>>() {
                }).stream().filter(r -> !r.fork()).collect(Collectors.toList());
    }

    /**
     * Retrieves information about branches in a repository from the GitHub API.
     *
     * @param username   The username of the owner of the repository.
     * @param repository The name of the repository.
     * @return A list of BranchDto objects representing the branches in the repository.
     */
    public List<BranchDto> getBranchesForRepository(String username, String repository) {
        return restClient.get()
                .uri(BRANCHES_URL, username, repository)
                .retrieve()
                .body(new ParameterizedTypeReference<List<BranchDto>>() {
                });
    }
}
