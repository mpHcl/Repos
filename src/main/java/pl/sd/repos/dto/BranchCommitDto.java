package pl.sd.repos.dto;

import lombok.Getter;


/**
 * Data Transfer Object (DTO) representing a commit in a branch of a Git repository.
 */
public record BranchCommitDto (String sha){}
