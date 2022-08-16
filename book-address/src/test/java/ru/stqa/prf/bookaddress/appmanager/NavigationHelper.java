package ru.stqa.prf.bookaddress.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends HelperBase{
    private WebDriver wb;

    public NavigationHelper(WebDriver wb) {
        super(wb);
    }

    public void gotoGroupPage() {
        Click(By.linkText("groups"));
      //wb.findElement(By.linkText("groups")).click();
    }

    public void gotoHomePage() {
        Click(By.linkText("home"));
    }
}
