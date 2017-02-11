package sample;

import java.io.IOException;
import java.net.MalformedURLException;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;


public class PSLogin {
    private final WebClient WEB_CLIENT = new WebClient(BrowserVersion.CHROME);

    private final String username;
    private final String password;


    public PSLogin(String username, String password){
        this.username = username;
        this.password = password;

        WEB_CLIENT.getCookieManager().setCookiesEnabled(true);
    }

    public void login(){

        String loginURL = "https://powerschool.asd.edu.qa/public/home.html";
        try {

            HtmlPage loginPage = WEB_CLIENT.getPage(loginURL);
            HtmlForm loginForm = loginPage.getFirstByXPath("//form[@id='LoginForm']");

            loginForm.getInputByName("account").setValueAttribute(username);
            loginForm.getInputByName("pw").setValueAttribute(password);

            loginForm.getElementsByTagName("button").get(0).click();

        } catch (FailingHttpStatusCodeException e) {
//            e.printStackTrace();
        } catch (MalformedURLException e) {
//            e.printStackTrace();
        } catch (IOException e) {
//            e.printStackTrace();
        }
    }

    public String get(String URL){
        try {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     return WEB_CLIENT.getPage(URL).getWebResponse().getContentAsString();
        } catch (FailingHttpStatusCodeException e) {
//            e.printStackTrace();
        } catch (MalformedURLException e) {
//            e.printStackTrace();
        } catch (IOException e) {
//            e.printStackTrace();
        }
        return null;
    }
}