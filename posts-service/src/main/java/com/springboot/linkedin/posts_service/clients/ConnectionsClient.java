package com.springboot.linkedin.posts_service.clients;

import com.springboot.linkedin.posts_service.dto.PersonDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "connections-service", path = "/connections")
public interface ConnectionsClient {

    @GetMapping("/core/{userId}/first-degree")
    List<PersonDTO> getFirstConnections(@PathVariable Long userId);

}
