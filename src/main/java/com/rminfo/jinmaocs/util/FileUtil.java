package com.rminfo.jinmaocs.util;

import com.rminfo.jinmaocs.entity.R5rmdocuments;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * Created by carl on 2017/1/9.
 */
public class FileUtil {
    private static final int MAX_SIZE = 1024 * 1024 * 2;   //限制用户头像的最大值为1M
    private static String rootPath;                    //文件根路径
    private static String imageNewPath;        //头像新路径（包含头像名以及扩展名）
    private static String imageOldPath;        //头像在数据库中的原有路径（包含头像名以及扩展名）
    private static String imageNames;          //头像的新名字（时间+用户名），时间精确到毫秒
    private static String extendName;         //头像的扩展名，进行扩展名验证，以达到对用户头像的图片类型限制
    private static String message;                 //用于返回上传头像的信息
    private static String imageURL;                //用于返回用户头像存放的物理路径
    private static MultipartFile imageFile;

    /***
     * 保存文件
     *
     * @param file
     * @return
     */
    public static boolean saveFile(MultipartFile file, String fileName) {
        // 判断文件是否为空
        if (!file.isEmpty()) {
            try {
                //获取文件扩展名
                extendName = fileName.substring(fileName.lastIndexOf("."));
                //获取上传图片的大小
                int imageSize = (int) file.getSize();
                //验证图片的扩展名是否符合要求
                String extendNamesArray = ConfigUtil.getValue(ConfigConstants.EXTENDNAMESARRAY);
                String path = ConfigUtil.getValue(ConfigConstants.FILESAVEPATH);
                if (extendNamesArray.contains(extendName)) {
                    // 保存的文件路径
                    rootPath = path + fileName;
                    File saveDir = new File(rootPath);
                    if (!saveDir.getParentFile().exists())
                        saveDir.getParentFile().mkdirs();
                    // 转存文件
                    file.transferTo(saveDir);
                    return true;
                } else {
                    return false;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 读取多个文档zip返回
     *
     * @param response
     * @param r5rmDocs
     */
    public static void readDoc(HttpServletResponse response, Object[] r5rmDocs) {
        if (r5rmDocs.length == 0) return;
        //响应头设置为下载压缩文件
        response.setContentType("application/zip");
        response.setHeader("Content-Disposition", "attachment;filename=doc.zip");
        String path = ConfigUtil.getValue(ConfigConstants.FILESAVEPATH);
        ZipOutputStream zos = null;
        FileInputStream in = null;
        try {
            zos = new ZipOutputStream(response.getOutputStream());
            for (int i = 0; i < r5rmDocs.length; i++) {
                File file = new File(path + r5rmDocs[i]);
                if (file.exists()) {
                    in = new FileInputStream(file);
                    //添加压缩实体
                    zos.putNextEntry(new ZipEntry((String) r5rmDocs[i]));
                    byte[] b = new byte[1024];
                    int n = 0;
                    while ((n = in.read(b)) != -1) {
                        zos.write(b, 0, n);
                    }
                    in.close();
                }
            }
            zos.flush();
            zos.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                zos.close();
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 读取多个图片zip返回
     *
     * @param response
     * @param r5rmDocs
     */
    public static void readImages(HttpServletResponse response, Object[] r5rmDocs) {
        if (r5rmDocs.length == 0) return;
        //响应头设置为下载压缩文件
        response.setContentType("application/zip");
        response.setHeader("Content-Disposition", "attachment;filename=doc.zip");
        String path = ConfigUtil.getValue(ConfigConstants.FILESAVEPATH);
        ZipOutputStream zos = null;
        FileInputStream in = null;
        try {
            zos = new ZipOutputStream(response.getOutputStream());
            //验证图片的扩展名是否符合要求
            String extendNamesArray = ConfigUtil.getValue(ConfigConstants.EXTENDNAMESARRAY);
            for (int i = 0; i < r5rmDocs.length; i++) {
                //获取文件扩展名
                extendName = r5rmDocs[i].toString().substring(r5rmDocs[i].toString().lastIndexOf("."));
                if (extendNamesArray.contains(extendName)) {
                    File file = new File(path + r5rmDocs[i]);
                    if (file.exists()) {
                        in = new FileInputStream(file);
                        //添加压缩实体
                        zos.putNextEntry(new ZipEntry((String) r5rmDocs[i]));
                        byte[] b = new byte[1024];
                        int n;
                        while ((n = in.read(b)) != -1) {
                            zos.write(b, 0, n);
                        }
                        in.close();
                    }
                }
            }
            zos.flush();
            zos.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                zos.close();
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 文件多个传
     *
     * @param myfiles
     * @return
     * @throws IOException
     */
    public String upload(MultipartFile[] myfiles) throws IOException {
        String result = "文件未上传";
        for (MultipartFile file : myfiles) {
            //此处MultipartFile[]表明是多文件,如果是单文件MultipartFile就行了
            if (file.isEmpty()) {
                System.out.println("文件未上传!");
            } else {
                //得到上传的文件名
                String fileName = file.getOriginalFilename();
                int idnex = fileName.indexOf(".");
                //得到服务器项目发布运行所在地址
                String path1 = ConfigUtil.getValue("filePath");
                if (idnex != -1) {
                    String Suffix = fileName.substring(idnex);
                    String[] videoList = ConfigUtil.videoList();
                    for (int i = 0; i < videoList.length; i++) {
                        if (Suffix.equals(videoList[i])) {
                            path1 = ConfigUtil.getValue("videoPath");
                            break;
                        }
                    }
                }
                Thread thread = new Thread();
                try {
                    thread.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //  此处未使用UUID来生成唯一标识,用日期做为标识
                String time = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
                String path = path1 + time + "_" + fileName;
                //查看文件上传路径,方便查找
                System.out.println(path);
                //把文件上传至path的路径
                File localFile = new File(path);
                file.transferTo(localFile);
                result = "文件上传成功";
            }
        }
        return result;
    }

    /**
     * 上传单个
     *
     * @param myfile
     * @return
     * @throws IOException
     */
    public Map<String, Object> uploadDan(MultipartFile myfile, R5rmdocuments r5rmdocuments) throws IOException {
        String result = "0";
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("result", result);
        if (!(myfile.isEmpty())) {
            String fileName = myfile.getOriginalFilename();
            //得到服务器项目发布运行所在地址
            String path1 = ConfigUtil.getValue(ConfigConstants.FILEPATH);
            Thread thread = new Thread();
            try {
                thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //  此处未使用UUID来生成唯一标识,用日期做为标识
            String time = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
            r5rmdocuments.setDocnewname(time + "_" + fileName);
            r5rmdocuments.setDocoldname(fileName);
            String path = path1 + time + "_" + fileName;
            //查看文件上传路径,方便查找
            System.out.println(path);
            //把文件上传至path的路径
            File localFile = new File(path);
            myfile.transferTo(localFile);
            result = "1";
            map.put("result", result);
            map.put("r5rmdocuments", r5rmdocuments);
        }
        return map;
    }

    /**
     * 下载
     *
     * @param fileName
     * @param response
     */
    public static void download(String fileName, HttpServletResponse response) {
        try {
            response.setCharacterEncoding("utf-8");
            response.setContentType("multipart/form-data");
            response.setHeader("Content-Disposition", "attachment;fileName="
                    + new String(fileName.getBytes("utf-8"), "ISO-8859-1"));
            String path = ConfigUtil.getValue(ConfigConstants.FILEPATH);
            int idnex = fileName.lastIndexOf(".");
            if (idnex != -1) {
                String Suffix = fileName.substring(idnex);
                String[] videoList = ConfigUtil.videoList();
                for (int i = 0; i < videoList.length; i++) {
                    if (Suffix.equals(videoList[i])) {
                        path = ConfigUtil.getValue("videoPath");
                        break;
                    }
                }
            }
            InputStream inputStream = new FileInputStream(new File(path
                    + fileName));

            OutputStream os = response.getOutputStream();
            byte[] b = new byte[2048];
            int length;
            while ((length = inputStream.read(b)) > 0) {
                os.write(b, 0, length);
            }
            // 这里主要关闭。
            os.close();
            inputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //  返回值要注意，要不然就出现下面这句错误！
        //java+getOutputStream() has already been called for this response
    }


    /**
     * 下载文件
     * @param fileName
     * @param response
     */
    public static void downloadEvent(String fileName, HttpServletResponse response) {
        try {
            response.setCharacterEncoding("utf-8");
            response.setContentType("multipart/form-data");
            response.setHeader("Content-Disposition", "attachment;fileName="
                    + new String(fileName.getBytes("utf-8"), "ISO-8859-1"));
            String path = ConfigUtil.getValue("fileSavePath");
       /*     int idnex = fileName.lastIndexOf(".");
            if (idnex != -1) {
                String Suffix = fileName.substring(idnex);
                String[] videoList = ConfigUtil.videoList();
                for (int i = 0; i < videoList.length; i++) {
                    if (Suffix.equals(videoList[i])) {
                        path = ConfigUtil.getValue("videoPath");
                        break;
                    }
                }
            }*/
            InputStream inputStream = new FileInputStream(new File(path
                    + fileName));

            OutputStream os = response.getOutputStream();
            byte[] b = new byte[2048];
            int length;
            while ((length = inputStream.read(b)) > 0) {
                os.write(b, 0, length);
            }
            // 这里主要关闭。
            os.close();
            inputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //  返回值要注意，要不然就出现下面这句错误！
        //java+getOutputStream() has already been called for this response
    }
    public static void downloadUpdate(String fileName, HttpServletResponse response, String typeUrl) {
        response.setCharacterEncoding("utf-8");
        response.setContentType("multipart/form-data");
        try {
            response.setHeader("Content-Disposition", "attachment;fileName="
                    + new String(fileName.getBytes("utf-8"),"ISO-8859-1"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        try {
            String path = typeUrl;
            InputStream inputStream = new FileInputStream(new File(path
                    + fileName));

            OutputStream os = response.getOutputStream();
            byte[] b = new byte[2048];
            int length;
            while ((length = inputStream.read(b)) > 0) {
                os.write(b, 0, length);
            }
            // 这里主要关闭。
            os.close();
            inputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //  返回值要注意，要不然就出现下面这句错误！
        //java+getOutputStream() has already been called for this response
    }

    /**
     *  读取ZIP
     * @param file
     * @throws Exception
     */
    public static void readZipFile(String file) throws Exception {
        ZipFile zf = new ZipFile(file);
        InputStream in = new BufferedInputStream(new FileInputStream(file));
        ZipInputStream zin = new ZipInputStream(in);
        ZipEntry ze;
        while ((ze = zin.getNextEntry()) != null) {
            if (ze.isDirectory()) {
            } else {
                System.err.println("file - " + ze.getName() + " : "
                        + ze.getSize() + " bytes");
                long size = ze.getSize();
                if (size > 0) {
                    BufferedReader br = new BufferedReader(
                            new InputStreamReader(zf.getInputStream(ze)));
                    String line;
                    while ((line = br.readLine()) != null) {
                        System.out.println(line);
                    }
                    br.close();
                }
                System.out.println();
            }
        }
        zin.closeEntry();
    }
    public static String readZipContext(String url) throws IOException{

        String zipPath=url;
        ZipFile zf=new ZipFile(zipPath);
        InputStream in=new BufferedInputStream(new FileInputStream(zipPath));
        ZipInputStream zin=new ZipInputStream(in);
        //ZipEntry 类用于表示 ZIP 文件条目。
        ZipEntry ze;
        String value="";
        while((ze=zin.getNextEntry())!=null){
            if(ze.isDirectory()){
                //为空的文件夹什么都不做
            }else{
                System.err.println("file:"+ze.getName()+"\nsize:"+ze.getSize()+"bytes");
               // if(ze.getSize()>0){
                    BufferedReader reader;
                    try {
                        reader = new BufferedReader(new InputStreamReader(zf.getInputStream(ze), "GBK"));
                        String line=null;
                        while((line=reader.readLine())!=null){
                            value=line;
                        }
                        reader.close();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
              //  }
            }
        }
        return value;
    }
/*    public static void main(String[] args) {
        try {
            readZipContext("C:\\inforEAM\\data\\docuploads\\DS_MP_1\\20180404150340newSdFile.zip");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
    public static void main(String[] args) throws Exception {
        try {
            readZipContext("C:\\inforEAM\\data\\docuploads\\DS_MP_1\\20180404150340newSdFile.zip");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


}
