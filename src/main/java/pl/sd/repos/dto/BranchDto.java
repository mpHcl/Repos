package pl.sd.repos.dto;

import lombok.Getter;

/**
 * Data Transfer Object (DTO) representing a branch in a Git repository.
 */
public record BranchDto (String name, BranchCommitDto commit){}

