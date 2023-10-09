import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import org.junit.jupiter.api.Test;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
public class Konsum extends TestBase {
    @Test
    void loggin1() {

        //Navigate to a URL
        String loginUrl = "https://www.coop.se/handla/";
        page.navigate(loginUrl);
        Locator acceptCookies = page.getByRole(AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName("Acceptera alla cookies"));

        if (acceptCookies.isVisible()) {
            acceptCookies.click();
        }
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Logga in")).click();


        Locator usernameInput = page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("E-postadress"));
        Locator passwordInput = page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Lösenord"));
        Locator loginButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Logga in"));
        Locator rememberMeCheckbox = page.getByRole(AriaRole.CHECKBOX, new Page.GetByRoleOptions().setName("Kom ihåg mig"));

        //Enter login and password (text input)
        usernameInput.fill("s.josefin@gmail.com");
        passwordInput.fill("12345678");

        //Check and uncheck Remember me (checkbox)
        rememberMeCheckbox.check();
        loginButton.click();
        assertThat(page).hasURL("https://www.coop.se/handla/");


    }

    @Test
    void failedlogin() {

        //Navigate to a URL
        String loginUrl = "https://www.coop.se/handla/";
        page.navigate(loginUrl);
        Locator acceptCookies = page.getByRole(AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName("Acceptera alla cookies"));

        if (acceptCookies.isVisible()) {
            acceptCookies.click();
        }

        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Logga in")).click();


        Locator usernameInput = page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("E-postadress"));
        Locator passwordInput = page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Lösenord"));
        Locator loginButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Logga in"));
        Locator rememberMeCheckbox = page.getByRole(AriaRole.CHECKBOX, new Page.GetByRoleOptions().setName("Kom ihåg mig"));

        //Enter login and password (text input)
        usernameInput.fill("admin@gmail.com");
        passwordInput.fill("admin");

        //Check and uncheck Remember me (checkbox)
        rememberMeCheckbox.check();
        loginButton.click();

        // loginButton.click();
        Locator text = page.getByText("Det finns inget kundkonto på coop.se med den här e-postadressen. Om det är första gången du loggar in behöver du ");
        assertThat(text).isVisible();
    }


    @Test
    void meny() {
        page.navigate("https://www.coop.se/");
        Locator acceptCookies = page.getByRole(AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName("Acceptera alla cookies"));

        if (acceptCookies.isVisible()) {
            acceptCookies.click();
        }
        Locator svgIcon = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Meny"));
        svgIcon.click();
        Locator handla = page.locator("#portal").getByRole(AriaRole.LINK, new Locator.GetByRoleOptions().setName("Handla online"));
        handla.click();
        Locator searchfield = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Mejeri & Ägg"));
        searchfield.click();
        assertThat(searchfield).containsText("Mejeri & Ägg");


    }

    @Test
        // denna test fungerar inte längre och det beror på att tiderna ändra dag till dag på webben.
    void meny1() {
        page.navigate("https://www.coop.se/handla/varor/frukt-gronsaker");
        Locator acceptCookies = page.getByRole(AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName("Acceptera alla cookies"));

        if (acceptCookies.isVisible()) {
            acceptCookies.click();
        }
        Locator svgIcon = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Frukt & Grönsaker"));
        svgIcon.click();
        page.waitForLoadState();
        page.getByText("Gurka", new Page.GetByTextOptions().setExact(true)).click();
        page.locator("[data-test=\"addtocart-addbutton\"]").click();
        page.locator("[data-id=\"0\"]").fill("1");
        page.locator("[data-id=\"1\"]").fill("1");
        page.locator("[data-id=\"2\"]").fill("3");
        page.locator("[data-id=\"3\"]").fill("6");
        page.locator("[data-id=\"4\"]").fill("8");
        page.getByText("Coop Hagastaden").click();
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("09:00-11:00")).click();
        Locator Borjahandla = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Börja handla"));
        assertThat(Borjahandla).containsText("Börja handla");


    }

    @Test
    void varukorg() {
        page.navigate("https://www.coop.se/handla/varor/frukt-gronsaker/rotfrukter-svamp/potatis/farskpotatis-2317483300007");
        Locator acceptCookies = page.getByRole(AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName("Acceptera alla cookies"));

        if (acceptCookies.isVisible()) {
            acceptCookies.click();
        }
        Locator svgIcon = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Frys"));
        svgIcon.click();
        page.waitForLoadState();
        assertThat(page).hasURL("https://www.coop.se/handla/varor/frys");


    }


    @Test
    void ReceptPaj() {
        page.navigate("https://www.coop.se/recept/");
        Locator acceptCookies = page.getByRole(AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName("Acceptera alla cookies"));

        if (acceptCookies.isVisible()) {
            acceptCookies.click();
        }
        page.getByAltText("Äppelpaj").click();
        Locator hasTitle = page.locator("[href*=\"/recept/knackig-appelpaj/\"]");
        hasTitle.click();
        Locator searchfield = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Knäckig äppelpaj"));
        searchfield.click();
        assertThat(searchfield).containsText("Knäckig äppelpaj");


    }

    @Test
    void Kundservice() {
        page.navigate("https://www.coop.se/coop-kundservice/");
        Locator acceptCookies = page.getByRole(AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName("Acceptera alla cookies"));

        if (acceptCookies.isVisible()) {
            acceptCookies.click();
        }
        Locator checkButton = page.getByRole(AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName("Se Kontaktuppgifter"));
        checkButton.click();
        assertThat(checkButton).isEnabled();


    }

    @Test
    void BliMedlem() {
        page.navigate("https://www.coop.se/medlem/coop-medlemsprogram/");
        Locator acceptCookies = page.getByRole(AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName("Acceptera alla cookies"));

        if (acceptCookies.isVisible()) {
            acceptCookies.click();
        }

        Locator checktext = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Poängshop"));
        checktext.click();
        assertThat(checktext).containsText("Poängshop");

    }

    @Test
    void ButikerErbjudande() {
        page.navigate("https://www.coop.se/butiker-erbjudanden/");
        Locator acceptCookies = page.getByRole(AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName("Acceptera alla cookies"));

        if (acceptCookies.isVisible()) {
            acceptCookies.click();
        }

        Locator verifyText = page.getByPlaceholder("Sök på butik, ort eller postnummer");
        verifyText.fill("Coop Stora Rådmansgatan");
        page.keyboard().press("Enter");


    }

    @Test
    void Bankbetalkort() {
        page.navigate("https://www.coop.se/bank-och-betalkort/");
        Locator acceptCookies = page.getByRole(AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName("Acceptera alla cookies"));
        if (acceptCookies.isVisible()) {
            acceptCookies.click();
        }
        Locator hasText = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Inköpstjänst"));
        hasText.click();
        Locator mobilbetalning = page.getByText("Mobilbetalning");
        mobilbetalning.hover();
        assertThat(mobilbetalning).isVisible();

    }

    @Test
    void Konsum() {
        page.navigate(" https://www.coop.se/");
        Locator acceptCookies = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Acceptera alla cookies"));
        if (acceptCookies.isVisible()) {
            acceptCookies.click();
            Locator tjansterverktyg = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Tjänster & verktyg"));
            tjansterverktyg.click();
            assertThat(tjansterverktyg).isVisible();
        }

    }
}
