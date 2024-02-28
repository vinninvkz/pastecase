package ru.vinninvkz.pastecase.api.respones;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class PasteCaseUrlResponse {
    private final String url;
}
