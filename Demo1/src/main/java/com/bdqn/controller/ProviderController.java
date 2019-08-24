package com.bdqn.controller;

import com.bdqn.entity.Provider;
import com.bdqn.entity.User;
import com.bdqn.services.ProviderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
@Controller
@RequestMapping("provider.do")
public class ProviderController {
    @Resource
    ProviderService ps;

    @RequestMapping("query")
    public ModelAndView load(ModelAndView model){
        List<Provider> providerAll = ps.getAll();
        model.addObject("providerList",providerAll);
        model.setViewName("jsp/providerlist");
        return model;
    }
    @RequestMapping("selectInfo")
    public ModelAndView selectInfo(ModelAndView model,String queryProCode,String queryProName){
        List<Provider> providers = ps.selectInfo(queryProCode, queryProName);
        model.addObject("providerList",providers);
        model.setViewName("jsp/providerlist");
        return model;
    }
    @RequestMapping("add")
    public ModelAndView add(ModelAndView model, HttpSession session, String proCode, String proName, String proContact
            , String proPhone, String proAddress, String proFax, String proDesc){
        User user = (User) session.getAttribute("user");
        Provider provider = new Provider(0,proCode,proName,proDesc,proContact,proPhone,proAddress,proFax,
                user.getId(),new Date(),null,0);
        int i = ps.addInfo(provider);
        if (i>0){
            return new ModelAndView("forward:query");
        }
        return null;
    }
    @RequestMapping("view")
    public ModelAndView view(ModelAndView model,String proid){
        Provider provider = ps.userInfo(Integer.parseInt(proid));
        model.addObject("provider",provider );
        model.setViewName("jsp/providerview");
        return model;
    }
    @RequestMapping("modify")
    public ModelAndView modify(ModelAndView model,String proid){
        Provider provider = ps.userInfo(Integer.parseInt(proid));
        model.addObject("provider",provider );
        model.setViewName("jsp/providermodify");
        return model;
    }
    @ResponseBody
    @RequestMapping("updateInfo")
    public void add(ModelAndView model, HttpServletResponse response, HttpSession session, String proid, String proCode, String proName
            , String proContact, String proPhone, String proAddress, String proFax, String proDesc){
        Provider provider = new Provider(Integer.parseInt(proid),proCode,proName,proDesc,proContact,proPhone,proAddress,proFax,
                0,new Date(),null,0);
        int i = ps.updateInfo(provider);
        if (i>0){
            PrintWriter out = null;
            try {
                out = response.getWriter();
            } catch (IOException e) {
                e.printStackTrace();
            }
            out.print("<script>alert('修改成功');window.location.href='provider.do/query'</script>");
        }else {
            PrintWriter out = null;
            try {
                out = response.getWriter();
            } catch (IOException e) {
                e.printStackTrace();
            }
            out.print("<script>alert('修改失败');window.location.href='provider.do/query'</script>");
        }
    }
    @ResponseBody
    @RequestMapping("del")
    public String del(ModelAndView model,HttpServletResponse response,String proid){
        int i = ps.delInfo(Integer.parseInt(proid));
        if (i>0){
            return "true";
        }else if (i==0){
            return "notexist";
        }else {
            return "false";
        }
    }
}
