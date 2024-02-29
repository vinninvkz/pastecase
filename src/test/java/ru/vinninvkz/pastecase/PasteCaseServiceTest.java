package ru.vinninvkz.pastecase;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.vinninvkz.pastecase.api.respones.PasteCaseResponse;
import ru.vinninvkz.pastecase.exception.NotFoundEntityException;
import ru.vinninvkz.pastecase.repository.PasteCaseEntity;
import ru.vinninvkz.pastecase.repository.PasteCaseRepository;
import ru.vinninvkz.pastecase.service.PasteCaseService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
public class PasteCaseServiceTest {
    @Autowired
    private PasteCaseService pasteCaseService;

    @MockBean
    private PasteCaseRepository pasteCaseRepository;
    @Test
    public void notExistHash(){
        when(pasteCaseRepository.getByHash(anyString())).thenThrow(NotFoundEntityException.class);
        assertThrows(NotFoundEntityException.class, () -> pasteCaseService.getByHash("hi"));
    }

    @Test
    public void getExistHash(){
        PasteCaseEntity entity = new PasteCaseEntity();
        entity.setHash("1");
        entity.setData("hi");
        entity.setPublic(true);

        when(pasteCaseRepository.getByHash("1")).thenReturn(entity);
        PasteCaseResponse expected = new PasteCaseResponse("hi",true);
        PasteCaseResponse actual = pasteCaseService.getByHash("1");
        assertEquals(expected,actual);

    }
}
