package service;

import domain.CardDTO;
import response.BaseResponse;

public interface CardService {

    void createCard(CardDTO card, Long lid) throws Exception;
    CardDTO getCard(Long cid) throws Exception;
    void deleteCard(CardDTO card) throws Exception;
    void updateCard(CardDTO card) throws Exception;
    void successCard(CardDTO card) throws Exception;
    void moveCard(CardDTO card) throws Exception;
}
