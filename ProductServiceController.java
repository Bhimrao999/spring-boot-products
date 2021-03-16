package com.example.demo.mycontrollers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
@RestController
@RequestMapping(path="/products")
public class ProductServiceController {
    static Map<String, Product> productRepo= new HashMap<>();
    static{
        Product honey=new Product();
        honey.setId("1");
        honey.setName("Honey");
        productRepo.put(honey.getId(), honey);

        Product alomond=new Product();
        alomond.setId("2");
        alomond.setName("Almond");
        productRepo.put(alomond.getId(), alomond);

    }
    @RequestMapping(value = "/removeProduct/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<Object> delete(@PathVariable("id") String id){
      productRepo.remove(id);
      return new ResponseEntity<>("product deleted successfully", HttpStatus.OK);
    }
    @RequestMapping(value = "/updateProduct/{id}",method = RequestMethod.PUT)
    public  ResponseEntity<Object> updateProduct(@PathVariable("id") String id, @RequestBody Product product){
        productRepo.remove(id);
        product.setId(id);
        productRepo.put(id,product);
        return new ResponseEntity<>("product updated",HttpStatus.OK);

    }

    @PostMapping(path= "/saveProduct", produces = "application/json")
    public ResponseEntity<Object> createProduct(@RequestBody Product product){
        productRepo.put(product.getId(), product);
        return  new ResponseEntity<>("product is created",HttpStatus.OK);
    }
    @RequestMapping(value = "/getProduct")
    public ResponseEntity<Object> getProduct(){
        return new ResponseEntity<>(productRepo.values(),HttpStatus.OK);
    }


}
