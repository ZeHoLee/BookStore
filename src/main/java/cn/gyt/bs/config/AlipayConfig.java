package cn.gyt.bs.config;


import org.springframework.context.annotation.Configuration;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

@Configuration
public class AlipayConfig {

//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public static String app_id = "2016101800712266";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCF2GWjTa7q5MPP1/SioYUt8AnktjO8CAaVos2T6bckHj3EgZAdAamkukiUY0Moc+BqcRaykAaqC0t5g0O4YKdjmAvGbLtOX4km5izvEsST5fpw5njcY/ufn7c8uZLCPI3m4L4GCseOv1i/ON4Wl+ZQ0lMBin2QBTU2XtC9IkcfUv+01dV+Lbe0x0DCipuEUtI0V9IUp2elLOVZXvFXrQM/N/mYp+ZzmAHPhrt+XPzcdFOgoLB0SpvJbKgE/Do+pq7JHI707RVsE4v05n7jk4t9QZsZQnPIGIW5BzoeV1YawK2R4SSs+r6pzgSFxN9Wnajj/BI7RJBcWq9TOvOv5I9RAgMBAAECggEARWg3JJ0HJPXmeOSTDrbVukGsz7aQU2SSDYy0MIzVFNy9ySYq1m/jSfHz60ciL1sPuALW81furMVYqTWhq34rr2DMUmRWKUkvdUWNbZh86fkritppOFh8xoTrX4kYCrOeHmjTAY0bePHPErJpsKuqmqTGuJjZjhK1SnGWq02YNixxRSgMB4Z4KWy5gwsSBElU5DsjjG8J8aismBAt0qtlMS4dZri9HUeMJULTc5p32cMqkfoKpLbf3RvmUnVRPWdfXn5odFHfeS/bBScCcD6McreCuVyfjJE9ITgpgHClmYq1qOddgu7slf5X4AbVvhRVd+sFpPQhFbsHYvQ7HB6FZQKBgQDVzMHRdyl9FH29fO5EuhHfBOJxz1Eh08NmhH5rbm+dULmNnGgUBN2pc/oiN+rprw16UiRESrPJcUOskz6ljWGaWKcwREB+3xrnO/CHZ5FICtLJna63ovf2e37kBZf/sPtYW8MDwKqedyl8oOnyoI+Em+ZNOStlbs4iK76ecFgaFwKBgQCgQ4+U0TyPqAv2mY8DXq8CZ6E/lP3Cn2XJlLMpbOFZZI9CuOXnrLmQ6jJjjGkGUTZV5Z5DLb5J8RV79xKa5sr36AMfDFChSMwrKqy8j7rJpAN/AA9qZAy6ZkR0EooQ1PWWBqVtr9wI7gj7GMm8l7pV/uiJ7dwL+hrxGURJ+gRK1wKBgC1yYATRqr0O3QephdCQKPpaRHRi2GEzbYbeRS07BXjQTxEeHTWcLy41HAeoEURpVJka2uC1bHA2pmcJybhRTF0N2UQ2TetqK4LZx/BQgJ5qxpHa9efNOZ5oc/mugrf0fAk1QEgM7XfP5r66SeCsZrWOmi7yJxphqmmzGMr/IIszAoGAQ8+SZzOJXneKaFNdMd3USz4x+kc+gCUV2o9yp2qMh1iHTYIStU5eTjTqETkOD0A5r2qy5lXqmDrMDmHim2xgITkoaPC7BSNaUEGESVy/eiZN3GTYIPoocI5pFHvrs2eIamxxftHUVmrxnd1FLuk8TLrJBeOBIgg3ciBwxqRlQI0CgYAC+jkmPwgVXUM9Ek2QSC6CXc5WVMJM6ZvM9nMZIg1TfIUwgF4mnZHzbDw4SwH6n95LQr3Jk4mTNfrujWWMMpASSUHHw97Y5k+gaE3oLQC1ff/oquAsp9br5AYcUY4gkQinwnMIFuAYfLohXSD2PjXPZwFhDsyiK1hNGSD5+UKHbQ==";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAq1ug0CTTxNshA9Tuz0sAPqpr3vvfwlK+nCgVGCTUkZ9ySBqphQp6CdSh9/lwrp9uyESnAKul/Jfp2zrYQmZhl1jzePaLYDObFUI/m3kpwxemraD+ht8Fijccn1Cl9CEIAMOCMIM0M6zxSP7V6BojgUbQoOQGF3mDeEwJMkjqjYfBcF6XK+jNazTWGbh0+c70Vdn7imWjBaZTSyC2BSSWOAQG2ld5G6TD3j+wJtd+zj3XFK+l8teoS52ffhv9XJjFwy2I/j9NlIT1aT+uJd2rQ+8mDgz6xu4plg4xF6dw6Jsx/uCeC+nsN93M4I71L5T1YyhGpL+bL5iNRvOOtx98fwIDAQAB";

//    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
//    public static String notify_url = "http://工程公网访问地址/alipay.trade.page.pay-JAVA-UTF-8/notify_url.jsp";
    public static String notify_url = "http://z34ykp.natappfree.cc/pay/notify";

//    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
//    public static String return_url = "http://工程公网访问地址/alipay.trade.page.pay-JAVA-UTF-8/return_url.jsp";
    public static String return_url = "http://z34ykp.natappfree.cc/pay/return";

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String charset = "utf-8";

    // 支付宝网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    // 支付宝网关
    public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     *
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis() + ".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
