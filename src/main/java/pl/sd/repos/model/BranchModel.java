package pl.sd.repos.model;

import lombok.Builder;
import lombok.Getter;

/**
 * Represents a branch in a Git repository.
 */
public record BranchModel (
    /**
     * The name of the branch.
     */
    String name,

    /**
     * The SHA of last commit in the branch.
     */
    String sha
) {}
