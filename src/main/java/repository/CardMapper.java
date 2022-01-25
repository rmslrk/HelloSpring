package repository;

import domain.CardCriteria;
import domain.CardDTO;
import domain.UserDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface CardMapper {
    void createCard(CardDTO cardDTO, Long lid);

    CardDTO getCard(Long id);

    List<CardDTO> getSearchCardList(@Param("cardCriteria") CardCriteria cardCriteria, @Param("user") UserDTO user);

    List<CardDTO> getCountCardList(@Param("cardCriteria") CardCriteria cardCriteria, @Param("user") UserDTO user);

    List<CardDTO> getExpireCardList(@Param("cardCriteria") CardCriteria cardCriteria, @Param("user") UserDTO user);

    void updateCard(CardDTO card);

    void moveCard(CardDTO card);

    void successCard(CardDTO card);

    void deleteCard(CardDTO card);

    Long isExistsCard(Long cid);
}
