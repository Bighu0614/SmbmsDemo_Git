package com.bdqn.controller;

import com.alibaba.fastjson.JSON;
import com.bdqn.conlletion.UserDao;
import com.bdqn.entity.Page;
import com.bdqn.entity.Role;
import com.bdqn.entity.User;
import com.bdqn.entity.User2;
import com.bdqn.services.RoleService;
import com.bdqn.services.UserDaoServiceImpl;
import org.apache.commons.io.FilenameUtils;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("user.do")
public class UserController {
    @Resource
    UserDaoServiceImpl uds;
    @Resource
    RoleService rs;

    @RequestMapping("query")
    public ModelAndView loadInfo(String page, HttpSession session, ModelAndView model){
        List<Role> roleAll = rs.getAll();
        session.setAttribute("roleList",roleAll);
        Page p1 = new Page(0,page!=null?Integer.parseInt(page):1,0,page!=null?Integer.parseInt(page):1);
        List<User2> userAll = uds.getAll(p1);
        model.addObject("userList",userAll);
        int count = uds.getCount(new User2(),0);
        Page param = new Page(count,page!=null?Integer.parseInt(page):1,count%5==0?count/5:count/5+1,page!=null?Integer.parseInt(page):1);
        model.addObject("Page",param);
        model.setViewName("jsp/userlist");
        return model;
    }
    @RequestMapping("login")
    public ModelAndView login(ModelAndView model,String userCode,String userPassword,
                   HttpSession session ){
        User user = uds.checkCode(userCode, userPassword);
        if (user != null) {
            session.setAttribute("user", user);
            model.addObject("user",user);
            return new ModelAndView("redirect:/smbms/user");
        }else {
//           return new ModelAndView("redirect:../login.jsp");
            throw new RuntimeException("用户名或密码不正确");
        }
    }
    //局部异常
//    @ExceptionHandler(value = {RuntimeException.class})
//    public String exception(RuntimeException e ,HttpServletRequest request){
//        request.setAttribute("e", e);
//        return "jsp/error";
//    }
    @RequestMapping("fanye")
    public ModelAndView fanye(String page){
        return new ModelAndView("redirect:query?page="+page);
    }
    @RequestMapping("selectInfo")
    public ModelAndView selectInfo(ModelAndView model,String queryUserRole,String queryname){
        User2 u = new User2(queryname, Integer.parseInt(queryUserRole));
        List<User2> user2s = uds.selectInfo(u,1);
        int count = uds.getCount(u,0);
        Page param = new Page(count,1,count%5==0?count/5:count/5+1,1);
        model.addObject("queryUserName",queryname);
        model.addObject("queryUserRole",queryUserRole);
        model.addObject("Page",param);
        model.addObject("userList",user2s);
        model.setViewName("jsp/userlist");
        return model;
    }
//    @ModelAttribute("user")
//    public User getUser(){
//        return new User();
//    }
    @RequestMapping("add")
    public ModelAndView add(ModelAndView model, HttpSession session, HttpServletRequest request, @RequestParam(value = "pics",required = false) MultipartFile[]  attach,
                            User user) {
        String idPicPath = null;
        //判断文件是否为空
        for (int i = 0; i<attach.length;i++){
            if (!attach[i].isEmpty()){
                String realPath = session.getServletContext().getRealPath("images" + File.separator + "uploadfiles");
                String originalFilename = attach[i].getOriginalFilename(); //原文件名
                String extension = FilenameUtils.getExtension(originalFilename);  //    原文件后缀
                int filesize = 500000;
                if (attach[i].getSize()>filesize){
                    throw new RuntimeException("文件大小不得大于500K");
                }
                File picc = new File(realPath,originalFilename);
                if (picc.mkdir()){}
                try {
                    attach[i].transferTo(picc);   //上传文件
                } catch (IOException e) {
                    throw new RuntimeException("图片上传失败");
                }
//            idPicPath = realPath+File.separator+originalFilename;
            }
        }

//        user.setIdPicPath(idPicPath);
        user.setCreationDate(new Date());
        User user1 = (User) session.getAttribute("user");
//        int queryUserRole1 = Integer.parseInt(queryUserRole);
//        int gender1 = Integer.parseInt(gender);
//        User user = new User(0, userCode, userName, userPassword, gender1, birthday, phone, address, queryUserRole1, user1.getId(),
////                new Date(), 0, null,idPicPath);
        int i = uds.addInfo(user);
        if (i > 0) {
            return new ModelAndView("forward:query");
        }
        return null;
    }
    @ResponseBody
    @RequestMapping("updatePwd")
    public void updatePwd(HttpServletResponse response, HttpSession session, String oldpassword,
                                  String newpassword, String rnewpassword){
        response.setCharacterEncoding("UTF-8");
        User user = (User)session.getAttribute("user");
        if (user.getUserPassword().equals(oldpassword)){
            int i = uds.updatePwd(user.getUserName(), newpassword);
            if (i>0){
                PrintWriter out = null;
                try {
                    out = response.getWriter();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                out.print("<script  language=\"JavaScript\">alert('修改成功');window.location.href='query'</script>");
            }
            else {
                PrintWriter out = null;
                try {
                    out = response.getWriter();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                out.print("<script  language=\"JavaScript\">alert('修改失败,密码错误');window.location.href='jsp/pwdmodify.jsp'</script>");
            }
        }
    }
    @ResponseBody
    @RequestMapping("out")
    public void out(ModelAndView model, HttpServletResponse response){
        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        out.print("<script>");
        out.print("if(confirm('确定要退出吗')){sessionStorage.clear();window.location.href='../login.jsp'}");
        out.print("else{history.go(-1)}");
        out.print("</script>");
    }
    @RequestMapping("view")
    public ModelAndView view(ModelAndView model, HttpServletResponse response,String uid){
        User user = uds.userInfo(Integer.parseInt(uid));
        model.addObject("user",user );
        model.setViewName("jsp/userview");
        return model;
    }
    @RequestMapping("modify")
    public ModelAndView modify(ModelAndView model, HttpServletResponse response,String uid){
        User user = uds.userInfo(Integer.parseInt(uid));
        model.addObject("user",user );
        model.setViewName("jsp/usermodify");
        return model;
    }
    @ResponseBody
    @RequestMapping("updateInfo")
    public void add(HttpServletResponse response,HttpSession session,User user){
//        int queryUserRole1 = Integer.parseInt(queryUserRole);
//        int gender1 = Integer.parseInt(gender);
//        User user = new User(0,null,userName,null,gender1,birthday,phone,address,queryUserRole1,0,
//                new Date(),0,null,null);
        int i = uds.updateInfo(user);
        if (i>0){
            PrintWriter out = null;
            try {
                out = response.getWriter();
            } catch (IOException e) {
                e.printStackTrace();
            }
            out.print("<script>alert('修改成功');window.location.href='query'</script>");
        }else {
            PrintWriter out = null;
            try {
                out = response.getWriter();
            } catch (IOException e) {
                e.printStackTrace();
            }
            out.print("<script>alert('修改失败');window.location.href='query'</script>");
        }
    }
    @ResponseBody
    @RequestMapping("del")
    public String del(String uid){
        int i = uds.delInfo(Integer.parseInt(uid));
        if (i>0){
            return "true";
        }else if (i==0){
            return "notexist";
        }else {
            return "false";
        }
    }
    @ResponseBody
    @RequestMapping("check")
    public String check( HttpServletResponse response,String userCode){
        int i = uds.checkName(userCode);
        User user = new User();
        if (i>0){
            user.setUserCode("exist");
        }else if (userCode==null||userCode==""){
            user.setUserCode("isnull");
        }
        String s = JSON.toJSONString(user);
        return s;
    }
    @Test
    public void test(){
//        CommonsMultipartResolver
        ApplicationContext ac = new ClassPathXmlApplicationContext("Application.xml");
        UserDaoServiceImpl uds = ac.getBean("uds",UserDaoServiceImpl.class);
        User user2 = uds.checkCode("admin", "12345678");
        System.out.println(user2.getUserName());
    }
}
