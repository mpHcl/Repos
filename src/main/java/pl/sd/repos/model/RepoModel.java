package pl.sd.repos.model;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

/**
 * Represents a GitHub repository.
 */
@Getter
@Builder
public class RepoModel {
    /**
     * The name of the repository.
     */
    private String name;

    /**
     * The login of the owner of the repository.
     */
    private String ownerLogin;

    /**
     * The list of branches in the repository.
     */
    private List<BranchModel> branches;
}
