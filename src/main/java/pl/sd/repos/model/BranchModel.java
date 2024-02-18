package pl.sd.repos.model;

import lombok.Builder;
import lombok.Getter;

/**
 * Represents a branch in a Git repository.
 */
@Getter
@Builder
public class BranchModel {
    /**
     * The name of the branch.
     */
    public String name;

    /**
     * The SHA of last commit in the branch.
     */
    public String sha;
}
