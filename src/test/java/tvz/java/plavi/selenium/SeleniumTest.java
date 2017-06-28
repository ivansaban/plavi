package tvz.java.plavi.selenium;

import io.ddavison.conductor.Browser;
import io.ddavison.conductor.Config;
import io.ddavison.conductor.Locomotive;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import tvz.java.plavi.dao.UserRepository;
import tvz.java.plavi.model.entity.User;

import static org.junit.Assert.*;

/**
 * Created by NIS on 7.6.2017..
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Config(browser = Browser.CHROME, baseUrl = "http://localhost:4200/")
public class SeleniumTest extends Locomotive {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testSelenium() throws Exception {
        driver.switchTo().window("").manage().window().maximize();

        String text = setText(By.name("email"), "admin")
                .setText(By.name("password"), "pass")
                .click(By.xpath("//*[@id=\"loginbox\"]/div[2]/div[2]/form/button"))
                .getText(By.xpath("//*[@id=\"profile-sidebar\"]/div[2]/div[2]/a/p[2]"));

        assertEquals("Ivo Ivić", text);

        validatePresent(By.xpath("/html/body/app-root/div/div/div/app-dashboard/app-general-statistics/div"))
                .selectOptionByText(By.xpath("/html/body/app-root/div/div/div/app-dashboard/app-project-details/div/div[2]/select"), "Projekt2")
                .validatePresent(By.xpath("/html/body/app-root/div/div/div/app-dashboard/app-project-details/app-project-gantt/table/tbody/tr"))
                .validatePresent(By.xpath("/html/body/app-root/div/div/div/app-dashboard/app-project-details/app-project-statistics/div/div[1]/div[1]"))
                .validatePresent(By.xpath("/html/body/app-root/div/div/div/app-dashboard/app-project-details/app-project-statistics/div/div[2]/table/tbody/tr"))
                .validatePresent(By.xpath("//*[@id=\"unfinished\"]/tbody/tr"));

        click(By.xpath("//*[@id=\"profile-sidebar\"]/div[5]/div[2]/a"))
                .setText(By.name("username"), "test")
                .setText(By.name("firstname"), "Test")
                .setText(By.name("lastname"), "Test")
                .setText(By.name("inputPassword"), "pass")
                .selectOptionByText(By.name("roleId"), "user")
                .check(By.id("optionsRadios1"))
        .click(By.xpath("/html/body/app-root/div/div/div/app-user-management/form/button[2]"));

        User user = userRepository.findByUsername("test");
        assertNotNull(user);

        WebDriverWait wait = new WebDriverWait(driver, 5);
        if (wait.until(ExpectedConditions.alertIsPresent()) != null)
            driver.switchTo().alert().accept();

        click(By.xpath("//*[@id=\"profile-sidebar\"]/div[2]/div[2]/button"))
        .validateUrl("http://localhost:4200/login");

        setText(By.name("email"), "test")
                .setText(By.name("password"), "pass")
                .click(By.xpath("//*[@id=\"loginbox\"]/div[2]/div[2]/form/button"))
                .getText(By.xpath("//*[@id=\"profile-sidebar\"]/div[2]/div[2]/a/p[2]"));

        click(By.xpath("//*[@id=\"profile-sidebar\"]/div[2]/div[2]/button"))
                .validateUrl("http://localhost:4200/login");

        userRepository.delete(user.getId());
    }
}
