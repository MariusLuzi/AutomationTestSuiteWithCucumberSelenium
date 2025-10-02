public class orangeHRMSteps {

    public WebDriver driver;

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public orangeHRMSteps() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }
@Test
    @Given("navigate to orangeHRM website")
    public void navigateToOrangeHRMWebsite() {
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
    }
@Test
    @And("enter value {string} as username")
    public void enterValueAdminAsUsername() {
    System.out.println("here");
        driver.findElement(By.cssSelector("input[name='username']")).sendKeys("username");
    }

@Test
    @Given("enter value {string} as password")
    public void enter_value_admin123_as_password() {
        driver.findElement(By.cssSelector("input[name='password']")).sendKeys("password");
    }
@Test
    @When("click Login button")
    public void click_login_button() {
        driver.findElement(By.cssSelector("button[type='submit']")).click();
    }
@Test
    @Then("verify that you are logged in successfully")
    public void verify_that_you_are_logged_in_successfully() {
        String expectedURL = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
        String currentURL = driver.getCurrentUrl();
        Assertions.assertEquals(expectedURL, currentURL);
    }
@Test
    @After
    public void tearDown() {
        driver.close();
        driver.quit();
    }

  @AfterStep
    public void afterStep() {
        try {
           Thread.sleep(2000);
       } catch (InterruptedException e) {
            e.printStackTrace();
      }
    }

    @Then("make sure that an error message with following text is thrown")
    public void makeSureErrorIsThrown(DataTable errorMessage) {
        String expectedMessage = errorMessage.asList().get(0);
        String currentMessage = driver.findElement(By.cssSelector(".orangehrm-login-error > div:nth-child(1) p")).getText();

        Assertions.assertEquals(expectedMessage, currentMessage);
    }
}
