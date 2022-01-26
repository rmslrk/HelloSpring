package controller;

import domain.ListDTO;
import domain.ListDTO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import response.BaseResponse;
import service.ListService;
import service.ListServiceImpl;
import service.ListService;

@RequestMapping(value = "/list")
@Controller
public class ListController {

    @Autowired
    private ListService listService;

    @ApiOperation(value = "리스트 생성", notes = "리스트를 추가합니다.")
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity createList(@Validated(ListDTO.class) @RequestBody ListDTO list) throws Exception{
        listService.createList(list);
        return new ResponseEntity( new BaseResponse("리스트가 정상적으로 추가되었습니다.", HttpStatus.OK), HttpStatus.OK);
    }

    @ApiOperation( value = "리스트 읽기", notes = "리스트를 읽어옵니다.")
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<ListDTO> getList(@RequestParam Long id) throws Exception{
        return new ResponseEntity<ListDTO>(listService.getList(id), HttpStatus.OK);
    }

    @ApiOperation( value = "리스트 수정", notes = "리스트를 수정합니다. {}")
    @RequestMapping(value = "", method = RequestMethod.PATCH)
    public ResponseEntity updateList(@Validated(ListDTO.class) @RequestBody ListDTO list) throws Exception{
        listService.updateList(list);
        return new ResponseEntity( new BaseResponse("리스트가 정상적으로 수정됐습니다.", HttpStatus.OK), HttpStatus.OK);
    }

    @ApiOperation( value = "리스트 이동", notes = "리스트를 이동합니다.")
    @RequestMapping(value = "/position", method = RequestMethod.PATCH)
    public ResponseEntity moveList(@Validated(ListDTO.class) @RequestBody ListDTO list) throws Exception{
        listService.moveList(list);
        return new ResponseEntity( new BaseResponse("리스트가 정상적으로 이동했습니다.", HttpStatus.OK), HttpStatus.OK);
    }

    @ApiOperation( value = "리스트 삭제", notes = "리스트를 삭제합니다.")
    @RequestMapping(value = "", method = RequestMethod.DELETE)
    public ResponseEntity deleteMemo(@RequestBody ListDTO list) throws Exception{
        listService.deleteList(list);
        return new ResponseEntity( new BaseResponse("리스트가 정상적으로 삭제됐습니다.", HttpStatus.OK), HttpStatus.OK);
    }

}
