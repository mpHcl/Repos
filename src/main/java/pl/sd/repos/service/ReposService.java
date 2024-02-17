package pl.sd.repos.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import pl.sd.repos.client.GitHubClient;
import pl.sd.repos.dto.BranchDto;
import pl.sd.repos.model.RepoModel;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReposService {

    private final GitHubClient gitHubClient;

    public List<RepoModel> getUserRepositoriesInfo(String name) throws HttpClientErrorException {
        return gitHubClient.getUserRepositoriesInfo(name);
    }

    public List<BranchDto> getBranches() {
        return gitHubClient.getBranchesForRepository("torvalds", "linux");
    }
}
