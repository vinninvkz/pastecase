package ru.vinninvkz.pastecase.controller;

import org.springframework.web.bind.annotation.*;
import ru.vinninvkz.pastecase.api.request.PasteCaseRequest;

import java.util.Collection;
import java.util.Collections;

@RestController
public class PasteCaseController {
    @GetMapping("/{hash}")
    public String getByHash(@PathVariable String hash){
        return hash;
    }
    @GetMapping("/")
    public Collection<String> getPasteList(){
        return Collections.emptyList();
    }
    @PostMapping("/")
    public String add(@RequestBody PasteCaseRequest request){
        return request.getData();
    }
}
