package pl.sd.repos.dto;

import lombok.Getter;

@Getter
public class RepoDto {
    private String name;
    private OwnerDto owner;
    private boolean fork;
}
