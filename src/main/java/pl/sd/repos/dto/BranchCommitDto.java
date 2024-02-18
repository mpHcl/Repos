package pl.sd.repos.dto;

import lombok.Getter;


/**
 * Data Transfer Object (DTO) representing a commit in a branch of a Git repository.
 */
@Getter
public class BranchCommitDto {
    /**
     * The SHA of the last commit.
     */
    private String sha;
}
