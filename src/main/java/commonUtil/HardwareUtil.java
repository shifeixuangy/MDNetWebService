package commonUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by liudongdong on 2016/4/5.
 */
public class HardwareUtil {
    public static String getLocalMacAddress()
    {
        try {
            byte[] mac = NetworkInterface.getByInetAddress(InetAddress.getLocalHost()).getHardwareAddress();
            StringBuilder sb = new StringBuilder();
            for(int i=0; i<mac.length; i++) {
                if(i!=0) {
                    sb.append("-");
                }
                //字节转换为整数
                int temp = mac[i]&0xff;
                String str = Integer.toHexString(temp);
                if(str.length()==1) {
                    sb.append("0"+str);
                }else {
                    sb.append(str);
                }
            }
            return sb.toString().replace("-","").toLowerCase();
        }
        catch (Exception ex)
        {
            throw new RuntimeException(ex);
        }
    }

    public static List<String>getAllLocalMacs()
    {
        List<String>result=new LinkedList<>();
        try {
            Enumeration<NetworkInterface> nis = NetworkInterface.getNetworkInterfaces();
            while (nis.hasMoreElements())
            {
                NetworkInterface ni=nis.nextElement();
                byte[]mac= ni.getHardwareAddress();
                if(mac==null)
                    continue;
                StringBuilder sb = new StringBuilder();
                for(int i=0; i<mac.length; i++) {
                    if(i!=0) {
                        sb.append("-");
                    }
                    //字节转换为整数
                    int temp = mac[i]&0xff;
                    String str = Integer.toHexString(temp);
                    if(str.length()==1) {
                        sb.append("0"+str);
                    }else {
                        sb.append(str);
                    }
                }
                result.add(sb.toString().replace("-","").toLowerCase());
            }
        }
        catch (Exception ex)
        {
            throw new RuntimeException(ex);
        }
        return result;
    }

    public static String getMotherboardSerial() {
        String result = "";
        try {
            File file = File.createTempFile("GetMBSerial",".vbs");
            file.deleteOnExit();
            FileWriter fw = new FileWriter(file);

            String vbs =
                    "Set objWMIService = GetObject(\"winmgmts:\\\\.\\root\\cimv2\")\n"
                            + "Set colItems = objWMIService.ExecQuery _ \n"
                            + "   (\"Select * from Win32_ComputerSystemProduct\") \n"
                            + "For Each objItem in colItems \n"
                            + "    Wscript.Echo objItem.IdentifyingNumber \n"
                            + "Next \n";

            fw.write(vbs);
            fw.close();
            Process gWMI = Runtime.getRuntime().exec("cscript //NoLogo " + file.getPath());
            BufferedReader input = new BufferedReader(new InputStreamReader(gWMI.getInputStream()));
            String line;
            while ((line = input.readLine()) != null) {
                result += line;
                System.out.println(line);
            }
            input.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        result = result.trim();
        return result;
    }
}
