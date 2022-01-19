package repository;

import domain.CardDTO;
import org.springframework.stereotype.Repository;

@Repository
public interface CardMapper {
    void createCard(CardDTO cardDTO, Long lid);

    CardDTO getCard(Long cid);

    void updateCard(CardDTO card);

    void moveCard(CardDTO card);

    void successCard(CardDTO card);

    void deleteCard(CardDTO card);

    Long isExistsCard(Long cid);
}
