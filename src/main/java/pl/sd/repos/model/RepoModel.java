package pl.sd.repos.model;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class RepoModel {
    private String name;
    private String ownerLogin;
    private List<BranchModel> branches;
}
