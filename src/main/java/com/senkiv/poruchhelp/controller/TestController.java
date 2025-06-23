package com.senkiv.poruchhelp.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Test Controller", description = "Will be removed after development is finished.")
public class TestController {
    @GetMapping
    public String greeting() {
        return "Authentication working good!";
    }
}
