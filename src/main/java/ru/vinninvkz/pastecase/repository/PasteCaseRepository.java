package ru.vinninvkz.pastecase.repository;

import java.util.List;

public interface PasteCaseRepository {
    PasteCaseEntity getByHash(String hash);
    List<PasteCaseEntity> getListOfPublicAndAlive(int amount);
    void add(PasteCaseEntity pasteCaseEntity);
}
