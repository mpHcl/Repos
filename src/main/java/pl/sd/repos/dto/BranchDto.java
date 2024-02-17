package pl.sd.repos.dto;

import lombok.Getter;

@Getter
public class BranchDto {
    private String name;
    private BranchCommitDto commit;
}
