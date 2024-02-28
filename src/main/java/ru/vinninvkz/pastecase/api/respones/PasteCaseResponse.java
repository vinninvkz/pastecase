package ru.vinninvkz.pastecase.api.respones;

import lombok.Data;
import ru.vinninvkz.pastecase.api.request.PublicStatus;
@Data
public class PasteCaseResponse {
    private String data;
    private PublicStatus status;
}
