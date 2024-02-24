package pl.sd.repos.dto;

import lombok.Getter;

/**
 * Data Transfer Object (DTO) representing the owner of a GitHub repository.
 */
public record OwnerDto(String login){}
