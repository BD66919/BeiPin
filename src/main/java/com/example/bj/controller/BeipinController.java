package com.example.bj.controller;

import com.example.bj.entity.BeiPin;
import com.example.bj.entity.Stock;
import com.example.bj.entity.Take;
import com.example.bj.mapper.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 * @author Jiangzhendong
 * @Description 该类用于系统页面跳转和增删查功能实现
 *
 */
@Controller
public class BeipinController {

    private BeiPinMapper beiPinMapper;

    private StockMapper stockMapper;

    private TakeMapper takeMapper;

    private BeiPinRepository beiPinRepository;


    private TakeRepository takeRepository;


    public BeipinController(BeiPinMapper beiPinMapper,
                            StockMapper stockMapper,
                            TakeMapper takeMapper,
                            BeiPinRepository beiPinRepository,
                            TakeRepository takeRepository){
        this.stockMapper = stockMapper;
        this.beiPinMapper = beiPinMapper;
        this.takeMapper = takeMapper;
        this.takeRepository = takeRepository;
        this.beiPinRepository = beiPinRepository;

    }

    @GetMapping("/beipin")
    public String beipin(Model model,
                         @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        PageHelper.startPage(pageNum,20);
        List<Stock> stockList = stockMapper.list();
        PageInfo<Stock> stockPageInfo = new PageInfo<Stock>(stockList);
        model.addAttribute("stockPageInfo",stockPageInfo);
        return "beipin/beipin.html";
    }

    @GetMapping("/beipin/add")
    public String add(Model model){
        List<Stock> nameList = stockMapper.list();
        model.addAttribute("nameList",nameList);
        return "beipin/add.html";}

    @PostMapping("/beipin/addBeipin")
    public String addBeipin(BeiPin beipin,Model model) throws ParseException {
        if (beipin.getDate() == null || beipin.getDate().equals("")){
            String string = new SimpleDateFormat("yyyy-MM-dd").format(new Date()).toString();
            beipin.setDate(string);
        }
        Stock stock = new Stock(beipin.getName(),beipin.getNumber());
        Stock stock1 = stockMapper.get(beipin.getName());
        if(stock1 == null)
            stockMapper.Insert(stock);
        else {
            stock.setNumber(stockMapper.get(beipin.getName()).getNumber() + beipin.getNumber());
            stockMapper.Update(stock);
        }
        beiPinMapper.Insert(beipin);
        model.addAttribute("msg","添加数据成功");
        List<Stock> nameList = stockMapper.list();
        model.addAttribute("nameList",nameList);
        return "beipin/add.html";
    }

    @GetMapping("/beipin/delete")
    public String delete(Model model){
        List<Stock> nameList = stockMapper.list();
        model.addAttribute("nameList",nameList);
        return "beipin/delete.html";}

    @PostMapping("/beipin/deleteBeipin")
    public String deleteBeipin(Take take, Model model) throws ParseException {
        if (take.getDate() == null || take.getDate().equals("")){
            String string = new SimpleDateFormat("yyyy-MM-dd").format(new Date()).toString();
            take.setDate(string);
        }
        Stock stock = new Stock(take.getName(),take.getNumber());
        Stock stock1 = stockMapper.get(take.getName());
        if(stock1 == null) {
            stock.setNumber(-stock.getNumber());
            stockMapper.Insert(stock);
        }
        else {
            stock.setNumber(stockMapper.get(take.getName()).getNumber() - take.getNumber());
            stockMapper.Update(stock);
        }
        takeMapper.Insert(take);
        model.addAttribute("msg","备品出库成功");
        List<Stock> nameList = stockMapper.list();
        model.addAttribute("nameList",nameList);
        return "beipin/delete.html";
    }

    @GetMapping("/beipin/addQuery")
    public String addQuery(Model model){
        List<Stock> nameList = stockMapper.list();
        model.addAttribute("nameList",nameList);
        return "beipin/addQuery.html";}

    @GetMapping("/beipin/addQueryResult")
    public String addQueryResult(){ return "beipin/addQueryResult.html";}

    @PostMapping("/beipin/BeiPinQuery")
    public String BeiPinQuery(@RequestParam(value = "name",required = false) String name,
                              @RequestParam(value = "number",required = false) String number,
                              @RequestParam(value = "type",required = false) String type,
                              @RequestParam(value = "brand",required = false) String brand,
                              @RequestParam(value = "serialnumber",required = false) String serialnumber,
                              @RequestParam(value = "room",required = false) String room,
                              @RequestParam(value = "supply",required =  false) String supply,
                              @RequestParam(value = "date",required = false) String date, Model model){
        List<BeiPin> BeiPinList = beiPinRepository.select(name, number, type,brand,serialnumber,room, supply, date);
        PageInfo<BeiPin> BeiPinListInfo = new PageInfo<BeiPin>(BeiPinList);
        model.addAttribute("BeiPinListInfo",BeiPinListInfo);
        return "beipin/addQueryResult";
    }

    @PostMapping("/beipin/BeiPinDelete={id}")
    public String deleteBeiPin(@PathVariable("id") int id, Model model){
        BeiPin beipin = beiPinMapper.Select(id);
        String name = beipin.getName();
        int number = beipin.getNumber();
        if(stockMapper.get(name)!=null){
            Stock stock = stockMapper.get(name);
            stock.setNumber(stock.getNumber()-number);
            stockMapper.Update(stock);
            beiPinMapper.Delete(id);
            model.addAttribute("msg","删除成功");
            List<Stock> nameList = stockMapper.list();
            model.addAttribute("nameList",nameList);
            return "beipin/addQuery";
        }
        else{
            beiPinMapper.Delete(id);
            model.addAttribute("msg","删除成功");
            List<Stock> nameList = stockMapper.list();
            model.addAttribute("nameList",nameList);
            return "beipin/addQuery";
        }
    }

    @PostMapping("/beipin/takeDelete={id}")
    public String takeDelete(@PathVariable("id") int id,Model model){
        Take take = takeMapper.Select(id);
        String name = take.getName();
        int number =take.getNumber();
        if(stockMapper.get(name)!=null) {
            Stock stock = stockMapper.get(name);
            stock.setNumber(stock.getNumber() + number);
            stockMapper.Update(stock);
            takeMapper.Delete(id);
            model.addAttribute("msg", "删除成功");
            List<Stock> nameList = stockMapper.list();
            model.addAttribute("nameList", nameList);
            return "beipin/deleteQuery";
        }
        else{
            takeMapper.Delete(id);
            model.addAttribute("msg", "删除成功");
            List<Stock> nameList = stockMapper.list();
            model.addAttribute("nameList", nameList);
            return "beipin/deleteQuery";
        }
    }

    @GetMapping("/beipin/deleteQuery")
    public String deleteQuery(Model model){
        List<Stock> nameList = stockMapper.list();
        model.addAttribute("nameList",nameList);
        return "beipin/deleteQuery.html";}

    @GetMapping("/beipin/deleteQueryResult")
    public String deleteQueryResult(){ return "beipin/deleteQueryResult.html";}

    @PostMapping("/beipin/takeQuery")
    public String queryTake(@RequestParam(value = "name",required = false) String name,
                            @RequestParam(value = "number",required = false) String number,
                            @RequestParam(value = "peopleName",required = false) String peopleName,
                            @RequestParam(value = "type",required = false) String type,
                            @RequestParam(value = "brand",required = false) String brand,
                            @RequestParam(value = "serialnumber",required = false) String serialnumber,
                            @RequestParam(value = "room",required = false) String room,
                            @RequestParam(value = "date",required = false) String date, Model model){
        List<Take> takeList = takeRepository.select(name, number, peopleName,type,brand,serialnumber,room, date);
        PageInfo<Take> TakeListInfo = new PageInfo<Take>(takeList);
        model.addAttribute("TakeListInfo",TakeListInfo);
        return "beipin/deleteQueryResult";
    }

    @GetMapping("/beipin/query")
    public String queryBeiPin(Model model){
        List<Stock> nameList = stockMapper.list();
        model.addAttribute("nameList",nameList);
        return "beipin/query.html";}

    @GetMapping("/beipin/queryResult")
    public String queryResult(){ return "beipin/queryResult.html";}

    @PostMapping("/beipin/queryStock")
    public String queryStock(@RequestParam(value = "name",required = false) String name,
                             Model model){
        Stock stock = stockMapper.get(name);
        model.addAttribute("name",stock.getName());
        model.addAttribute("number",stock.getNumber());
        return "beipin/queryResult.html";
    }

    @GetMapping("/beipin/addStock")
    public String addStock(Model model){
        List<Stock> nameList = stockMapper.list();
        model.addAttribute("nameList",nameList);
        return "beipin/addStock.html";
    }

    @PostMapping("/beipin/stockAdd")
    public String stockAdd(String name,String action,Model model) throws ParseException {
        if(action.equals("添加")) {
            if ((stockMapper.get(name) != null)) {
                model.addAttribute("msg", "备品类型已存在");
                List<Stock> nameList = stockMapper.list();
                model.addAttribute("nameList", nameList);
                return "beipin/addStock.html";
            }
            Stock stock = new Stock(name, 0);
            stockMapper.Insert(stock);
            model.addAttribute("msg", "添加数据成功");
            List<Stock> nameList = stockMapper.list();
            model.addAttribute("nameList", nameList);
            return "beipin/addStock.html";
        }
        else{
            if ((stockMapper.get(name) == null)) {
                model.addAttribute("msg", "备品类型不存在");
                List<Stock> nameList = stockMapper.list();
                model.addAttribute("nameList", nameList);
                return "beipin/addStock.html";
            }
            Stock stock = stockMapper.get(name);
            if(stock.getNumber()!=0){
                model.addAttribute("msg", "该备品类型库存不为0，无法删除");
                List<Stock> nameList = stockMapper.list();
                model.addAttribute("nameList", nameList);
                return "beipin/addStock.html";
            }
            else {
                stockMapper.Delete(name);
                model.addAttribute("msg", "删除数据成功");
                List<Stock> nameList = stockMapper.list();
                model.addAttribute("nameList", nameList);
                return "beipin/addStock.html";
            }
        }
    }
}
