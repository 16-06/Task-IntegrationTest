package org.example.taskspring.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record GithubBranchResponse(String name, CommitDto commit) {}
