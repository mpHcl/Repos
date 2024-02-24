package pl.sd.repos.dto;

import lombok.Getter;

/**
 * Data Transfer Object (DTO) representing a GitHub repository.
 * Used for parsing JSON to objects.
 */
public record RepoDto(String name, OwnerDto owner, boolean fork){}
