package top.wanda92632.blog.util;

import com.qiniu.util.Auth;

/**
 * @author ZhiFei
 */
public class UploadUtil {
    /**
     * 返回upToken
     * @return
     */
    public static String getUpToken(){
        //生成上传凭证
        String accessKey = "E07ipcYc6VM9TEfwvGMZ9xDJ6TTLFsqVrVJlh-cm";
        String secretKey = "N6_BO5IXAQWYt70j-dxjz_9Kq96IE1-LgUMrE833";
        //bucket名
        String bucket = "blog";
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        return upToken;
    }

    public static void main(String[] args) {
        System.out.println(getUpToken());
    }
}
