package org.example.taskspring.Client;

import org.example.taskspring.DTO.GithubBranchResponse;
import org.example.taskspring.DTO.GithubRepoDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Component
public class GithubClient {

    private final RestTemplate restTemplate;
    private final String baseUrl;

    public GithubClient(RestTemplate restTemplate, @Value("${github.api.base-url}") String baseUrl) {
        this.restTemplate = restTemplate;
        this.baseUrl = baseUrl;
    }

    public List<GithubRepoDto> getRepos(String username) {

        String url = baseUrl + "/users/" + username + "/repos";

        ResponseEntity<GithubRepoDto[]> response = restTemplate.getForEntity(url, GithubRepoDto[].class);

        return Optional.ofNullable(response.getBody())
                .map(Arrays::asList)
                .orElse(Collections.emptyList());
    }

    public List<GithubBranchResponse> getBranches(String username, String repoName) {

        String url = baseUrl + "/repos/" + username + "/" + repoName + "/branches";

        ResponseEntity<GithubBranchResponse[]> response = restTemplate.getForEntity(url, GithubBranchResponse[].class);

        return Optional.ofNullable(response.getBody())
                .map(Arrays::asList)
                .orElse(Collections.emptyList());
    }
}
