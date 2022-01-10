package service;

import domain.CardDTO;
import exception.BaseException;
import exception.ErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.CardMapper;

@Service
public class CardServiceImpl implements CardService {

    @Autowired
    private CardMapper cardMapper;

    @Override
    public void createCard(CardDTO card) throws Exception {
        /*
        User user = userService.getLoginUser();
        //유저 정보가 없는 경우 예외 처리
        if (user==null)
            throw new RequestInputException(ErrorMessage.INVALID_USER_EXCEPTION);
        Long userId = user.getId();
        */
        //if(!userId.equals(memoMapper.getUserIdByTimeTablesId(memo.getTimetable_id())))
        //    throw new RequestInputException(ErrorMessage.INVALID_ACCESS_EXCEPTION);

        cardMapper.createCard(card);
    }


    @Override
    public CardDTO getCard(Long cid) throws Exception {
        CardDTO card = new CardDTO();
        /*
        User user = userService.getLoginUser();
        //유저 정보가 없는 경우 예외 처리
        if (user==null)
            throw new RequestInputException(ErrorMessage.INVALID_USER_EXCEPTION);
        Long userId = user.getId();
        */
        //if(!userId.equals(timetableMapper.getUserIdByTimeTableId(timeTableId)))
        //throw new RequestInputException(ErrorMessage.INVALID_ACCESS_EXCEPTION);

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
        /*
        User user = userService.getLoginUser();
        //유저 정보가 없는 경우 예외 처리
        if (user==null)
            throw new RequestInputException(ErrorMessage.INVALID_USER_EXCEPTION);
        Long userId = user.getId();

        //수정 권한 확인
        if(!userId.equals(memoMapper.getUserIdByTimeTablesId(memo.getTimetable_component_id())))
            throw new RequestInputException(ErrorMessage.INVALID_ACCESS_EXCEPTION);
        */

        if(cardMapper.isExistsCard(card.getCid())==null)
            throw new BaseException(ErrorMessage.CONTENT_NOT_EXISTS);
        cardMapper.updateCard(card);
    }

    @Override
    public void deleteCard(CardDTO card) throws Exception {
        /*
        User user = userService.getLoginUser();
        //유저 정보가 없는 경우 예외 처리
        if (user==null)
            throw new RequestInputException(ErrorMessage.INVALID_USER_EXCEPTION);
        Long userId = user.getId();
        //수정 권한 확인
        if(!userId.equals(memoMapper.getUserIdByTimeTablesId(memo.getTimetable_component_id())))
            throw new RequestInputException(ErrorMessage.INVALID_ACCESS_EXCEPTION);
        if(memoMapper.isExistsMemo(memo.getTimetable_component_id())==null)
            throw new RequestInputException(ErrorMessage.CONTENT_NOT_EXISTS);
        */
        cardMapper.deleteCard(card);
    }
}
