package ru.vinninvkz.pastecase.repository;

import lombok.Data;
import ru.vinninvkz.pastecase.api.request.PublicStatus;

import java.time.LocalDateTime;
@Data
public class PasteCaseEntity {
    private int id;
    private String data;
    private String hash;
    private LocalDateTime lifeTime;
    private boolean isPublic;

}
