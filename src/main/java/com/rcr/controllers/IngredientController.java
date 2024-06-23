package com.rcr.controllers;

import com.rcr.model.IngredientsCategory;
import com.rcr.model.IngredientsItem;
import com.rcr.request.IngredientCategoryRequest;
import com.rcr.request.IngredientRequest;
import com.rcr.services.IngredientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/ingredients")
public class IngredientController {
    @Autowired
    private IngredientsService ingredientsService;
    @PostMapping("category")
    public ResponseEntity<IngredientsCategory> createIngredientCategory(@RequestBody IngredientCategoryRequest req)throws Exception{
        IngredientsCategory category = ingredientsService.createIngredientCategory(req.getName(), req.getRestaurantId());
        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }

    @PostMapping()
    public ResponseEntity<IngredientsItem> createIngredientItem(@RequestBody IngredientRequest req)throws Exception{
        IngredientsItem item = ingredientsService.createIngredientItem(req.getRestaurantId(), req.getName(), req.getCategoryId());

        return new ResponseEntity<>(item, HttpStatus.CREATED);
    }

    @PutMapping("/{id}/stock")
    public ResponseEntity<IngredientsItem> updateIngredientItemStock(@PathVariable Long id)throws Exception{
        IngredientsItem item = ingredientsService.updateStock(id);

        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @GetMapping("/restaurant/{id}")
    public ResponseEntity<List<IngredientsItem>> getRestaurantIngredientItem(@PathVariable Long id)throws Exception{
        List<IngredientsItem> items = ingredientsService.findRestaurantIngredientItem(id);

        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @GetMapping("/restaurant/{id}/category")
    public ResponseEntity<List<IngredientsCategory>> getRestaurantIngredientCategory(@PathVariable Long id)throws Exception{
        List<IngredientsCategory> categories = ingredientsService.findIngredientCategoryByRestaurantId(id);

        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

}
