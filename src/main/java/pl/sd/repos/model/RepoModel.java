package pl.sd.repos.model;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

/**
 * Represents a GitHub repository.
 */
public record RepoModel (
    /**
     * The name of the repository.
     */
    String name,

    /**
     * The login of the owner of the repository.
     */
    String ownerLogin,

    /**
     * The list of branches in the repository.
     */
    List<BranchModel> branches
) {};
