package com.GestionDeStock.Controller;

import com.GestionDeStock.DTO.ArticleDTO;
import com.GestionDeStock.Entity.Article;
import com.GestionDeStock.Services.ArticleService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
@AllArgsConstructor
@CrossOrigin("*")
@RequestMapping("/api/v1")
@RestController
public class ArticleController {


    @Autowired
    ArticleService articleService;

    //  http://localhost:8081/api/v1/savearticle/{iddepot}/{idcategory}

    @RequestMapping (value = "/savearticle/{iddepot}/{idcategory}" , method = RequestMethod.POST,consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<ArticleDTO> savearticle(@RequestPart("article") String article , @PathVariable("iddepot") int iddepot, @PathVariable ("idcategory") int idcategory ,@RequestPart("picture") MultipartFile picture)throws IOException{
        return ResponseEntity.ok(articleService.savearticle( article , iddepot ,idcategory , picture));
    }

    // http://localhost:8081/api/v1/saveearticle/

  //  @RequestMapping (value = "/saveearticle" , method = RequestMethod.POST,consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE})
  //  public ResponseEntity<ArticleDTO> savearticle(@RequestPart("article") String article  ,@RequestPart("picturee") MultipartFile picturee)throws IOException{
   //     return ResponseEntity.ok(articleService.saveearticle(article , picturee));
  //  }

    //  http://localhost:8081/api/v1/updatearticle/{articleId}

    @RequestMapping(value = "/updatearticle/{articleId}", method = RequestMethod.PUT, consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<ArticleDTO> updateArticle(@PathVariable("articleId") int articleId, @RequestPart("article") String article, @RequestPart("picture") MultipartFile picture) throws IOException {
        ArticleDTO updatedArticle = articleService.updatearticle(articleId, article, picture);
        return ResponseEntity.ok(updatedArticle);
    }


    //  http://localhost:8081/api/v1/getAllarticle

    @GetMapping("getAllarticle")
    public List<ArticleDTO> getAllarticle(){
        return articleService.getAllArticle();
    }



    //  http://localhost:8081/api/v1/deletearticle/{idarticle}
    @DeleteMapping("deletearticle/{idarticle}")
    public void deletearticle(@PathVariable ("idarticle") int idarticle){
        articleService.deletearticle(idarticle);
    }




    //  http://localhost:8081/api/v1/getarticlebyid/{idarticle}
    @GetMapping("/getarticlebyid/{idarticle}")
    public ResponseEntity<ArticleDTO> getarticlebyid(@PathVariable("idarticle") int idarticle){
        return ResponseEntity.ok(articleService.getarticlebyid(idarticle));
    }

    //  http://localhost:8081/api/v1/getallarticlebyiddepot/{iddepot}
    @GetMapping("getallarticlebyiddepot/{iddepot}")
    public ResponseEntity<List<ArticleDTO>> getaalarticlebyiddepot(@PathVariable int iddepot){
        return ResponseEntity.ok(articleService.getallarticlebyiddepot(iddepot));
    }


}
