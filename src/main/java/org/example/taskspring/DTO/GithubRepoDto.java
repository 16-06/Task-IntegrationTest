package org.example.taskspring.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record GithubRepoDto(String name, Owner owner,Boolean fork, List<BranchDto> branches) {

    public GithubRepoDto(String name, String ownerLogin, List<BranchDto> branches) {
        this(name, new Owner(ownerLogin), false, branches);
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Owner(String login) {}
}
