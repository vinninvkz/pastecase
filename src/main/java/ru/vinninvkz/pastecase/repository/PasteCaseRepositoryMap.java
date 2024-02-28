package ru.vinninvkz.pastecase.repository;

import org.springframework.stereotype.Repository;
import ru.vinninvkz.pastecase.exception.NotFoundEntityException;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class PasteCaseRepositoryMap implements PasteCaseRepository {
    private final Map<String, PasteCaseEntity> vault = new HashMap<>();

    @Override
    public PasteCaseEntity getByHash(String hash) {
        PasteCaseEntity pasteCaseEntity = vault.get(hash);
        if (pasteCaseEntity == null) {
            throw new NotFoundEntityException("PasteCase not found with hash + " + hash);
        }
        return pasteCaseEntity;
    }

    @Override
    public List<PasteCaseEntity> getListOfPublicAndAlive(int amount) {
        LocalDateTime now = LocalDateTime.now();
        return vault.values().stream()
                .filter(PasteCaseEntity::isPublic)
                .filter(pasteCaseEntity -> pasteCaseEntity.getLifeTime().isAfter(now))
                .sorted(Comparator.comparing(PasteCaseEntity::getId).reversed())
                .limit(amount)
                .collect(Collectors.toList());
    }

    @Override
    public void add(PasteCaseEntity pasteCaseEntity) {
        vault.put(pasteCaseEntity.getHash(), pasteCaseEntity);
    }
}
