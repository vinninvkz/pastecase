package ru.vinninvkz.pastecase.service;

import ru.vinninvkz.pastecase.api.request.PasteCaseRequest;
import ru.vinninvkz.pastecase.api.respones.PasteCaseResponse;
import ru.vinninvkz.pastecase.api.respones.PasteCaseUrlResponse;

import java.util.List;

public interface PasteCaseService {
    PasteCaseResponse getByHash(String hash);
    List<PasteCaseResponse> getPublicPasteCases();
    PasteCaseUrlResponse create(PasteCaseRequest request);
}
