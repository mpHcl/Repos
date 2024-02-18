package pl.sd.repos.dto;

import lombok.Getter;

/**
 * Data Transfer Object (DTO) representing the owner of a GitHub repository.
 */
@Getter
public class OwnerDto {
    /**
     * The login of the owner.
     */
    private String login;
}
