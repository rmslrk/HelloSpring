package service;

import domain.CardCriteria;
import domain.CardDTO;
import response.BaseResponse;

import java.util.Map;

public interface CardService {

    void createCard(CardDTO card, Long lid) throws Exception;
    CardDTO getCard(Long id) throws Exception;
    Map<String, Object> getSearchCardList(CardCriteria cardCriteria) throws Exception;
    Map<String, Object> getExpireCardList(CardCriteria cardCriteria) throws Exception;
    void deleteCard(CardDTO card) throws Exception;
    void updateCard(CardDTO card) throws Exception;
    void successCard(CardDTO card) throws Exception;
    void moveCard(CardDTO card) throws Exception;

}
