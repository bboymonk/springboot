package com.wjb.controller;

import com.wjb.service.PicService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by wjb on 2017/3/9.
 */
@Controller
public class IndexController {
    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);
    @Autowired
    private PicService picService;


    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String hello(){
        return "boy";
    }

    @RequestMapping(value = "/PhotoUpload",method = RequestMethod.GET)
    public String PhotoUpload(){
        return "photoUpload/photo";
    }

//    @ResponseBody
//    @RequestMapping(value = "/picUpload")
//    public String picUpload(HttpServletRequest request) throws Exception {
//        logger.info("======================进入文件上传方法");
//        request.setCharacterEncoding("UTF-8");
//        //解析请求判断是否为文件上传类型
//        boolean multipartContent = ServletFileUpload.isMultipartContent(request);
//        //让上传的文件放在服务器上真实路径下的upload文件夹下
//        String upload = request.getSession().getServletContext().getRealPath("/webapp/static/upload/");
//        //判断upload文件夹是否存在，不存在自动创建
//        File file = new File("upload");
//        if(!file.exists()){
//            file.mkdir();
//        }
//
//        String picPath = request.getParameter("picPath");
//        Pic pic = new Pic();
//        pic.setCreatdate(new Date());
//        pic.setPicpath(picPath);
//
//
//        if(multipartContent){
//            //创建文件上传的核心类
//            FileItemFactory factory = new DiskFileItemFactory();
//            //解析器
//            ServletFileUpload uploadPath = new ServletFileUpload(factory);
//            //解析请求
//            List<FileItem> fileItems = uploadPath.parseRequest(request);
//
//
//            //获取迭代器
//            Iterator<FileItem> iterator = fileItems.iterator();
//            while(iterator.hasNext()){
//                FileItem fileItem = iterator.next();
//                //判断fileItem是文本域还是文件域
//                if(fileItem.isFormField()){  //如果是普通的文本表单控件
//                    String fieldName = fileItem.getFieldName();//得到的是input框里name的值
//                    logger.info("======================"+fieldName);
//                }else{   //若是文件表单控件
//                    String name = fileItem.getName();   //本地上传文件的全路径加文件名
//                    logger.info("=============="+fileItem.getName());
//                    if(name != null && !name.equals("")){
//                        File fullFile = new File(name);
//                        File saveFile = new File(upload,fullFile.getName()); //把文件指定到saveFile里
//                        fileItem.write(saveFile); //把fileItem文件的内容写到saveFile里
//
//                    }
//                }
//            }
//        }
//
//        int i = picService.insertSelective(pic);
//        if(i > 0){
//            return "增加成功";
//        }else{
//            return "增加失败";
//        }
//
//    }


}


