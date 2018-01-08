package com.example.controller;


import com.example.Domain.Menu_card;
import com.example.Domain.Menucardelasticsearch;
import com.example.Service.MenuService;
import com.example.repository.MenuRepositoryelastic;
import com.example.repository.MenuRespository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MenuController {


    @Autowired
    private MenuService menuService;

    @Autowired
    private MenuRespository menuRespository;
    @Autowired
    private MenuRepositoryelastic menuRepositoryelastic;

    ModelMapper modelMapper = new ModelMapper();

    @RequestMapping("/items")
    public List<Menucardelasticsearch> getAllItems(){

        return menuService.getAllItems();
    }

   // @RequestMapping(method = RequestMethod.POST,path="/items")
   // public void addItemToMenu(@RequestBody Menu_card menu_card){
    //    menuService.addItemToMenu(menu_card);
    //}


   @RequestMapping(method = RequestMethod.POST,path="/items")
    public void addItemToMenu(@RequestBody Menu_card menu_card){
        menuRespository.save(menu_card);
        Menucardelasticsearch menucardelasticsearch=modelMapper.map(menu_card,Menucardelasticsearch.class);
        menuRepositoryelastic.save(menucardelasticsearch);
    }




    @RequestMapping(value = "/items/{id}", method = RequestMethod.DELETE)
    void delete(@PathVariable("id") Integer id) {

        menuService.delete(id);
    }
    @RequestMapping(value = "/items/{id}",method = RequestMethod.PUT)
    void update(@RequestBody Menu_card menu_card){
        menuService.update(menu_card);
    }

   @RequestMapping(value = "/items/{id}",method = RequestMethod.GET)
    public Menucardelasticsearch findById(@PathVariable("id") int id){

        return menuService.findById(id);
    }

    @RequestMapping(method = RequestMethod.GET,value="/items/{min}/{max}")
    public List<Menucardelasticsearch> findBetween(@PathVariable int min,@PathVariable int max) {
        //List<Menucardelasticsearch> arrayList=new ArrayList<Menucardelasticsearch>();
        //arrayList.addAll(menuRepositoryelastic.findMenucardelasticsearchByPrice(min,max));
        //menuRepositoryelastic.findByPriceBetween(min,max).forEach(arrayList::add);
        return menuService.findBetween(min,max);
    }

}












