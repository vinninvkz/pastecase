package ru.vinninvkz.pastecase.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.vinninvkz.pastecase.api.request.PasteCaseRequest;
import ru.vinninvkz.pastecase.api.respones.PasteCaseResponse;
import ru.vinninvkz.pastecase.api.respones.PasteCaseUrlResponse;
import ru.vinninvkz.pastecase.service.PasteCaseService;

import java.util.Collection;

@RestController
@RequiredArgsConstructor
public class PasteCaseController {
    private final PasteCaseService pasteCaseService;

    @GetMapping("/{hash}")
    public PasteCaseResponse getByHash(@PathVariable String hash){
        return pasteCaseService.getByHash(hash);
    }
    @GetMapping("/")
    public Collection<PasteCaseResponse> getPasteList(){
        return pasteCaseService.getPublicPasteCases();
    }
    @PostMapping("/")
    public PasteCaseUrlResponse add(@RequestBody PasteCaseRequest request){
        return pasteCaseService.create(request);
    }
}
