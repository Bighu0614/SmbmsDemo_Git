package com.bdqn.controller;

import com.alibaba.fastjson.JSON;
import com.bdqn.entity.Bill;
import com.bdqn.entity.Provider;
import com.bdqn.entity.User;
import com.bdqn.services.BillDaoservice;
import com.bdqn.services.ProviderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
@Controller
@RequestMapping("smbms")
public class BillController {
    @Resource
    BillDaoservice bds;
    @Resource
    ProviderService ps;
    @ResponseBody
    @RequestMapping(value = "load")
    public String load(HttpServletRequest request,HttpServletResponse response, String queryProductName, String queryProviderId, String queryIsPayment){
        Integer queryProviderId1 = queryProviderId!=null?Integer.parseInt(queryProviderId):0;
        Integer queryIsPayment1 =queryIsPayment!=null?Integer.parseInt(queryIsPayment):0;
        Bill bill = new Bill(queryProductName,queryIsPayment1,queryProviderId1);
        List<Bill> billAll = bds.selectInfo(bill);
        String s = JSON.toJSONString(billAll);
        return s;
    }
    @RequestMapping("billlist")
    public ModelAndView billlist(ModelAndView model, HttpSession session,HttpServletResponse response, String queryProductName, String queryProviderId, String queryIsPayment){
        List<Provider> providerAll = ps.getAll();
        List<Bill> billAll = bds.getAll();
        model.addObject("billList",billAll);
        session.setAttribute("providerList",providerAll);
        model.setViewName("jsp/billlist");
        return model;
    }
    @RequestMapping("user")
    public String user(){
        return "jsp/frame";
    }
    @ResponseBody
    @RequestMapping("add")
    public ModelAndView add(ModelAndView model,HttpServletResponse response,HttpSession session,String billCode, String productName,
                      String productUnit,String productCount,String totalPrice,String queryProviderId,String isPayment){
        User user = (User)session.getAttribute("user");
        int createdBy = user.getId();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Bill bill = new Bill(0,billCode,productName,null,productUnit,Integer.parseInt(productCount),Integer.parseInt(totalPrice),Integer.parseInt(isPayment),createdBy
                ,sdf.format(new Date()),0,null,null,Integer.parseInt(queryProviderId));
        int i = bds.addInfo(bill);
        if (i>0){
            return new ModelAndView("forward:billlist");
        }
        else {
            PrintWriter out = null;
            try {
                out = response.getWriter();
            } catch (IOException e) {
                e.printStackTrace();
            }
            out.print("<script>");
            out.print("alert('添加失败')");
            out.print("location.href='billadd.jsp';");
            out.print("</script>");
        }
        return null;
    }
    @RequestMapping("view")
    public ModelAndView view(ModelAndView model,String billid){
        Bill bill = bds.userInfo(Integer.parseInt(billid));
        model.addObject("bill",bill );
        model.setViewName("jsp/billview");
        return model;
    }
    @RequestMapping("modify")
    public ModelAndView modify(ModelAndView model,String billid){
        Bill bill = bds.userInfo(Integer.parseInt(billid));
        model.addObject("bill",bill );
        model.setViewName("jsp/billmodify");
        return model;
    }
    @ResponseBody
    @RequestMapping("updateInfo")
    public void updateInfo(HttpServletResponse response,HttpSession session,String billCode, String productName,
                            String productUnit,String productCount,String totalPrice,String queryProviderId,String isPayment,String id){
        User user = (User)session.getAttribute("user");
        int createdBy = user.getId();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Bill bill = new Bill(Integer.parseInt(id),billCode,productName,null,productUnit,Integer.parseInt(productCount),Integer.parseInt(totalPrice),Integer.parseInt(isPayment),createdBy
                ,sdf.format(new Date()),0,null,null,Integer.parseInt(queryProviderId));
        int i = bds.updateInfo(bill);
        if (i>0){
            PrintWriter out = null;
            try {
                out = response.getWriter();
            } catch (IOException e) {
                e.printStackTrace();
            }
            out.print("<script>alert('修改成功');window.location.href='smbms/user'</script>");
        }else {
            PrintWriter out = null;
            try {
                out = response.getWriter();
            } catch (IOException e) {
                e.printStackTrace();
            }
            out.print("<script>alert('修改失败');window.location.href='smbms/user'</script>");
        }
    }
    @ResponseBody
    @RequestMapping("del")
    public String del(ModelAndView model,HttpServletResponse response,String billid){
        int i = bds.delInfo(Integer.parseInt(billid));
        if (i>0){
            return "true";
        }else if (i==0){
            return "notexist";
        }else {
            return "false";
        }
    }
}
