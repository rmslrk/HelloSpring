package repository;

import domain.CardDTO;
import org.springframework.stereotype.Repository;

@Repository
public interface CardMapper {
    void createCard(CardDTO cardDTO);

    CardDTO getCard(Long cid);

    void updateCard(CardDTO cardDTO);

    void deleteCard(CardDTO cardDTO);

    Long isExistsCard(Long cid);
}
