package org.example.taskspring.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class GithubRepoDto {

    private String name;
    private Owner owner;
    private boolean fork;
    private List<BranchDto> branches;

    public GithubRepoDto(String name, String ownerLogin, List<BranchDto> branches) {
        this.name = name;
        this.owner = new Owner(ownerLogin);
        this.branches = branches;
    }

    @Getter
    @Setter
    public static class Owner {

        private String login;

        public Owner(String login) {
            this.login = login;
        }

    }
}
