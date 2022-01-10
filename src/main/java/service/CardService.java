package service;

import domain.CardDTO;
import response.BaseResponse;

public interface CardService {

    void createCard(CardDTO cardDTO) throws Exception;
    CardDTO getCard(Long cid) throws Exception;
    void deleteCard(CardDTO cardDTO) throws Exception;
    void updateCard(CardDTO cardDTO) throws Exception;
}
