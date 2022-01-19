package service;

import domain.CardDTO;
import domain.UserDTO;
import exception.BaseException;
import exception.ErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.CardMapper;


@Service
public class CardServiceImpl implements CardService {

    @Autowired
    private UserService userService;

    @Autowired
    private CardMapper cardMapper;

    @Override
    public void createCard(CardDTO card, Long lid) throws Exception {

        UserDTO user = userService.getLoginUser();
        //유저 정보가 없는 경우 예외 처리
        if (user==null)
            throw new BaseException(ErrorMessage.INVALID_USER_EXCEPTION);

        cardMapper.createCard(card, lid);
    }


    @Override
    public CardDTO getCard(Long cid) throws Exception {
        CardDTO card = new CardDTO();

        UserDTO user = userService.getLoginUser();
        //유저 정보가 없는 경우 예외 처리
        if (user==null)
            throw new BaseException(ErrorMessage.INVALID_USER_EXCEPTION);

        // 해당 cid의 카드가 존재하지 않는다면 임의로 값을 넣어 출력
        if(cardMapper.isExistsCard(cid)==null) {
            card.setTitle("");
            return card;
        }

        card = cardMapper.getCard(cid);

        return card;
    }

    @Override
    public void updateCard(CardDTO card) throws Exception {

        UserDTO user = userService.getLoginUser();
        //유저 정보가 없는 경우 예외 처리
        if (user==null)
            throw new BaseException(ErrorMessage.INVALID_USER_EXCEPTION);

        if(cardMapper.isExistsCard(card.getCid())==null)
            throw new BaseException(ErrorMessage.CONTENT_NOT_EXISTS);
        cardMapper.updateCard(card);
    }

    @Override
    public void moveCard(CardDTO card) throws Exception {

        UserDTO user = userService.getLoginUser();
        //유저 정보가 없는 경우 예외 처리
        if (user==null)
            throw new BaseException(ErrorMessage.INVALID_USER_EXCEPTION);

        if(cardMapper.isExistsCard(card.getCid())==null)
            throw new BaseException(ErrorMessage.CONTENT_NOT_EXISTS);

        cardMapper.moveCard(card);
    }

    @Override
    public void successCard(CardDTO card) throws Exception {

        UserDTO user = userService.getLoginUser();
        //유저 정보가 없는 경우 예외 처리
        if (user==null)
            throw new BaseException(ErrorMessage.INVALID_USER_EXCEPTION);

        if(cardMapper.isExistsCard(card.getCid())==null)
            throw new BaseException(ErrorMessage.CONTENT_NOT_EXISTS);

        cardMapper.successCard(card);
    }

    @Override
    public void deleteCard(CardDTO card) throws Exception {

        UserDTO user = userService.getLoginUser();
        //유저 정보가 없는 경우 예외 처리
        if (user==null)
            throw new BaseException(ErrorMessage.INVALID_USER_EXCEPTION);

        if(cardMapper.isExistsCard(card.getCid())==null)
            throw new BaseException(ErrorMessage.CONTENT_NOT_EXISTS);

        cardMapper.deleteCard(card);
    }
}
