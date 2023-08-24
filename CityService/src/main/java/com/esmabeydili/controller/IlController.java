package com.esmabeydili.controller;

import com.esmabeydili.exception.IlAlreadyExistException;
import com.esmabeydili.exception.IlNotFoundException;
import com.esmabeydili.model.Il;
import com.esmabeydili.service.IlService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/sehirler")
@AllArgsConstructor
public class IlController {

    private final IlService ilService;//allaragsConstructor durumu bu sorunu çözüyor

    @GetMapping()
    public ResponseEntity<List<Il>> getIller(@RequestParam(required = false) String name){

        return new ResponseEntity<>(ilService.getIller(name), OK);//kodu küçültmek için static import kullandık

    }

    @GetMapping("/{id}")
    public ResponseEntity<Il> getIl(@PathVariable("id") String ilId){

        return new ResponseEntity<>(getIlById(ilId) , OK);
    }

    @PostMapping
    public ResponseEntity<Il> createIl(@RequestBody Il newIl){

        return new ResponseEntity<>(ilService.createIl(newIl), CREATED);

    }

    @PutMapping("/{id}")// put için post+ get diyebiliriz
    public ResponseEntity<Void>  updateIl(@PathVariable String id,@RequestBody Il newIl){
      Il oldIl = getIlById(id);

      ilService.updateIl(id,newIl);

      return new ResponseEntity<>(OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletedIl(@PathVariable String id){

        ilService.deletedIl(id);
        return new ResponseEntity<>(OK);
    }

    private  Il getIlById(String ilId){
        return ilService.getIlById(ilId);
    }

    @ExceptionHandler(IlNotFoundException.class)
    public ResponseEntity<String> handleIlNotFoundException(IlNotFoundException ex){
       return  new ResponseEntity<>(ex.getMessage(), NOT_FOUND);
    }
    @ExceptionHandler(IlAlreadyExistException.class)
    public ResponseEntity<String> handleIlAlreadyExistException(IlAlreadyExistException ex){
        return  new ResponseEntity<>(ex.getMessage(), CONFLICT);
    }


}
