import com.microsoft.playwright.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class TestBase {
    protected static Playwright playwright;
    protected static Browser browser;
    protected BrowserContext context;
    protected Page page;

    @BeforeAll
    static public void launchBrowser() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(5000));
        //browser = playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(5000));
        //browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(5000));
    }

    @BeforeEach
    public void createContextAndPage(){
        context = browser.newContext();
        page = context.newPage();
    }

    @AfterEach
    public void closeContext() {
        page.close();
        context.close();
    }
    @AfterAll
    static public void closeBrowser(){
        browser.close();
        playwright.close();
    }
}

