package org.example.taskspring.Service;

import lombok.RequiredArgsConstructor;
import org.example.taskspring.Client.GithubClient;
import org.example.taskspring.DTO.BranchDto;
import org.example.taskspring.DTO.GithubRepoDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GithubService {

    private final GithubClient githubClient;

    public List<GithubRepoDto> getRepos(String username) {

        List<GithubRepoDto> repos = githubClient.getRepos(username);

        return repos.stream()
                .filter(repo -> !repo.fork())
                .map(repo -> {
                    List<BranchDto> branches = githubClient.getBranches(username, repo.name())
                            .stream()
                            .map(branch -> new BranchDto(branch.name(), branch.commit().sha()))
                            .toList();
                            return new GithubRepoDto(repo.name(), repo.owner().login(), branches);
                })
                .toList();

    }

}
