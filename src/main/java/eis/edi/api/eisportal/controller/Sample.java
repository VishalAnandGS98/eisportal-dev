package eis.edi.api.eisportal.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("api")
public class Sample {

    @GetMapping("sample")
    public String get()
    {
        return "Hello WORLD";
    }
}
