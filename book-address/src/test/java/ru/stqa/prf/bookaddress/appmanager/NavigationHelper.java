package ru.stqa.prf.bookaddress.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends HelperBase{

    public NavigationHelper(WebDriver wb) {
        super(wb);
    }

    public void gotoGroupPage() {
        if (isElementPresent(By.tagName("h1"))
                && wb.findElement(By.tagName("h1")).getText().equals("Groups")
                && isElementPresent(By.name("new"))) {
            return;
        }
        Click(By.linkText("groups"));
      //wb.findElement(By.linkText("groups")).click();
    }

    public void gotoHomePage() {
        if(isElementPresent(By.id("maintable"))){
            return;
        }
        Click(By.linkText("home"));
    }
}
