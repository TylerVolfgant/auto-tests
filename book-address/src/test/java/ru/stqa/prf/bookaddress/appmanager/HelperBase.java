package ru.stqa.prf.bookaddress.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import ru.stqa.prf.bookaddress.model.GroupData;
import org.openqa.selenium.support.ui.Select;

import java.io.File;

public class HelperBase {
    protected WebDriver wd;

    public HelperBase(WebDriver wb) {
        this.wd = wb;
    }

    protected void Click(By locator) {
        wd.findElement(locator).click();
    }

    protected void type(By locator, String text) {
        Click(locator);
        if (text != null){
            String existingText = wd.findElement(locator).getAttribute("value");
            if(! text.equals(existingText)){
                wd.findElement(locator).clear();
                wd.findElement(locator).sendKeys(text);
            }
        }
    }
    protected void selectFromDropDown(String locator, String text) {
        new Select(wd.findElement(By.name(locator))).selectByVisibleText(text);

    }

    protected void attach(By locator, File file) {
                wd.findElement(locator).sendKeys(file.getAbsolutePath());
            }


    public boolean isAlertPresent() {
        try {
            wd.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    protected boolean isElementPresent(By locator) {
        try{
            wd.findElement(locator);
            return true;
        } catch (NoSuchElementException ex){
            return false;
        }
    }
}