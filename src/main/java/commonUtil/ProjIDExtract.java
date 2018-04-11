package commonUtil;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Response;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by liudongdong on 2015/8/6.
 */
public class ProjIDExtract {
    private static Log logger = LogFactory.getLog(ProjIDExtract.class);
    private static List<String> extractFromRaw = new LinkedList<String>();
    private static List<String> extractFromID = new LinkedList<String>();
    private static List<String> extractFromProjID = new LinkedList<String>();

    private static Pattern PROJ_PATTERN = Pattern.compile("\"projid\":(?<pid>-?\\d+)", Pattern.CASE_INSENSITIVE);
    private static Pattern ID_PATTERN = Pattern.compile("\"id\":(?<pid>-?\\d+)", Pattern.CASE_INSENSITIVE);

    static {
        try (InputStream inputStream = AccessPermissions.class.getResourceAsStream(CommonVariable.PROJID_EXTRACT_PATH);) {
            Properties commonProperties = new Properties();
            commonProperties.load(inputStream);
            commonProperties.stringPropertyNames().forEach(keyName -> {
                String[] lPermissions = commonProperties.getProperty(keyName).split(",");
                if (keyName.equals("Raw")) {
                    extractFromRaw.addAll(Arrays.asList(lPermissions));
                } else if (keyName.equals("ID")) {
                    extractFromID.addAll(Arrays.asList(lPermissions));
                } else {
                    extractFromProjID.addAll(Arrays.asList(lPermissions));
                }
            });

            String s = "";
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
    }

    public static boolean hasProjIDProperty(String path) {
        return (extractFromRaw.contains(path)) || (extractFromID.contains(path)) || (extractFromProjID.contains(path));
    }


    private static int extractProjID(String path, String parameter) {
        if (extractFromRaw.contains(path)) {
            return Integer.parseInt(parameter);
        } else if (extractFromID.contains(path)) {
            Matcher m = ID_PATTERN.matcher(parameter);
            if (!m.find())
                throw new RuntimeException("在--" + path + "中，其参数" + parameter + "没有解析到ID");
            int ipid = Integer.parseInt(m.group("pid"));
            return ipid;
        } else if (extractFromProjID.contains(path)) {
            Matcher m = PROJ_PATTERN.matcher(parameter);
            if (!m.find())
                throw new RuntimeException("在--" + path + "中，其参数" + parameter + "没有解析到ID");
            int ipid = Integer.parseInt(m.group("pid"));
            return ipid;
        }
        throw new RuntimeException("不在要解析的方法中" + path + ":" + parameter);
    }

    public static int extractRequestProjID(ContainerRequestContext requestContext,boolean isResetRequestStream) {
        String path=null,parameter=null;
        try {
            path = requestContext.getUriInfo().getPath();
            parameter = extractRequestParameter(requestContext, isResetRequestStream);
            return extractProjID(path, parameter);
        }
        catch (Exception ex)
        {
            if(parameter==null)
            {
                logger.error("方法："+path+"参数为NULL",ex);
            }
            else
            logger.error("方法："+path+"参数：||"+parameter+"||"+"参数长度："+parameter.length(),ex);
            throw  ex;
        }
    }

    private static String extractRequestParameter(ContainerRequestContext requestContext, boolean isResetRequestStream) {
        String path=requestContext.getUriInfo().getPath();
        InputStream is = requestContext.getEntityStream();
        try {
            if (is == null) {
//                logger.info("方法："+path+"输入流为NULL");
                return null;
            }
            if (is.available() <= 0) {
//                logger.info("方法："+path+"available输入流长度为0");
//                return null;
            }
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len;
//            logger.info("开始读取输入流-------------------------");
            while ((len = is.read(buffer)) > -1) {
                baos.write(buffer, 0, len);
            }
//            logger.info("输入流读取完毕-------------------------"+baos.size());
            baos.flush();
            is.close();
            //重置请求的输入流
            if (isResetRequestStream)
                requestContext.setEntityStream(new ByteArrayInputStream(baos.toByteArray()));
            BufferedReader br = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(baos.toByteArray()), StandardCharsets.UTF_8));
            String strEntity = br.readLine();
//            logger.info("调用方法："+path+"    参数："+strEntity);
            return strEntity;
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw new RuntimeException(ex);
        }
    }


}
