package ru.stqa.prf.bookaddress.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SessionHelper extends HelperBase{
    private WebDriver wb;
    public SessionHelper(WebDriver wb) {
        super(wb);
    }
    public void login(String username, String password) {
        type(By.name("user"),username);
        type(By.name("pass"),password);
        Click(By.xpath("//input[@value='Login']"));
//        wb.findElement(By.name("user")).clear();
//        wb.findElement(By.name("user")).sendKeys(username);
//        wb.findElement(By.name("pass")).click();
//        wb.findElement(By.name("pass")).clear();
//        wb.findElement(By.name("pass")).sendKeys(password);
//        wb.findElement(By.xpath("//input[@value='Login']")).click();
    }
}
