package pl.sd.repos.dto;

import lombok.Getter;

/**
 * Data Transfer Object (DTO) representing a GitHub repository.
 * Used for parsing JSON to objects.
 */
@Getter
public class RepoDto {
    /**
     * The name of the repository.
     */
    private String name;

    /**
     * Information about the owner of the repository.
     */
    private OwnerDto owner;

    /**
     * Indicates if the repository is a fork.
     */
    private boolean fork;
}
