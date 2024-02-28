package ru.vinninvkz.pastecase.api.request;

import lombok.Data;

@Data
public class PasteCaseRequest {
    private String data;
    private long expirationTimeSeconds;
    private PublicStatus publicStatus;
}
