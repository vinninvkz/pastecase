package ru.vinninvkz.pastecase.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.bind.ConstructorBinding;
import org.springframework.stereotype.Service;
import ru.vinninvkz.pastecase.api.request.PasteCaseRequest;
import ru.vinninvkz.pastecase.api.request.PublicStatus;
import ru.vinninvkz.pastecase.api.respones.PasteCaseResponse;
import ru.vinninvkz.pastecase.api.respones.PasteCaseUrlResponse;
import ru.vinninvkz.pastecase.repository.PasteCaseEntity;
import ru.vinninvkz.pastecase.repository.PasteCaseRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
//@ConfigurationPropertiesScan
public class PasteCaseServiceImpl implements PasteCaseService{
    private final PasteCaseRepository repository;
    @Value("${app.id_generator}")
    private int idGenerator ;
    @Value("${app.host}")
    private String host;
    @Value("${app.public_list_size}")
    private int publicListSize;

    @Override
    public PasteCaseResponse getByHash(String hash) {
        PasteCaseEntity pasteCaseEntity = repository.getByHash(hash);
        return new PasteCaseResponse(pasteCaseEntity.getData(), pasteCaseEntity.isPublic());
    }

    @Override
    public List<PasteCaseResponse> getPublicPasteCases() {
       List<PasteCaseEntity> list = repository.getListOfPublicAndAlive(publicListSize);
       return list.stream()
               .map(pasteCaseEntity -> new PasteCaseResponse(pasteCaseEntity.getData(), pasteCaseEntity.isPublic()))
               .collect(Collectors.toList());

    }

    @Override
    public PasteCaseUrlResponse create(PasteCaseRequest request) {
        int hash = generateId();
        PasteCaseEntity pasteCaseEntity = new PasteCaseEntity();
        pasteCaseEntity.setLifeTime(LocalDateTime.now().plusSeconds(request.getExpirationTimeSeconds()));
        pasteCaseEntity.setData(request.getData());
        pasteCaseEntity.setId(hash);
        pasteCaseEntity.setHash(Integer.toHexString(hash));
        pasteCaseEntity.setPublic(request.getPublicStatus() == PublicStatus.PUBLIC);
        //map from request
        repository.add(pasteCaseEntity);
        // map to response
        return new PasteCaseUrlResponse(host + "/" + pasteCaseEntity.getHash());
    }

    private int generateId() {
        return idGenerator++;
    }
}
