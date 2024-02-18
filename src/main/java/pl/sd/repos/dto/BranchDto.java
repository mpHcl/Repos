package pl.sd.repos.dto;

import lombok.Getter;

/**
 * Data Transfer Object (DTO) representing a branch in a Git repository.
 */
@Getter
public class BranchDto {
    /**
     * The name of the branch.
     */
    private String name;

    /**
     * Information about the last commit on the branch.
     */
    private BranchCommitDto commit;
}
