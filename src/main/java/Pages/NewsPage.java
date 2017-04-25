package main.java.Pages;

import main.java.Support.ElementsHelper;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static main.java.Support.SingletonDriver.getDriver;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.lessThan;

public class NewsPage {

    @FindBy(className = "_timestamp")
    private List<WebElement> timeOfAllPosts;



    private WebElement getPostByIndex(int index){

        ElementsHelper.waitElementsToBeDisplayed(timeOfAllPosts);
        return timeOfAllPosts.get(index);
    }

    private long getCurrentTime(){
        return System.currentTimeMillis();
    }

    private long getTimeOfPostByIndex(int index){
        String timeOfPost = getPostByIndex(index).getAttribute("data-time-ms");
        return Long.parseLong(timeOfPost);
    }

    public NewsPage() {
        PageFactory.initElements(getDriver(), this);
    }


    public void checkTimeDifferenceByIndex(int index){

        long timeOfPost = getTimeOfPostByIndex(index);
        long difference = getCurrentTime() - timeOfPost;
        long differenceInHours = TimeUnit.MILLISECONDS.toHours(difference);
        long oneDay = 24;

        assertThat("Time of post is " + differenceInHours + " it's more than 24 hours",
                differenceInHours, lessThan(oneDay));
    }
}