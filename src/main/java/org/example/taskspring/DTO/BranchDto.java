package org.example.taskspring.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class BranchDto {

    private String name;
    private String sha;

    public BranchDto(String name, String sha) {
        this.name = name;
        this.sha = sha;
    }
}
