package tvz.java.plavi.selenium;

import io.ddavison.conductor.Browser;
import io.ddavison.conductor.Config;
import io.ddavison.conductor.Locomotive;
import org.junit.Test;
import org.openqa.selenium.By;
import static org.junit.Assert.*;

/**
 * Created by NIS on 7.6.2017..
 */
@Config(browser = Browser.CHROME, baseUrl = "http://localhost:4200/")
public class SeleniumTest extends Locomotive {

    @Test
    public void testLogin() throws Exception {
        String text = navigateTo("")
                .setText(By.name("email"), "user")
                .setText(By.name("password"), "pass")
                .click(By.xpath("//*[@id=\"loginbox\"]/div[2]/div[2]/form/button"))
                .getText(By.xpath("//*[@id=\"profile-sidebar\"]/div[2]/div[2]/p[2]"));

        assertEquals("Pero Perić", text);
    }
}
