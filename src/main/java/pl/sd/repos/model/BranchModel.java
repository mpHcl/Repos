package pl.sd.repos.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BranchModel {
    public String name;
    public String sha;
}
