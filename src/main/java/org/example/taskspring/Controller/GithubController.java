package org.example.taskspring.Controller;

import lombok.RequiredArgsConstructor;
import org.example.taskspring.DTO.GithubRepoDto;
import org.example.taskspring.Service.GithubService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/github")
@RequiredArgsConstructor
public class GithubController {

    private final GithubService githubService;

    @GetMapping("/{username}/repos")
    public List<GithubRepoDto> getRepos(@PathVariable String username) {
        return githubService.getRepos(username);
    }
}
