package ru.vinninvkz.pastecase.api.respones;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import ru.vinninvkz.pastecase.api.request.PublicStatus;
@Data
@RequiredArgsConstructor
public class PasteCaseResponse {
    private final String data;
    private final boolean isPublic;
}
