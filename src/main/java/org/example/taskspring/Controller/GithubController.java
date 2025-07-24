package org.example.taskspring.Controller;

import lombok.RequiredArgsConstructor;
import org.example.taskspring.DTO.ErrorResponse;
import org.example.taskspring.DTO.GithubRepoDto;
import org.example.taskspring.Service.GithubService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@RestController
@RequestMapping("/github")
@RequiredArgsConstructor
public class GithubController {

    private final GithubService githubService;



    @GetMapping("/{username}/repos")
    public ResponseEntity<?> getRepos(@PathVariable String username) {
        try {
            List<GithubRepoDto> repos = githubService.getRepos(username);
            return ResponseEntity.ok(repos);
        } catch (HttpClientErrorException.NotFound e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(404, e.getMessage()));
        }
    }
}
