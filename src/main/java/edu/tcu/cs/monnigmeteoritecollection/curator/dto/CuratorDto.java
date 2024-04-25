package edu.tcu.cs.monnigmeteoritecollection.curator.dto;

import jakarta.validation.constraints.NotEmpty;

//no password field so it doesnt get sent to client when they call findall
public record CuratorDto(
    Integer id,
    @NotEmpty(message = "username is required.")
    String username,
    boolean enabled,
    @NotEmpty(message = "roles are required.")
    String roles
) {
}