package org.example.taskspring.Client;

import lombok.RequiredArgsConstructor;
import org.example.taskspring.DTO.GithubBranchResponse;
import org.example.taskspring.DTO.GithubRepoDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.*;

@Component
@RequiredArgsConstructor
public class GithubClient {

    private final RestClient restClient;

    @Value("${github.api.base-url}")
    private String baseUrl;


    public List<GithubRepoDto> getRepos(String username) {

        return List.of(restClient
                .get()
                .uri(baseUrl + "/users/{username}/repos", username)
                .retrieve()
                .body(GithubRepoDto[].class));
    }

    public List<GithubBranchResponse> getBranches(String username, String repoName) {

        return List.of(restClient
                .get()
                .uri(baseUrl + "/repos/{username}/{repo}/branches", username, repoName)
                .retrieve()
                .body(GithubBranchResponse[].class));
    }
}
